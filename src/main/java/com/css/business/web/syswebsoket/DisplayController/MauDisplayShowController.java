package com.css.business.web.syswebsoket.DisplayController;


/**
 * @Title: MauDisplayShowController.java
 * @Package com.css.business.web.syswebsoket
 * @Description: 车间电子看板刷新控制
 * @author rb
 * @date 2017年7月17日 下午2:53:24
 * @company SMTC
 */
@Deprecated
public class MauDisplayShowController /*extends HttpServlet*/ {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 *//*
	private static final long serialVersionUID = 1644075951148916485L;
	Gson gson = new Gson();
	EhCacheFactory cacheFactory = EhCacheFactory.getInstance();
	// /车间电子看板的机台信息的websocket
	CenCrewWebS cenCrewWebS = new CenCrewWebS();
	// 车间电子看板的生产异常故障的的websocket
	CenExceptionWebS cenExceptionWebS = new CenExceptionWebS();
	// 今日计划
	CenTodayPlanWebS cenTodayPlanWebS = new CenTodayPlanWebS();
	// 叉车
	CenForkliftWebS cenForkliftWebS = new CenForkliftWebS();

	//@Override
	public void init() {
		// 默认非实时表
		String cacheName =null YorkUtil.Memcache_看板_生产;
		show(cacheName);
	}

	*//**
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 *//*
	public void show(String cacheName) {
		System.out.println(cacheName+"电子看板执行器,执行了");
		try {
			while (true) {
				// 从缓存中获取对应的cache
				String cmdCache = cacheFactory.getWebsocketCmdCache(cacheName);
				if (cmdCache != null && cmdCache != "") {
					// 如果不为空
					cenTodayPlanWebS.flushPage();
					cenCrewWebS.flushPage();
					cacheFactory.removeWebsocketCmdCache(cacheName);
					Thread.sleep(60000 * 5);
				}

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
