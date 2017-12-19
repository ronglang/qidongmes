package com.css.business.web.syswebsoket.mauJHTWebSocket;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.css.business.web.subsysplan.plaManage.bean.PlaMachineDisplayVo;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanScheduleManageService;
import com.css.commcon.util.YorkUtil;
import com.css.common.servlet.GetHttpSessionConfigurator;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.service.SpringContextHolder;
import com.google.gson.Gson;

/**
 * @Title: JHTTaskWebSocket.java
 * @Package com.css.business.web.syswebsoket.mauJHTWebSocket
 * @Description: 挤护套任务进度的websocket
 * @author rb
 * @date 2017年7月22日 上午11:11:21
 * @company SMTC
 */
@ServerEndpoint(value = "/JHTTaskWebSocket", configurator = GetHttpSessionConfigurator.class)
public class JHTTaskWebSocket {
	// 刷新条件判断
	public static boolean flag = false;
	EhCacheFactory cacheFactory = EhCacheFactory.getInstance();
	Gson gson = new Gson();
	public String text;

	@OnMessage
	public void OnMessage(String message, Session session, EndpointConfig config)
			throws Exception {
		while (session.isOpen()) {
			if (getFlag()) {
				try {
					String json = getTaskByMachineId(text);
					session.getBasicRemote().sendText(json);// 发送消息到前台
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new Exception("挤护套电子看板当前工单任务完成情况异常的websocket,出现异常");
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
		String machineId = cacheFactory
				.getWebsocketCmdCache(YorkUtil.Memcache_看板_挤护套_任务);
		if (machineId != null && machineId != "") {
			text = machineId;
			cacheFactory.removeWebsocketCmdCache(YorkUtil.Memcache_看板_挤护套_任务);
			return true;
		}
		return false;
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
	}

	@OnClose
	public void onClose() {
		System.out.println("挤护套电子看板当前工单任务完成情况的webSocket  Connection closed");
	}

	public String getTaskByMachineId(String machineId) {
		PlaMachinePlanScheduleManageService service = SpringContextHolder
				.getBean("plaMachinePlanScheduleManageService");
		PlaMachineDisplayVo vo = service.queryTaskCompByMachineId(machineId);
		if (vo != null) {
			return gson.toJson(vo);
		}
		return null;
	}

}
