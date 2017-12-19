
package com.css.business.web.syswebsoket.storewebsocket;

import java.util.List;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.css.business.web.subsysstore.storeManage.service.StoreMrecordManageService;
import com.css.business.web.subsysstore.storeManage.service.StoreScrapRecordManageService;
import com.css.commcon.util.YorkUtil;
import com.css.common.servlet.GetHttpSessionConfigurator;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.service.SpringContextHolder;
import com.google.gson.Gson;

/**     
 * @Title: storeNumWebsocket.java   
 * @Package com.css.business.web.syswebsoket.storewebsocket   
 * @Description: 仓库电子看板的库存归类的 和报废分类的websocket     
 * @author   rb
 * @date 2017年9月26日 上午10:04:20   
 * @company  SMTC   
 */
@ServerEndpoint(value = "/storeNumWebsocket", configurator = GetHttpSessionConfigurator.class)
public class StoreNumWebsocket {
	
	// 刷新条件判断
	private boolean flag = false;
	EhCacheFactory cacheFactory = EhCacheFactory.getInstance();
	private Gson gson = new Gson();
	public String text ;
	@OnMessage
	public void OnMessage(String message, Session session, EndpointConfig config)
			throws Exception {
		while (session.isOpen()) {
			if (getfFlag()) {
				try {
					session.getBasicRemote().sendText(text);// 发送消息到前台
				} catch (Exception e) {
					throw new Exception("异常的websocket,出现异常");
				}
			}
			Thread.sleep(6000);
		}
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return         
	 */ 
	private boolean getfFlag() {
		// TODO Auto-generated method stub
		String cmdCache = cacheFactory.getWebsocketCmdCache(YorkUtil.Memcache_看板_仓库_统计);
		if (cmdCache!=null && cmdCache!="") {
			
			//原材料
			StoreMrecordManageService smService = SpringContextHolder.getBean("storeMrecordManageService");
			List<Map<String,String>> stList = smService.getClassify("原材料");
			//报废料			
			StoreScrapRecordManageService srService = SpringContextHolder.getBean("storeScrapRecordManageService");
			List<Map<String,String>> srList = srService.getClassify("报废");
			if (srList !=null  && srList.size() > 0) {
				stList.addAll(srList);
			}
			text = gson.toJson(stList);
			cacheFactory.removeWebsocketCmdCache(YorkUtil.Memcache_看板_仓库_统计);
			return true;
		}
		return false;
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
	}

	@OnClose
	public void onClose() {
		System.out.println("仓库信息的webSocket  Connection closed");
	}
}
