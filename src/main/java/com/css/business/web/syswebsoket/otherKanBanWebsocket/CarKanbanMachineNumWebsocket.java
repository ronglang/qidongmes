package com.css.business.web.syswebsoket.otherKanBanWebsocket;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.css.business.web.subsysmanu.mauManage.service.MauMachineManageService;
import com.css.common.servlet.GetHttpSessionConfigurator;
import com.css.common.web.syscommon.service.SpringContextHolder;
import com.google.gson.Gson;

/**
 * 车间电子看板查询机台状态个数
 * @author Administrator
 *
 */
@ServerEndpoint(value = "/CarKanbanMachineNumWebsocket", configurator = GetHttpSessionConfigurator.class)
public class CarKanbanMachineNumWebsocket {
	// 刷新条件判断
		Gson gson = new Gson();
		private boolean flag = false;
		private String text;
		@OnMessage
		public void OnMessage(String message, Session session, EndpointConfig config,HttpServletRequest req)
				throws Exception {
			while (session.isOpen()) {
				if(flag && getMacCount()){
					//try {
						String json = text;
						session.getBasicRemote().sendText(json);// 发送消息到前台
					//} catch (Exception e) {
						//throw new Exception("车间电子看板查询机台状态个数的websocket,出现异常");
					//}
				}
				//多少秒查询一次机台状态数量
				Thread.sleep(60000);
			}
		}

		@OnOpen
		public void onOpen(Session session, EndpointConfig config) {
			flag = true;
		}

		@OnClose
		public void onClose() {
			flag = false;
			System.out.println("车间电子看板查询机台状态个数的webSocket  Connection closed");
		}
		
		public boolean getMacCount() {
			try{
				Map<String, String> map = new HashMap<String, String>();
				MauMachineManageService service = SpringContextHolder.getBean("mauMachineManageService");
				
				Long errorCount = service.getCount("故障");
				Long allCount = service.getCount("");
				Long stopCount = service.getCount("关闭");
				Long openCount = service.getCount("运行");
				Long maintainCount = service.getCount("保养");
				map.put("errorCount", errorCount.toString());
				map.put("allCount", allCount.toString());
				map.put("stopCount", stopCount.toString());
				map.put("openCount", openCount.toString());
				map.put("maintainCount", maintainCount.toString());
				text = gson.toJson(map);
				return true;
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("车间电子看板查询机台状态个数的websocket,出现异常");
				return false;
			}
			
			
		}

		
}
