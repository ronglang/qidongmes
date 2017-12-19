package com.css.common.web.apachemq.handle;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import com.css.business.web.subsyscraft.craManage.dao.CraCmaterManageDAO;
import com.css.common.web.syscommon.service.SpringContextHolder;

public class MesSocketServerThread implements Runnable{
	private Socket clientSock;
	private BufferedOutputStream ous = null;
	private BufferedInputStream bis = null;
	@SuppressWarnings("unused")
	private CraCmaterManageDAO craCmaterManageDAO ;
	
	public MesSocketServerThread(Socket sock){
		craCmaterManageDAO = (CraCmaterManageDAO)SpringContextHolder.getBean("craCmaterManageDAO");
		this.clientSock = sock;
		try {
			bis = this.getBufferInput();
			ous = this.getOut();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run(){
		while(isValid()){
			try {
				int i = 0;
				int avi = bis.available();
				InetAddress address = clientSock.getInetAddress();
				if(avi > 0){
					System.out.println("接收到客户端："+address.getHostAddress()+"，长度："+avi);
					byte r[] = new byte[avi];
					bis.read(r);
					int d[] = new int[avi];
					StringBuffer sb = new StringBuffer();
					for(int j = 0 ; j < avi ; j ++){
						d[j] = r[j] & 0xff;
						sb.append(d[j]);
					}
					System.out.println("转换前数据"+sb.toString());
					String str = new String(d,0,d.length);
					System.out.println("转换后数据"+str);
					ous.write(0x80);
					//ous.flush();
				}
				Thread.sleep(300);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					bis.close();
					ous.close();
					clientSock.close();
					System.out.println("接收客户端数据出现异常"+e.getMessage());
					break;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
		}
	}
	
	@SuppressWarnings("unused")
	private BufferedInputStream getBufferInput() throws IOException{
		BufferedInputStream bis = new BufferedInputStream(clientSock.getInputStream());
		return bis;
	}
	
	@SuppressWarnings("unused")
	private DataInputStream getDataInput() throws IOException{
		DataInputStream bis = new DataInputStream(clientSock.getInputStream());
		return bis;
	}
	
	@SuppressWarnings("unused")
	private BufferedOutputStream getOut() throws IOException{
		BufferedOutputStream bos = new BufferedOutputStream(clientSock.getOutputStream());
		return bos;
	}
	
	private boolean isValid(){
		return clientSock != null && clientSock.isBound() && clientSock.isConnected(); 
	}
}
