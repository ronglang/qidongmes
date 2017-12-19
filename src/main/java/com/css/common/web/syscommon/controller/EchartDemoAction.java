package com.css.common.web.syscommon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/echartDemo")
public class EchartDemoAction {
	
	/**
	 * 饼状图
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/pie")
	public String toPieChart(HttpServletRequest request){
		return "echartDemo";
	}

}
