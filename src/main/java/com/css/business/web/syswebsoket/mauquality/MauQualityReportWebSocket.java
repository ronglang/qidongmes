
package com.css.business.web.syswebsoket.mauquality;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.css.business.web.subsysquality.quaManage.controller.QualityMauReportManageAction;
import com.css.common.servlet.GetHttpSessionConfigurator;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.service.SpringContextHolder;

/**     
 * @Title: MauQualityReportWebSocket.java   
 * @Package com.css.business.web.syswebsoket.mauquality   
 * @Description: 生产质检的websocket,实现功能:有扫面的时候,就监控是否有新的扫面过来   ;;;;;停用
 * @author   rb
 * @date 2017年9月25日 上午11:08:08   
 * @company  SMTC   
 */
@Deprecated
@ServerEndpoint(value = "/mauQualityReportWebSocket", configurator = GetHttpSessionConfigurator.class)
public class MauQualityReportWebSocket {
	private EhCacheFactory cacheFactory = EhCacheFactory.getInstance();
	public String text;
	@OnMessage
	public void OnMessage(String message, Session session, EndpointConfig config)
			throws Exception {
		while (session.isOpen()) {
			if (getFlag()) {
				try {
					session.getBasicRemote().sendText(text);// 发送消息到前台
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new Exception("质检的websocket,出现异常");
				}
			}
			Thread.sleep(1000);
		}
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return         
	 */ 
	private boolean getFlag() {
		// TODO Auto-generated method stub
		QualityMauReportManageAction mauAction = SpringContextHolder.getBean("qualityMauReportManageAction");
		String reportId = cacheFactory.getMacShowCaChe("reportId");
		String testType = cacheFactory.getMacShowCaChe("testType");
		
		return false;
	
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
	}

	@OnClose
	public void onClose() {
		System.out.println("质检的webSocket  Connection closed");
	}
}
