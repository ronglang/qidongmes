
package com.css.business.web.syswebsoket.quaAsk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.css.business.web.subsysquality.bean.QualityAsk;
import com.css.business.web.subsysquality.bean.QualityMaterReport;
import com.css.business.web.subsysquality.bean.QualityMauReport;
import com.css.business.web.subsysquality.quaManage.service.QualityAskManageService;
import com.css.business.web.subsysquality.quaManage.service.QualityMaterReportManageService;
import com.css.business.web.subsysquality.quaManage.service.QualityMauReportManageService;
import com.css.business.web.syswebsoket.bean.QuaAskVo;
import com.css.commcon.util.YorkUtil;
import com.css.common.servlet.GetHttpSessionConfigurator;
import com.css.common.util.DateUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.service.SpringContextHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**     
 * @Title: QuaAskWebSocket.java   
 * @Package com.css.business.web.syswebsoket.quaAsk   
 * @Description: 质检呼叫websocket   
 * @author   rb
 * @date 2017年9月5日 下午2:22:31   
 * @company  SMTC   
 */
@ServerEndpoint(value = "/quaAskWebSocket", configurator = GetHttpSessionConfigurator.class)
public class QuaAskWebSocket {

//	private QualityAskManageService askService = SpringContextHolder.getBean("qualityAskManageService");
//	private QualityMaterReportManageService materService = SpringContextHolder.getBean("qualityMaterReportManageService");
//	private QualityMauReportManageService mauService = SpringContextHolder.getBean("qualityMauReportManageService");
	private Gson gson = new Gson();
	private EhCacheFactory cacheFactory = EhCacheFactory.getInstance();
	public String text = null;
	@OnMessage
	public void OnMessage(String message, Session session, EndpointConfig config)
			throws Exception {
		while (session.isOpen()) {
			if ( getFlag()) {
				try {
					session.getBasicRemote().sendText(text);// 发送消息到前台
				} catch (Exception e) {
					// TODO Auto-generated catch block
					throw new Exception("质检呼叫的websocket,出现异常");
				}
			}
			Thread.sleep(6000);
		}
	}
	/**   
	 * @Description: 查询未处理的质检呼叫   
	 * @return         
	 */ 
	private boolean getFlag() {
		// TODO Auto-generated method stub
		//List<QualityAsk>askList = askService.getNoFinish();
		/*if(askList != null){
			return gson.toJson(askList).toString();
		}*/
		/*List<QuaAskVo> askList = new ArrayList<>();
		List<QualityMaterReport>materList = materService.getListByState(QualityMaterReport.STATE_NO_START);
		if (materList != null && materList.size() > 0) {
			for (QualityMaterReport mater : materList) {
				QuaAskVo vo = new QuaAskVo();
				vo.setCreateBy(mater.getCreateBy());
				Date createDate = mater.getCreateDate();
				String format = DateUtil.format(createDate, "yyyy-MM-dd HH:mm:ss");
				vo.setCreateDate(format);
				vo.setReportCode(mater.getReportCode());
				vo.setTestType("来料质检");
				askList.add(vo);
			}
		}
		List<QualityMauReport>mauList = mauService.getListByState(QualityMauReport.STATE_NO_TEST);
		if (mauList != null && mauList.size() > 0) {
			for (QualityMauReport mau : mauList) {
				QuaAskVo vo = new QuaAskVo();
				vo.setCreateBy(mau.getCreateBy());
				Date createDate = mau.getCreateDate();
				String format = DateUtil.format(createDate, "yyyy-MM-dd HH:mm:ss");
				vo.setCreateDate(format);
				vo.setReportCode(mau.getReportCode());
				vo.setTestType(mau.getTestType());
				askList.add(vo);
			}
		}*/
		String cmdCache = cacheFactory.getWebsocketCmdCache(YorkUtil.Memcache_质检呼叫);
		if (cmdCache != null) {
			text = cmdCache;
			return true;
		}
		return false;
	}
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
	}

	@OnClose
	public void onClose() {
		System.out.println("质检呼叫的webSocket  Connection closed");
	}
}
