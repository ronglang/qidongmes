/**     
*
*/   
package com.css.business.web.syswebsoket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**     
 * @Title: TestController.java   
 * @Package com.css.business.web.syswebsoket   
 * @Description: 作为电子看板的测试url
 * @author   rb
 * @date 2017年7月10日 下午1:36:52   
 * @company  SMTC   
 */
@Controller
@RequestMapping("testController")
public class TestController {
	@RequestMapping("toAllList")
	public String toAllList(){
		return "mauManage/cendisplay/cendisplayList";
	}
	
	@RequestMapping("toCrew")
	public String toCrew(){
		return "mauManage/cendisplay/crewcondition";
	}
	
	@RequestMapping("toBordError")
	public String toBordError(){
		return "mauManage/cendisplay/boradErrorTable";
	}
	@RequestMapping("toCallforklift")
	public String toCallforklift(){
		return "mauManage/cendisplay/callforklift";
	}
	@RequestMapping("toWorkOrder")
	public String toWorkOrder(){
		return "mauManage/cendisplay/workOrderTable";
	}
}
