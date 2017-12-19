package com.css.business.web.syswebsoket.newProceWebSocket;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.css.business.web.subsysmanu.mauManage.service.MauPowerManageService;
import com.css.common.servlet.GetHttpSessionConfigurator;
import com.css.common.web.apachemq.bean.MauPowerVo;
import com.css.common.web.syscommon.service.SpringContextHolder;
import com.google.gson.Gson;

/**
 * 
* @Title: MauPowerWebSocket.java   
* @Package com.css.business.web.syswebsoket.MauPowerWebSocket   
* @Description: 能耗websocket   
* @author   JS
* @date 2017年10月13日 下午2:43:07   
* @company  SMTC
 */
@ServerEndpoint(value = "/mauPowerWebSocket", configurator = GetHttpSessionConfigurator.class)
public class MauPowerWebSocket {

	//private EhCacheFactory factory = EhCacheFactory.getInstance();
		public MauPowerVo text ;
		
		private MauPowerManageService service = SpringContextHolder.getBean("mauPowerManageService");
		
		@OnMessage
		public void OnMessage(String message, Session session, EndpointConfig config)
				throws Exception {
			while (session.isOpen()) {
				if (getFlag(message)) {
					try {
						Gson gson = new Gson();
						String json = gson.toJson(text);
						session.getBasicRemote().sendText(json);// 发送消息到前台
					} catch (Exception e) {
						// TODO Auto-generated catch block
						throw new Exception("能耗的websocket,出现异常");
					}
					Thread.sleep(600000);
				}
			}
		}

		/**   
		 * @Description: TODO(这里用一句话描述这个方法的作用)   
		 * @return         
		 */ 
		private boolean getFlag(String message) {
			MauPowerVo vo = service.getEchartsDate(null, null, null);
			if (vo.getSeries() != null  && !"".equals(vo.getSeries())) {
				text = vo;
				return true;
			}
			return false;
		
		}

		@OnOpen
		public void onOpen(Session session, EndpointConfig config) {
		}

		@OnClose
		public void onClose() {
			System.out.println("能耗的webSocket  Connection closed");
		}
}
