
package com.css.business.web.syswebsoket.workshopWebSocket;

import java.util.Date;
import java.util.List;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.css.business.web.sysManage.bean.SysNotice;
import com.css.commcon.util.YorkUtil;
import com.css.common.servlet.GetHttpSessionConfigurator;
import com.css.common.util.EhCacheFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**     
 * @Title: SysNoticeToWorkShopWebSocket.java   
 * @Package com.css.business.web.syswebsoket.workshopWebSocket   
 * @Description: 车间电子看板的欢迎词  
 * @author   rb
 * @date 2017年9月25日 下午4:12:41   
 * @company  SMTC   
 */
@ServerEndpoint(value = "/sysNoticeToWorkShopWebSocket", configurator = GetHttpSessionConfigurator.class)
public class SysNoticeToWorkShopWebSocket {
	private Gson gson = new Gson();
	private EhCacheFactory eFactory = EhCacheFactory.getInstance();
	public String text;
	
	@OnMessage
	public void OnMessage(String message, Session session,EndpointConfig config) throws Exception{
			while (session.isOpen() ) {
				if (getFlag()) {
					try {
						session.getBasicRemote().sendText(text); //发送消息到前台
					} catch (Exception e) {
						//session.close();
						throw new Exception("车间欢迎词的websocket,出现异常");
					}
				}
				Thread.sleep(60000);
			}
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return         
	 */ 
	private boolean getFlag() {
		// TODO Auto-generated method stub
		//SysNoticeManageService noticeService = SpringContextHolder.getBean("sysNoticeManageService");
		String jsonList = eFactory.getWebsocketCmdCache(YorkUtil.Memcache_车间致辞);
		List<SysNotice>list = gson.fromJson(jsonList, new TypeToken<List<SysNotice>>(){}.getType());
		if(list != null && list.size() > 0){
			//第一条 的结束时间和当前对比,如果 小于当前时间 ,显示已完成删除
			long start = list.get(0).getStartTime().getTime();
			long end = list.get(0).getEndTime().getTime();
			long now = new Date().getTime();
			if (end >= now && start <= now) {
				text = gson.toJson(list.get(0));
				return true;
			}else if(end < now){
				list.remove(0);
				eFactory.addWebsocketCmdCache(YorkUtil.Memcache_车间致辞, gson.toJson(list));
				text = gson.toJson("0");
				return true;
			}
		}
		text = gson.toJson("0");
		return true;
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config){
	}
	
	@OnClose
	public void onClose() {
		System.out.println("车间欢迎词的webSocket  Connection closed");
	}
}
