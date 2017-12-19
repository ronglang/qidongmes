/**     
 *
 */
package com.css.business.web.syswebsoket.storewebsocket;

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
 * @Title: CenExceptionWebS.java
 * @Package com.css.business.web.syswebsoket
 * @Description: 仓库电子看板Grid的websocket
 * @author JS
 * @date 2017年7月11日 下午2:16:05
 * @company SMTC
 */
@ServerEndpoint(value = "/storeCrewWebS", configurator = GetHttpSessionConfigurator.class)
public class storeCrewWebS {

	// 刷新条件判断
	private boolean flag = false;
	EhCacheFactory cacheFactory = EhCacheFactory.getInstance();

	@OnMessage
	public void OnMessage(String message, Session session, EndpointConfig config)
			throws Exception {
		while (session.isOpen()) {
			if (getfFlag()) {
				try {
					session.getBasicRemote().sendText("true");// 发送消息到前台
					
				} catch (Exception e) {
					throw new Exception("异常的websocket,出现异常");
				}
			}
			Thread.sleep(6000);
		}
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return         
	 */ 
	private boolean getfFlag() {
		// TODO Auto-generated method stub
		String cmdCache = cacheFactory.getWebsocketCmdCache(YorkUtil.Memcache_看板_仓库_列表);
		if (cmdCache!=null && cmdCache!="") {
			cacheFactory.removeWebsocketCmdCache(YorkUtil.Memcache_看板_仓库_列表);
			return true;
		}
		return false;
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
	}

	@OnClose
	public void onClose() {
		System.out.println("仓库信息的webSocket  Connection closed");
	}


}
