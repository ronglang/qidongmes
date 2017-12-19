package com.css.business.web.subsysmanu.mauManage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysmanu.bean.MauParam;
import com.css.business.web.subsysmanu.mauManage.service.MauParamManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;

@Controller
@RequestMapping("/mauParamManageAction")
public class MauParamManageAction extends BaseSpringSupportAction<MauParam, MauParamManageService> {
	@Resource(name= "mauParamManageService")
	private MauParamManageService service;
	@Override
	public MauParamManageService getEntityManager() {
		return service;
	}
	
	
	
	
	

}
