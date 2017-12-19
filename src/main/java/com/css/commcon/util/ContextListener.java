package com.css.commcon.util;

import javax.annotation.Resource;

import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanManageService;


public class ContextListener{

	@Resource(name="plaMachinePlanManageService")
	private PlaMachinePlanManageService macService;
	
	
	public ContextListener() {  
		
    }  
	
	
	public void plaContext(){
		//  执行定时任务
		try {
			macService.reSetPlanTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
