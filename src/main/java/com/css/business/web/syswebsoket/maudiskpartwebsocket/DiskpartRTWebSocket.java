package com.css.business.web.syswebsoket.maudiskpartwebsocket;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.css.business.web.subsysplan.bean.PlaMachinePlanMater;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanMaterManageService;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanScheduleManageService;
import com.css.commcon.util.YorkUtil;
import com.css.common.servlet.GetHttpSessionConfigurator;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.service.SpringContextHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @Title: DiskpartRTWebSocket.java
 * @Package com.css.business.web.syswebsoket.maudiskpartwebsocket
 * @Description: 分盘的实时数据
 * @author rb
 * @date 2017年7月19日 下午1:42:25
 * @company SMTC
 */
@ServerEndpoint(value = "/diskpartRTWebSocket", configurator = GetHttpSessionConfigurator.class)
public class DiskpartRTWebSocket {
	public static boolean flag = false;
	public String text;
	Gson gson = new Gson();
	EhCacheFactory cacheFactory = EhCacheFactory.getInstance();

	@OnMessage
	public void OnMessage(String message, Session session, EndpointConfig config)
			throws Exception {
		while (session.isOpen() ) {
			if (getFlag()) {
				session.getBasicRemote().sendText(text);// 发送消息到前台
				flag = false;
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
		String cache = cacheFactory
				.getWebsocketCmdCache(YorkUtil.Memcache_看板_分盘_实时);
		if (cache != null && cache != "") {
			Map<String, String> cacheMap = gson.fromJson(cache,
					new TypeToken<Map<String, String>>() {
					}.getType());
			String machineId = cacheMap.get("machineId");
			PlaMachinePlanScheduleManageService service = SpringContextHolder
					.getBean("plaMachinePlanScheduleManageService");
			//获得本机台当前的生产中的工单 工单编号 ,计划长度,实际生产长度
			List<Object[]> list = service.getTodayList(machineId, "生产中");
			if (list != null && list.size() > 0) {
				Object[] objs = list.get(0);
				String courseCode = objs[0].toString();
				//工单材料详情
				PlaMachinePlanMaterManageService plaMachinePlanMaterManageService = SpringContextHolder.getBean("plaMachinePlanMaterManageService"); 
				PlaMachinePlanMater plaMachinePlanMater = plaMachinePlanMaterManageService.getPlaMachinePlanMaterByCourseCode(courseCode,machineId);
				if (plaMachinePlanMater!=null ) {
					//材料名称 ,规格,数量,单位,轴名称
					cacheMap.put("material", plaMachinePlanMater.getMaterName());
					cacheMap.put("material_type", plaMachinePlanMater.getGgxh());
					cacheMap.put("material_num", plaMachinePlanMater.getAmount().toString());
					cacheMap.put("unit", plaMachinePlanMater.getUnit());
					cacheMap.put("axis_name", plaMachinePlanMater.getAxisName());
				}
				cacheMap.put("courseCode", objs[0].toString());
				cacheMap.put("partLen", objs[1].toString());// 计划长度
				cacheMap.put("semiLen",
						new BigDecimal(objs[3].toString()).toString());// 生产长度

				BigDecimal partLen = new BigDecimal(objs[1].toString());
				BigDecimal proLen = new BigDecimal(objs[2].toString());
				BigDecimal divide = proLen.divide(partLen);
				BigDecimal result = new BigDecimal(Double.toString(100))
						.multiply(divide);
				// 完成百分比
				cacheMap.put(
						"taskPercent",
						result.toString().substring(0,
								result.toString().indexOf(".") + 2));

			}
			text = gson.toJson(cacheMap);
			cacheFactory
			.removeWebsocketCmdCache(YorkUtil.Memcache_看板_分盘_实时);
			return true;
		}
		return false;
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
	}

	@OnClose
	public void onClose() {
		System.out.println("分盘的实时数据的webSocket  Connection closed");
	}

	
}
