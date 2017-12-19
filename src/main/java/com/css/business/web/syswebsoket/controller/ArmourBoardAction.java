package com.css.business.web.syswebsoket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 铠装工序电子看板action
 * @author candy
 *
 */
@Controller
@RequestMapping("/armourboard")
public class ArmourBoardAction {
	
	@RequestMapping({"toarmour"})
	public String toArmour(){
		
		return "craManage/boardManage/craArmourKanban";
	}
	
	@RequestMapping({"armourone"})
	public String toArmourOne(){
		
		return "craManage/boardManage/childPage/armour1";
	}
	
	@RequestMapping({"armourtwo"})
	public String toArmourTwo(){
		
		return "craManage/boardManage/childPage/armour2";
	}
	
	
}
