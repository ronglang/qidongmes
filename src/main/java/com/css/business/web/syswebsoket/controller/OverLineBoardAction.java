package com.css.business.web.syswebsoket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 成缆电子看板action
 * @author candy
 *
 */

@Controller
@RequestMapping("/overLine")
public class OverLineBoardAction {
	
	@RequestMapping({"toindex"})
	public String toOverLinePage(){
		
		
		return "craManage/boardManage/craOverLineKanban";
	}
	
	@RequestMapping({"toLineOne"})
	public String toLineOne(){
		
		return "craManage/boardManage/childPage/overline1";
	}
	
	@RequestMapping({"toLineTwo"})
	public String toLineTwo(){
		
		return "craManage/boardManage/childPage/overline2";
	}
	
	
}
