package com.css.business.web.syswebsoket.DisplayController;

import javax.servlet.http.HttpServlet;

import com.css.business.web.syswebsoket.maudepartwebsocket.ProductDepartmentChartWebSocket;
import com.css.business.web.syswebsoket.maudepartwebsocket.ProductDepartmentPineWebSocket;
import com.css.business.web.syswebsoket.maudepartwebsocket.ProductDepartmentShowPlanWebS;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.EhCacheFactory;

/**
 * @Title: MaudepartController.java
 * @Package com.css.business.web.syswebsoket.DisplayController
 * @Description: 生产部电子看板控制
 * @author rb
 * @date 2017年7月21日 上午9:39:46
 * @company SMTC
 */
@Deprecated
public class MaudepartController /*extends HttpServlet*/ {

	private static final long serialVersionUID = 5199908050379683553L;
	EhCacheFactory cacheFactory = EhCacheFactory.getInstance();
	// websocket
	// 生产部电子看板柱状图页面的websocket
	ProductDepartmentChartWebSocket productDepartmentChartWebSocket = new ProductDepartmentChartWebSocket();
	// 生产部电子看板饼状图页面的websocket
	ProductDepartmentPineWebSocket productDepartmentPineWebSocket = new ProductDepartmentPineWebSocket();
	// /生产部电子看板生产延期情况表格的websocket
	ProductDepartmentShowPlanWebS productDepartmentShowPlanWebS = new ProductDepartmentShowPlanWebS();

	//@Override
	public void init() {
		// 默认非实时表
		String cacheName =null /*YorkUtil.Memcache_看板_生产部*/;
		show(cacheName);
	}

	/**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param cacheName
	 */
	private void show(String cacheName) {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println(cacheName+"电子看板执行器,执行了");
			try {
				String cmdCache = cacheFactory.getWebsocketCmdCache(cacheName);
				if (cmdCache !=null && cmdCache !="") {
					productDepartmentChartWebSocket.flushPage();
					productDepartmentPineWebSocket.flushPage();
				}
				Thread.sleep(60000*5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
