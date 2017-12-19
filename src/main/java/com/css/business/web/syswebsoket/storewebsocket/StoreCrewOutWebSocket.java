
package com.css.business.web.syswebsoket.storewebsocket;

import java.util.List;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.css.business.web.subsysstore.bean.StoreObj;
import com.css.business.web.subsysstore.storeManage.service.StoreObjManageService;
import com.css.commcon.util.YorkUtil;
import com.css.common.servlet.GetHttpSessionConfigurator;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.service.SpringContextHolder;
import com.google.gson.Gson;

/**     
 * @Title: StoreCrewOutWebSocket.java   
 * @Package com.css.business.web.syswebsoket.storewebsocket   
 * @Description: 出库websocket
 * @author   rb
 * @date 2017年9月27日 上午11:52:07   
 * @company  SMTC   
 */
@ServerEndpoint(value = "/storeCrewOutWebSocket", configurator = GetHttpSessionConfigurator.class)
public class StoreCrewOutWebSocket {

	// 刷新条件判断
		public static boolean flag = false;
		EhCacheFactory cacheFactory = EhCacheFactory.getInstance();
		Gson gson = new Gson();
		private StoreObjManageService soService  = SpringContextHolder.getBean("storeObjManageService");
		@OnMessage
		public void OnMessage(String message, Session session, EndpointConfig config)
				throws Exception {
			while (session.isOpen()) {
				if (getFlag()) {
					try {
					List<StoreObj> list = soService.getTodayList("出库");
					session.getBasicRemote().sendText(gson.toJson(list));// 发送消息到前台
						
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
		private boolean getFlag() {
			// TODO Auto-generated method stub
			String cache = cacheFactory.getWebsocketCmdCache(YorkUtil.Memcache_看板_仓库_出库);
			if (cache!=null && cache!="") {
				cacheFactory.removeWebsocketCmdCache(YorkUtil.Memcache_看板_仓库_出库);
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
