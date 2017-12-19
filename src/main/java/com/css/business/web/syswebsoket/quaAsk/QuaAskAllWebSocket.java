
package com.css.business.web.syswebsoket.quaAsk;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.css.commcon.util.YorkUtil;
import com.css.common.servlet.GetHttpSessionConfigurator;
import com.css.common.util.EhCacheFactory;
import com.google.gson.Gson;

/**     
 * @Title: QuaAskAllWebSocket.java   
 * @Package com.css.business.web.syswebsoket.quaAsk   
 * @Description: 获得质检呼叫  ,未处理的 或者当天的   
 * @author   rb
 * @date 2017年9月27日 下午4:07:21   
 * @company  SMTC   
 */
@ServerEndpoint(value = "/quaAskAllWebSocket", configurator = GetHttpSessionConfigurator.class)
public class QuaAskAllWebSocket {

	private Gson gson = new Gson();
	private EhCacheFactory cacheFactory = EhCacheFactory.getInstance();
	public String text = null;
	@OnMessage
	public void OnMessage(String message, Session session, EndpointConfig config)
			throws Exception {
		while (session.isOpen()) {
			if (getFlag()) {
				try {
					session.getBasicRemote().sendText(text);// 发送消息到前台
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new Exception("质检呼叫的websocket,出现异常");
				}
			}
			Thread.sleep(6000);
		}
	}
	
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
	}

	@OnClose
	public void onClose() {
		System.out.println("质检呼叫的webSocket  Connection closed");
	}
	
	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return         
	 */ 
	private boolean getFlag() {
		// TODO Auto-generated method stub
		String cmdCache = cacheFactory.getWebsocketCmdCache(YorkUtil.Memcache_质检呼叫_grid);
		if (cmdCache != null) {
			text = cmdCache;
			return true;
		}
		return false;
	}
}
