package com.css.business.web.subsysmanu.mauManage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysmanu.bean.MauMachineEmployee;
import com.css.business.web.subsysmanu.mauManage.service.MauMachineEmployeeManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;

@Controller
@RequestMapping("/mauMachineEmployeeManageAction")
public class MauMachineEmployeeManageAction extends BaseSpringSupportAction<MauMachineEmployee, MauMachineEmployeeManageService> {

	private MauMachineEmployeeManageService service;
	

	@Override
	public MauMachineEmployeeManageService getEntityManager() {
		return service;
	}

	public MauMachineEmployeeManageService getService() {
		return service;
	}

	@Resource(name = "mauMachineEmployeeManageService")
	public void setService(MauMachineEmployeeManageService service) {
		this.service = service;
	}
	
	
	
}
