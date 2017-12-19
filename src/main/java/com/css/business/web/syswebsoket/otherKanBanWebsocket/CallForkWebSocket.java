
package com.css.business.web.syswebsoket.otherKanBanWebsocket;

import javax.servlet.http.HttpServletRequest;
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
 * @Title: CallForkWebSocket.java   
 * @Package com.css.business.web.syswebsoket.otherKanBanWebsocket   
 * @Description: 叉车呼叫的websocket   
 * @author   rb
 * @date 2017年9月27日 上午9:56:46   
 * @company  SMTC   
 */
@ServerEndpoint(value = "/callForkWebSocket", configurator = GetHttpSessionConfigurator.class)
public class CallForkWebSocket {

	// 刷新条件判断
	private EhCacheFactory factory = EhCacheFactory.getInstance();
	public String text;
			@OnMessage
			public void OnMessage(String message, Session session, EndpointConfig config,HttpServletRequest req)
					throws Exception {
				while (session.isOpen()) {
						if (getFlag()) {
							try {
								session.getBasicRemote().sendText(text);// 发送消息到前台
							} catch (Exception e) {
								throw new Exception("车间电子看板呼叫叉车通知消息的websocket,出现异常");
							}
						}
					//10秒查询一次消息通知
					Thread.sleep(10000);
				}
			}

			/**   
			 * @Description: TODO(这里用一句话描述这个方法的作用)   
			 * @return         
			 */ 
			private boolean getFlag() {
				// TODO Auto-generated method stub
				String cache = factory.getWebsocketCmdCache(YorkUtil.Memcache_看板_叉车呼叫);
				if (cache != null) {
					text= cache;
					return true;
				} 
				return false;
			}

			@OnOpen
			public void onOpen(Session session, EndpointConfig config) {
			}

			@OnClose
			public void onClose() {
				System.out.println("车间电子看板呼叫叉车通知消息的webSocket  Connection closed");
			}
			
			
			
}
