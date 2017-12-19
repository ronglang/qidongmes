package com.css.business.web.syswebsoket.newProceWebSocket;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.css.commcon.util.YorkUtil;
import com.css.common.servlet.GetHttpSessionConfigurator;
import com.css.common.util.EhCacheFactory;

/**
 * 
* @Title: NewProceWebSocket.java   
* @Package com.css.business.web.syswebsoket.newProceWebSocket   
* @Description: 工序看板的新websocket   
* @author   rb
* @date 2017年10月13日 下午2:43:07   
* @company  SMTC
 */
@ServerEndpoint(value = "/newProceWebSocket", configurator = GetHttpSessionConfigurator.class)
public class NewProceWebSocket {

	private EhCacheFactory factory = EhCacheFactory.getInstance();
	public String text ;
	
	@OnMessage
	public void OnMessage(String message, Session session, EndpointConfig config)
			throws Exception {
		while (session.isOpen()) {
			if (getFlag(message)) {
				try {
					session.getBasicRemote().sendText(text);// 发送消息到前台
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new Exception("电能的websocket,出现异常");
				}
			}
			Thread.sleep(60000);
		}
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return         
	 */ 
	private boolean getFlag(String macCode) {
		// TODO Auto-generated method stub
		String cmdCache = factory.getWebsocketCmdCache(YorkUtil.Memcache_机台_实时_参数);
		if (cmdCache != null  && !"".equals(cmdCache)) {
			text = cmdCache;
			return true;
		}
		return false;
	
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
	}

	@OnClose
	public void onClose() {
		System.out.println("质检的webSocket  Connection closed");
	}
}
