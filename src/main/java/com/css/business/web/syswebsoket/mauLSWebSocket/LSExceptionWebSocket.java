
package com.css.business.web.syswebsoket.mauLSWebSocket;

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

/**     
 * @Title: CLExceptionWebSocket.java   
 * @Package com.css.business.web.syswebsoket.mauCLWebSocket   
 * @Description: 拉丝异常异常异常的webcosket   
 * @author   rb
 * @date 2017年7月23日 下午6:43:09   
 * @company  SMTC   
 */
@ServerEndpoint(value = "/LSExceptionWebSocket", configurator = GetHttpSessionConfigurator.class)
public class LSExceptionWebSocket {
	public String text;
	EhCacheFactory cacheFactory = EhCacheFactory.getInstance();
	Gson gson = new Gson();

	@OnMessage
	public void OnMessage(String message, Session session, EndpointConfig config)
			throws Exception {
		while (session.isOpen()) {
			if (getFlag()) {
				try {
					session.getBasicRemote().sendText(text);// 发送消息到前台
					cacheFactory.removeWebsocketCmdCache(YorkUtil.Memcache_看板_生产_异常);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//throw new Exception("拉丝异常异常异常电子看板八段温度检测表异常的websocket,出现异常");
					throw new Exception("websocket生产异常处理出现错误。"+e.getMessage());
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
		String cache = cacheFactory.getWebsocketCmdCache(YorkUtil.Memcache_看板_生产_异常);
		//String cache = "{\"code\":\"异常编码\",\"macCode\":\"jitaocode\",\"meInfo\":\"异常信息\",\"meTime\":1242544557884,\"axisName\":\"轴名称\",\"exceptionParam\":\"异常参数\",\"exceptionValue\":\"异常值\",\"agentBy\":\"操作人\",\"courseCode\":\"工单编码\"}";
		if (cache != null && cache!="") {
			text = cache;
			cacheFactory.removeWebsocketCmdCache(YorkUtil.Memcache_看板_生产_异常);
			return true;
		}
		return false;
		
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
	}

	@OnClose
	public void onClose() {
		System.out.println("拉丝异常异常异常电子看板的webSocket  Connection closed");
	}

}
