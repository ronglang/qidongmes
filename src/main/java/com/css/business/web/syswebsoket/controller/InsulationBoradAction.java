package com.css.business.web.syswebsoket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 绝缘工序电子看板action
 * @author candy
 *
 */

@Controller
@RequestMapping("/insulation")
public class InsulationBoradAction {
	
	@RequestMapping({"toindex"})
	public String toInsulation(){
		
		
		return "craManage/boardManage/craInsulationKanban";
	}
	
	@RequestMapping({"tochildone"})
	public String toInsulationOne(){
		
		
		return "craManage/boardManage/childPage/insulation1";
	}
	
	@RequestMapping({"tochildtwo"})
	public String toInsulationTwo(){
		
		
		return "craManage/boardManage/childPage/insulation2";
	}
	
	
}
