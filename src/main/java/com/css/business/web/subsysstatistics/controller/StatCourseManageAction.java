package com.css.business.web.subsysstatistics.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysplan.bean.PlaProductOrder;
import com.css.business.web.subsysstatistics.bean.StatCourse;
import com.css.business.web.subsysstatistics.service.StatCourseManageService;
import com.css.business.web.subsysstatistics.service.StatOrderManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/statCourseManageAction")
public class StatCourseManageAction extends BaseSpringSupportAction< StatCourse, StatCourseManageService> {
	@Resource(name="statCourseManageService")
	private StatCourseManageService service;

	/* 
	 * @return
	 */
	@Override
	public StatCourseManageService getEntityManager() {
		// TODO Auto-generated method stub
		return service;
	}
	
	
	
	

}
