
package com.css.business.web.syswebsoket.maudepartwebsocket;

import javax.websocket.server.ServerEndpoint;

import com.css.common.servlet.GetHttpSessionConfigurator;

/**     
 * @Title: SellContractSurveyWebSocket.java   
 * @Package com.css.business.web.syswebsoket.maudepartwebsocket   
 * @Description: 生产通知单简要概述  未分解,未生产,生产中,已生产 
 * @author   rb
 * @date 2017年9月26日 下午2:57:26   
 * @company  SMTC   
 */
@ServerEndpoint(value = "/sellContractSurveyWebSocket", configurator = GetHttpSessionConfigurator.class)
public class SellContractSurveyWebSocket {
	/*
	private SellContractManageService service = SpringContextHolder.getBean("sellContractManageService");
	private Gson gson = new Gson();
	private EhCacheFactory factory = EhCacheFactory.getInstance();
	@OnMessage
	public void OnMessage(String message, Session session, EndpointConfig config)
			throws Exception {

		while (session.isOpen() ) {
			if(getFlag()){
				try {
					String text = this.getSellContractSurvey();
					session.getBasicRemote().sendText(text);// 发送消息到前台
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new Exception("生产通知单的websocket,出现异常");
				}
			}
			Thread.sleep(6000);
		}
	}

	*//**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return         
	 *//* 
	private boolean getFlag() {
		// TODO Auto-generated method stub
		String cache = factory.getWebsocketCmdCache(YorkUtil.Memcache_看板_生产_通知单);
		if (cache != null) {
			factory.removeWebsocketCmdCache(YorkUtil.Memcache_看板_生产_通知单);
			return true;
		}
		return false;
	}

	*//**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return         
	 *//* 
	private String getSellContractSurvey() {
		// TODO Auto-generated method stub
		Map<String,String> map=service.getSellContractSurvey();
		return gson.toJson(map);
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
	}

	@OnClose
	public void onClose() {
		System.out.println("生产通知单的webSocket  Connection closed");
	}
	
	@OnError
	public void onError(Throwable t , Session s) throws IOException{
		if(s != null){
			s.close();
			System.out.println("生产通知单  websocket检测到错误。 关闭当前会话");
		}
	}*/
}
