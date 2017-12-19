/**     
 *
 */
package com.css.business.web.syswebsoket.maudepartwebsocket;

import java.io.IOException;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.css.common.servlet.GetHttpSessionConfigurator;

/**
 * 生产部电子看板生产延期情况表格的websocket
 * 
 * @author DELL
 * 
 */
@ServerEndpoint(value = "/productDepartmentShowPlanWebS", configurator = GetHttpSessionConfigurator.class)
public class ProductDepartmentShowPlanWebS {
	// 刷新条件判断
	//private  boolean flag = false;

	@OnMessage
	public void OnMessage(String message, Session session, EndpointConfig config)
			throws Exception {

		while (session.isOpen() /*&& flag*/) {
			try {
				session.getBasicRemote().sendText("true");// 发送消息到前台
				//flag = false;
				Thread.sleep(6000*60*4);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new Exception("异常的websocket,出现异常");
			}
		}
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
	}

	@OnClose
	public void onClose() {
		System.out.println("当日计划的webSocket  Connection closed");
	}
	
	@OnError
	public void onError(Throwable t , Session s) throws IOException{
		if(s != null){
			s.close();
			System.out.println("websocket检测到错误。 关闭当前会话");
		}
	}

	/*public void flushPage() {
		flag = true;
	}*/

}
