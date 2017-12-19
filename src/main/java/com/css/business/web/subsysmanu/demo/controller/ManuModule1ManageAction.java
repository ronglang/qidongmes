package com.css.business.web.subsysmanu.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysmanu.bean.ManuEntity1;
import com.css.business.web.subsysmanu.demo.service.ManuModule1ManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;

/**
 * @TODO  : demo 
 * @author: 翟春磊
 * @DATE  : 2017年4月12日
 */
@Controller
@RequestMapping("/manuModule1ManageAction")
public class ManuModule1ManageAction  extends BaseSpringSupportAction<ManuEntity1,ManuModule1ManageService>{
	
	private ManuModule1ManageService service;
	
	@Override
	public ManuModule1ManageService getEntityManager() {
		// TODO Auto-generated method stub
		return service;
	}
}
