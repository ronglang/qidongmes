
package com.css.business.web.syswebsoket.workshopWebSocket;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.css.business.web.subsysplan.plaManage.bean.PlaMachineDisplayVo;
import com.css.business.web.subsysplan.plaManage.service.PlaCourseManageService;
import com.css.commcon.util.YorkUtil;
import com.css.common.servlet.GetHttpSessionConfigurator;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.service.SpringContextHolder;
import com.google.gson.Gson;

/**     
 * @Title: PlaCourseWebSocket.java   
 * @Package com.css.business.web.syswebsoket.workshopWebSocket   
 * @Description: 工单完成情况 以最后一道工序完成量作为计算
 * @author   rb
 * @date 2017年9月22日 上午9:45:05   
 * @company  SMTC   
 */
@ServerEndpoint(value = "/plaCourseWebSocket_1", configurator = GetHttpSessionConfigurator.class)
public class PlaCourseWebSocket_1 {
	//刷新条件判断
		private  boolean flag = false;
		EhCacheFactory cacheFactory = EhCacheFactory.getInstance();
		PlaCourseManageService plaService = SpringContextHolder.getBean("plaCourseManageService");
		Gson gson =new Gson();
		
		@OnMessage
		public void OnMessage(String message, Session session,EndpointConfig config) throws Exception{
				while (session.isOpen() ) {
					if (getFlag()) {
						try {
							PlaMachineDisplayVo vo = plaService.getTodayComplete();
							session.getBasicRemote().sendText(gson.toJson(vo)); //发送消息到前台
							
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
			String cache = cacheFactory.getWebsocketCmdCache(YorkUtil.Memcache_看板_生产_柱);
			if (cache!=null && cache!="") {
				//有进度更新,查询实时工单完成情况
				//1.查询今日生产的,任务,有哪些工单
				cacheFactory.removeWebsocketCmdCache(YorkUtil.Memcache_看板_生产_柱);
				return true;
			}
			return false;
		}

		@OnOpen
		public void onOpen(Session session, EndpointConfig config){
		}
		
		@OnClose
		public void onClose() {
			System.out.println("仓库信息的webSocket  Connection closed");
		}
		
}
