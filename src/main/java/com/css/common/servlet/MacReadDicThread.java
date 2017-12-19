package com.css.common.servlet;

import javax.servlet.http.HttpServlet;

import com.css.business.web.sysManage.service.SysMacDictionaryManageService;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.service.SpringContextHolder;

/**
 * 
 * @author Administrator
 * @description 用于系统启动读取 机台参数的县城
 *
 */
public class MacReadDicThread extends Thread {
	
	public MacReadDicThread(){}
	private SysMacDictionaryManageService service = SpringContextHolder.getBean("sysMacDictionaryManageService");
	private EhCacheFactory factory = EhCacheFactory.getInstance();
	
	@Override
	public void run(){
		readMacDic();
	}

	private void readMacDic() {
		// TODO Auto-generated method stub
		service.getMacDicToEh();
		
	}
}
