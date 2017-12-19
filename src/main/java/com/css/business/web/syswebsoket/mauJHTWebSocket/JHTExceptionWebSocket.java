
package com.css.business.web.syswebsoket.mauJHTWebSocket;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.css.business.web.subsysmanu.bean.MauException;
import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.mauManage.service.MauExceptionManageService;
import com.css.business.web.subsysmanu.mauManage.service.MauMachineManageService;
import com.css.commcon.util.YorkUtil;
import com.css.common.servlet.GetHttpSessionConfigurator;
import com.css.common.util.DateUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.service.SpringContextHolder;
import com.google.gson.Gson;

/**     
 * @Title: CLExceptionWebSocket.java   
 * @Package com.css.business.web.syswebsoket.mauCLWebSocket   
 * @Description: 挤护套异常的webcosket   
 * @author   rb
 * @date 2017年7月23日 下午6:43:09   
 * @company  SMTC   
 */
@ServerEndpoint(value = "/JHTExceptionWebSocket", configurator = GetHttpSessionConfigurator.class)
public class JHTExceptionWebSocket {
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
					cacheFactory.removeWebsocketCmdCache(YorkUtil.Memcache_看板_车间_异常_挤护套);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new Exception("绞线电子看板八段温度检测表异常的websocket,出现异常");
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
		String eId = cacheFactory.getWebsocketCmdCache(YorkUtil.Memcache_看板_车间_异常_挤护套);
		if (eId != null && eId!="") {
			MauExceptionManageService service = SpringContextHolder.getBean("mauExceptionManageService");
			MauException mauException = service.get(eId);
			if (mauException !=null ) {
//				text = gson.toJson(mauException);
				Map<String, String>map = new HashMap<String, String>();
				map.put("meTime", DateUtil.format(mauException.getMeTime(),"yyyy-MM-dd HH:mm:ss"));
				map.put("meInfo ", mauException.getMeInfo());
				map.put("axisName", mauException.getAxisName());
				map.put("state", mauException.getState());
				MauMachineManageService macService = SpringContextHolder.getBean("mauMachineManageService");
				MauMachine mauMachine = macService.queryMachineByCode(mauException.getMacCode());
				if (mauMachine !=null) {
					map.put("machineName", mauMachine.getMacName());
				}
				text = gson.toJson(map);
				return true;
			}
		}
		return false;
		
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
	}

	@OnClose
	public void onClose() {
		System.out.println("绞线电子看板八段温度检测表的webSocket  Connection closed");
	}

}
