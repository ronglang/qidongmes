/**
 * @todo: 每轴线生产完毕，提交后。 这里从缓存里取数据，更新到前台JSP
 * @author : zhaichunlei
 * @date: 2017年12月15日
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
import com.google.gson.Gson;

@ServerEndpoint(value = "/plaMacTaskRecord_Save_WebSocket", configurator = GetHttpSessionConfigurator.class)
public class PlaMacTaskRecord_Save_WebSocket {
	// 刷新条件判断
		public static boolean flag = false;
		Gson gson = new Gson();
		public String text;
		EhCacheFactory cacheFactory = EhCacheFactory.getInstance();

		@OnMessage
		public void OnMessage(String message, Session session, EndpointConfig config)
				throws Exception {
			while (session.isOpen()) {
				if (getflag()) {
					try {
						session.getBasicRemote().sendText(text);// 发送消息到前台
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//session.close();
						
						throw new Exception("拉丝电子实时基础信息异常的websocket,出现异常");
					}
				}
				Thread.sleep(1000 * 6);
			}
		}

		/**
		 * @Description: TODO(这里用一句话描述这个方法的作用)
		 * @return
		 */
		private boolean getflag() {
			// TODO Auto-generated method stub
			String cache = cacheFactory.getMacShowCaChe(YorkUtil.Memcache_车间动态);
			if (cache != null && cache.length() > 0) {
				/*List<PlaMacTaskRecord> lst = gson.fromJson(cache,new TypeToken<List<PlaMacTaskRecord>>() {}.getType());
				if(lst == null || lst.size() == 0){
					cacheFactory.removeMacShowCache(YorkUtil.Memcache_车间动态);
					return false;
				}*/
				
				//怎么通知到页面呢
				//把本次有更新的机台、工单 list发到jsp. ajax根据参数查询，再根据返回值更新涉及到的局部变量？
				//把这个东东直接发到jsp.由js解析变更的参数
				text = cache;
				cacheFactory.removeMacShowCache(YorkUtil.Memcache_车间动态);
				return true;
			}
			return false;
		}

		@OnOpen
		public void onOpen(Session session, EndpointConfig config) {
		}

		@OnClose
		public void onClose() {
			System.out.println("拉丝电子实时工序电子看板基础信息的webSocket  Connection closed");
		}
}
