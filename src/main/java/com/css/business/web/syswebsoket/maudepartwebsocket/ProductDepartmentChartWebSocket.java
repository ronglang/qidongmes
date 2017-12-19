/**     
 *
 */
package com.css.business.web.syswebsoket.maudepartwebsocket;

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
 * 生产部电子看板柱状图页面的websocket
 * 
 * @author DELL
 * 
 */
@ServerEndpoint(value = "/productDepartmentChartWebSocket", configurator = GetHttpSessionConfigurator.class)
public class ProductDepartmentChartWebSocket {

	// 刷新条件判断
	private boolean flag = false;
	private String str = "";
	EhCacheFactory cacheFactory = EhCacheFactory.getInstance();

	@OnMessage
	public void OnMessage(String message, Session session, EndpointConfig config)
			throws Exception {
		while (session.isOpen()) {
			if (getFlag()) {
				try {
					session.getBasicRemote().sendText("true");// 发送消息到前台
					Thread.sleep(6000);
				} catch (Exception e) {
					throw new Exception("异常的websocket,出现异常");
				}
			}
		}
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
	}

	@OnClose
	public void onClose() {
		System.out.println("仓库信息的webSocket  Connection closed");
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	private boolean getFlag() {
		// TODO Auto-generated method stub
		String cache = cacheFactory
				.getWebsocketCmdCache(YorkUtil.Memcache_看板_生产_柱);
		if (cache != null && cache != "") {
			cacheFactory.removeWebsocketCmdCache(YorkUtil.Memcache_看板_生产_柱);
			return true;
		}
		return false;
	}

	public void flushPage() {
		flag = true;
	}

}
