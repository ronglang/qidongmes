package com.css.business.web.syswebsoket.marquee;

import java.util.List;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.css.business.web.sysManage.bean.SysNotice;
import com.css.business.web.sysManage.service.SysNoticeManageService;
import com.css.common.servlet.GetHttpSessionConfigurator;
import com.css.common.web.syscommon.service.SpringContextHolder;
import com.google.gson.Gson;

/**
 * @Title: NoticeWebSocket.java
 * @Package com.css.business.web.syswebsoket.marquee
 * @Description: 页面滚动
 * @author rb
 * @date 2017年9月7日 下午4:48:07
 * @company SMTC
 */
@ServerEndpoint(value = "/noticeWebSocket", configurator = GetHttpSessionConfigurator.class)
public class NoticeWebSocket {
	private Gson gson = new Gson();
	private SysNoticeManageService service = SpringContextHolder.getBean("sysNoticeManageService");
	@OnMessage
	public void OnMessage(String message, Session session, EndpointConfig config)
			throws Exception {
		while (session.isOpen()) {
			try {
				//String json = getNotice();
				//session.getBasicRemote().sendText(json);// 发送消息到前台
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception("异常的websocket,出现异常");
			}
			Thread.sleep(6000);
		}
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return         
	 */ 
	private String getNotice() {
		// TODO Auto-generated method stub
		List<SysNotice>list = service.getShowNotices();
		String result =null;
		if (list != null && list.size() > 0 ) {
			for (SysNotice sysNotice : list) {
				result += sysNotice.getValue()+" ";
			}
		}
		if (result == null ) {
			result ="暂时无消息通知";
		}
		return result;
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
	}

	@OnClose
	public void onClose() {
		System.out.println("生产电子看板呼叫叉车的webSocket  Connection closed");
	}
}
