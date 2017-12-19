package com.css.business.web.syswebsoket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 绞线电子看板action
 * @author candy
 *
 */
@Controller
@RequestMapping("/lineBoard")
public class LineBoardAction {
	
	@RequestMapping({"toboard"})
	public String toBoard(){
		return "craManage/boardManage/craStrepsinemaKanban";
	}
	
	@RequestMapping({"toChildone"})
	public String toBoardChildOne(){
		return "craManage/boardManage/childPage/strepsinema1";
	}
	
	@RequestMapping({"toChildtwo"})
	public String toBoardChildTwo(){
		return "craManage/boardManage/childPage/strepsinema2";
	}
	
	@RequestMapping({"toCent"})
	public String toCent(){
		return "craManage/boardManage/craCentLineKanban";
	}
	
	@RequestMapping({"toCentOne"})
	public String toCentChildOne(){
		return "craManage/boardManage/childPage/centLine1";
	}
	
	@RequestMapping({"toCentTwo"})
	public String toCentChildTwo(){
		return "craManage/boardManage/childPage/centLine2";
	}
	
	
	@RequestMapping({"toPacketBind"})
	public String toPacketBind(){
		return "craManage/boardManage/packetBind";
	}
	
	@RequestMapping({"toBindOne"})
	public String toBindOne(){
		return "craManage/boardManage/childPage/toBindOne";
	}
	
	
	@RequestMapping({"toBindTwo"})
	public String toBindTwo(){
		return "craManage/boardManage/childPage/toBindTwo";
	}
	
	
	
	
	
	
	
	
	
	
	
}
