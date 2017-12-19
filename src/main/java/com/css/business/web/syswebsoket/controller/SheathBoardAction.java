package com.css.business.web.syswebsoket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 挤护套工序看板action
 * @author candy
 *
 */
@Controller
@RequestMapping("/sheathboard")
public class SheathBoardAction {
	
	@RequestMapping({"tosheath"})
	public String toSheathIndex(){
		
		return "craManage/boardManage/craSheathKanban";
	}
	
	@RequestMapping({"sheathone"})
	public String toSheathOne(){
		
		return "craManage/boardManage/childPage/sheath1";
	}
	
	@RequestMapping({"sheathtwo"})
	public String toSheathTwo(){
		
		return "craManage/boardManage/childPage/sheath2";
	}
	
	
}
