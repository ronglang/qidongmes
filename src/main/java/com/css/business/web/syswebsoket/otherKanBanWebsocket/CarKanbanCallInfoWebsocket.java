package com.css.business.web.syswebsoket.otherKanBanWebsocket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.css.business.web.subsysmanu.bean.MauCallForkliftRecord;
import com.css.business.web.subsysmanu.mauManage.service.MauCallForkliftRecordManageService;
import com.css.common.servlet.GetHttpSessionConfigurator;
import com.css.common.web.syscommon.service.SpringContextHolder;
import com.google.gson.Gson;

/**
 * 车间电子看板查询机台状态个数
 * @author Administrator
 *
 */
@ServerEndpoint(value = "/CarKanbanCallInfoWebsocket", configurator = GetHttpSessionConfigurator.class)
public class CarKanbanCallInfoWebsocket {
	// 刷新条件判断
		private Gson gson = new Gson();
		private boolean flag = false;
		@OnMessage
		public void OnMessage(String message, Session session, EndpointConfig config,HttpServletRequest req)
				throws Exception {
			while (session.isOpen()) {
					if (flag) {
						try {
							String json = getMacCount(req);
							session.getBasicRemote().sendText(json);// 发送消息到前台
						} catch (Exception e) {
							throw new Exception("车间电子看板呼叫叉车通知消息的websocket,出现异常");
						}
					}
				//10秒查询一次消息通知
				Thread.sleep(30000);
			}
		}

		@OnOpen
		public void onOpen(Session session, EndpointConfig config) {
			flag = true;
		}

		@OnClose
		public void onClose() {
			flag = false;
			System.out.println("车间电子看板呼叫叉车通知消息的webSocket  Connection closed");
		}
		
		public String getMacCount(HttpServletRequest req) {
			Map<String, Object> map = new HashMap<>();
			MauCallForkliftRecordManageService service = SpringContextHolder.getBean("mauCallForkliftRecordManageService");
			List<MauCallForkliftRecord> list = null;
			try{
				list = service.queryCallCarList();
				map.put("data", list);
			}catch(Exception e){
				map.put("error", "通知消息异常");
				e.printStackTrace();
			}
			
			String str = gson.toJson(map);
			return str;
		}

		
}
