package com.css.business.web.syswebsoket.DisplayController;

import javax.servlet.http.HttpServlet;

import com.css.business.web.syswebsoket.storewebsocket.storeCallWebSocket;
import com.css.business.web.syswebsoket.storewebsocket.storeCrewWebS;
import com.css.business.web.syswebsoket.storewebsocket.storeCrewWebSocket;
import com.css.business.web.syswebsoket.storewebsocket.storeCrewsWebSocket;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.EhCacheFactory;

/**
 * @Title: DisplayEntrepotController.java
 * @Package com.css.business.web.syswebsoket.DisplayController
 * @Description: 仓库电子看板的展示的控制类
 * @author rb
 * @date 2017年7月19日 上午8:59:37
 * @company SMTC
 */
@Deprecated
public class DisplayEntrepotController /*extends HttpServlet*/ {

	private static final long serialVersionUID = -292360512264917335L;
	EhCacheFactory cacheFactory = EhCacheFactory.getInstance();
	// websocket
	// 警告消息 缺料
	storeCallWebSocket callWebSocket = new storeCallWebSocket();
	// 报废材料的websocket
	storeCrewsWebSocket crewsWebSocket = new storeCrewsWebSocket();
	// 总库存信息的websocket
	storeCrewWebSocket crewWebSocket = new storeCrewWebSocket();
	// 出入库列表
	storeCrewWebS crewWebS = new storeCrewWebS();

//	@Override
	public void init() {
		// 默认非实时表
		String cacheName=null /*= YorkUtil.Memcache_看板_仓库*/;
		//show(cacheName);
	}

	/**
	 * @Description: 查询缓存,判断当前需要的操作
	 * @param cacheName
	 */
	public void show(String cacheName) {
		/*while (true) {
			try {
				System.out.println(cacheName+"电子看板执行器,执行了");
				// 从缓存中取数据
				String cmdCache = cacheFactory.getWebsocketCmdCache(cacheName);
				if (cmdCache != null && cmdCache != "") {
					if ("入库".equals(cmdCache)) {
						crewWebS.flushpage();
						crewWebSocket.flushPage();
					} else if ("出库".equals(cmdCache)) {
						crewWebS.flushpage();
						crewWebSocket.flushPage();
					} else if ("报废".equals(cmdCache)) {
						crewWebSocket.flushPage();
						crewsWebSocket.flushPage();
					} else if ("缺料".equals(cmdCache)) {
						callWebSocket.flushPage();
					}
					// 删除当前的缓存
					cacheFactory.removeWebsocketCmdCache(cacheName);
				}
				Thread.sleep(60000*5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
	
}
