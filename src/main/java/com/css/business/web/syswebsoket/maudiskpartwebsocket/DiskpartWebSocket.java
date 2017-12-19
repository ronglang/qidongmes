package com.css.business.web.syswebsoket.maudiskpartwebsocket;

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
 * @Title: DiskpartWebSocket.java
 * @Package com.css.business.web.syswebsoket.maudiskpartwebsocket
 * @Description: 分盘的进度数据 websocket
 * @author rb
 * @date 2017年7月19日 下午1:28:51
 * @company SMTC
 */
@ServerEndpoint(value = "/diskpartWebSocket", configurator = GetHttpSessionConfigurator.class)
public class DiskpartWebSocket {
	public static boolean flag = false;
	public  String text;
	EhCacheFactory cacheFactory = EhCacheFactory.getInstance();

	@OnMessage
	public void OnMessage(String message, Session session, EndpointConfig config)
			throws Exception {
		while (session.isOpen() && flag) {
			session.getBasicRemote().sendText(text);// 发送消息到前台
			flag = false;
		}
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
	}

	@OnClose
	public void onClose() {
		System.out.println("分盘的进度数据的webSocket  Connection closed");
	}

	/**
	 * 
	 * @Description: 获得缓存信息,定时刷新
	 */
	public void getEhCache() {
		while (true) {
			try {
				// 查询缓存  机台id
				String crewId = cacheFactory
						.getWebsocketCmdCache(YorkUtil.Memcache_看板_分盘_任务);
				if (crewId != null && crewId != "") {
					// 如果有就发送到前台
					text = crewId;
					flag = true;
				}
				//删除缓存
				cacheFactory.removeWebsocketCmdCache(YorkUtil.Memcache_看板_分盘_任务);
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
