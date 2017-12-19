package com.css.common.servlet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.css.common.util.PropertyFileUtil;

/**
 * @TODO  : WEB系统的Socket服务端 
 * @author: 翟春磊
 * @DATE  : 2017年7月13日
 */
public class ServerSo extends Thread{
	private PropertyFileUtil pfu = new PropertyFileUtil();
	private static ServerSo so;
	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	private ExecutorService es = Executors.newCachedThreadPool();
	private final byte reply = 0x68; //收到数据后的回复
	private ByteBuffer bb; //缓存
	
	public static ServerSo getInstance(){
		if(so == null)
			so = new ServerSo();
		
		return so;
	}

	private ServerSo()   {
		try {
			Integer port = Integer.parseInt(pfu.getProp("SERVER_PORT"));
			selector = Selector.open();

			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.socket().bind(new InetSocketAddress(port));

			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("工序服务器端启动失败：" + e.getMessage());
		}
	}
	
	@Override
	public void run() {
		System.out.println("线程启动\n");
		while(true){
			try {
				int selected = selector.select();
				if (selected > 0) {
					Iterator<SelectionKey> it = selector.selectedKeys().iterator();
					while (it.hasNext()) {
						SelectionKey k = it.next();
						it.remove();
						if (k.isValid() && k.isAcceptable()) {
							doAccept(k);
						} else if (k.isValid() && k.isReadable()) {
							doRead(k);
						} else if (k.isValid() && k.isWritable()) {
							doWrite(k);
						}
					}
				}
				Thread.sleep(200);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				this.disconnect();
				System.out.println("服务器出现异常:"+e.getMessage()+"\n");
				break;
			}
		}
	}


	private void doAccept(SelectionKey k) throws IOException {
		ServerSocketChannel server = (ServerSocketChannel) k.channel();
		SocketChannel clientChannel;
		clientChannel = server.accept();
		clientChannel.configureBlocking(false);
		SelectionKey clientKey = clientChannel.register(selector,
				SelectionKey.OP_READ);
		EchoClient c = new EchoClient();
		clientKey.attach(c);
		InetAddress clientAddress = clientChannel.socket().getInetAddress();
		System.out.println("Accepted connection from "
				+ clientAddress.getHostAddress());
	}

	private void doRead(SelectionKey sk) {
		SocketChannel channel = (SocketChannel) sk.channel();
		bb = ByteBuffer.allocate(8192);
		int len;
		try {
			len = channel.read(bb);
			if (len < 0) {
				disconnect(channel,sk);
				return;
			}
			// 切换buffer到读状态,内部指针归位.
			bb.flip();
			String msg = Charset.forName("UTF-8").decode(bb).toString();
            System.out.println("Client received ["+msg+"] from server address:" + channel.getRemoteAddress());
		} catch (Exception e) {
			disconnect(channel,sk);
			return;
		}
		bb.flip();
		es.execute(new HandleMsg(sk, bb));
	}

	private void doWrite(SelectionKey sk) {
		SocketChannel channel = (SocketChannel) sk.channel();
		EchoClient echoClient = (EchoClient) sk.attachment();
		LinkedList<ByteBuffer> outq = echoClient.getOutputQueue();
		ByteBuffer bb = outq.getLast();
		try {
			//int len = channel.write(bb);
			ByteBuffer bc = ByteBuffer.allocate(1);
			bc.put(reply);
			int len = channel.write(bc);
			if (len == -1) {
				disconnect(channel,sk);
				return;
			}
			if (bb.remaining() == 0) {
				outq.removeLast();
			}
			
			//写到中心服务器
		} catch (Exception e) {
			disconnect(channel,sk);
		}
		if (outq.size() == 0) {
			sk.interestOps(SelectionKey.OP_READ);
		}
	}

	private void disconnect(SocketChannel c,SelectionKey sk) {
		try {
			sk.cancel();
			c.close();
			System.out.println("服务端发生异常，关闭一个客户端的连接\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void disconnect(){
		try {
			serverSocketChannel.close();
			selector.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class EchoClient {
		private LinkedList<ByteBuffer> outq;

		EchoClient() {
			outq = new LinkedList<ByteBuffer>();
		}

		public LinkedList<ByteBuffer> getOutputQueue() {
			return outq;
		}

		public void enqueue(ByteBuffer bb) {
			outq.addFirst(bb);
		}
	}

	class HandleMsg implements Runnable {
		SelectionKey sk;
		ByteBuffer bb;

		public HandleMsg(SelectionKey sk, ByteBuffer bb) {
			super();
			this.sk = sk;
			this.bb = bb;
		}

		@Override
		public void run() {
			EchoClient echoClient = (EchoClient) sk.attachment();
			echoClient.enqueue(bb);
			sk.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
			selector.wakeup();
		}
	}
}
