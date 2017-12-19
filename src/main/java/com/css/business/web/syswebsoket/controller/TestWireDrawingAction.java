package com.css.business.web.syswebsoket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**     
 * @Title: TestWireDrawing.java   
 * @Package com.css.business.web.syswebsoket   
 * @Description: 作为拉丝工序的测试url
 * @author   zb
 * @date 2017年7月10日 下午1:36:52   
 * @company  SMTC   
 */
@Controller
@RequestMapping("/testWireDrawingAction")
public class TestWireDrawingAction {
	@RequestMapping("toListPage")
	public String toListPage(){
		return "mauManage/mauWireDrawingEBorad";
	}
	@RequestMapping("toPShow")
	public String toPShow(){
		return "mauManage/mauWDEBParamsShow";
	}
	@RequestMapping("toHostSpeedTest")
	public String toHostSpeedTest(){
		return "mauManage/mauWDEBHostSpeedTesting";
	}
	@RequestMapping("toMProgress")
	public String toMProgress(){
		return "mauManage/mauWDEBMachineProgress";
	}
	
	
	@RequestMapping("toListPageTwo")
	public String toListPageTwo(){
		return "craManage/boardManage/mauWireDrawing";
	}
	
	
	@RequestMapping("toWireDrawingOne")
	public String toWireDrawingOne(){
		return "craManage/boardManage/childPage/wireDrawingOne";
	}
	@RequestMapping("toWireDrawingTwo")
	public String toWireDrawingTwo(){
		return "craManage/boardManage/childPage/wireDrawingTwo";
	}
	
	
	
	
	
	
	
}