package com.css.business.web.subsysstatistics.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysplan.bean.PlaProductOrder;
import com.css.business.web.subsysstatistics.service.StatOrderManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/statOrderManageAction")
public class StatOrderManageAction extends BaseSpringSupportAction<PlaProductOrder, StatOrderManageService> {
	@Resource(name="statOrderManageService")
	private StatOrderManageService service;

	/* 
	 * @return
	 */
	@Override
	public StatOrderManageService getEntityManager() {
		// TODO Auto-generated method stub
		return service;
	}
	
	
	
	
	

}
