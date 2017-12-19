package com.css.common.servlet;

import java.util.List;

import javax.servlet.http.HttpServlet;

import com.css.business.web.sysManage.bean.SysNotice;
import com.css.business.web.sysManage.service.SysNoticeManageService;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.service.SpringContextHolder;
import com.google.gson.Gson;

/**
 * 
* @ClassName: StartEhcacheServlet 
* @Description: 重启后,加载需要的东西到缓存
* @author RB
* @date 2017年12月15日 上午10:56:06 
*
 */
public class StartEhcacheServlet extends HttpServlet{

	private static final long serialVersionUID = -8282096889430156555L;
	private SysNoticeManageService snService = SpringContextHolder.getBean("sysNoticeManageService");
	private EhCacheFactory ecFactory = EhCacheFactory.getInstance();
	private Gson gson = new Gson();
	@Override
	public void init(){
		saveSysNotice();
	}
	
	/**
	 * 加载欢迎致辞
	 */
	private void saveSysNotice(){
		List<SysNotice>list = snService.getNoticesWebSocket("是",YorkUtil.Memcache_车间致辞);
		ecFactory.addWebsocketCmdCache(YorkUtil.Memcache_车间致辞, gson.toJson(list));
	}

}
