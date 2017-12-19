
package com.css.business.web.syswebsoket.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysmanu.bean.MauCallForkliftRecord;
import com.css.business.web.subsysmanu.mauManage.service.MauCallForkliftRecordManageService;

/**     
 * @Title: MauDisplayAction.java   
 * @Package com.css.business.web.syswebsoket.controller   
 * @Description: 生产电子看板页面跳转 
 * @author   rb
 * @date 2017年7月17日 下午2:12:51   
 * @company  SMTC   
 */
@Controller
@RequestMapping("mauDisplayAction")
public class MauDisplayAction {
	/*
	/**
	 * 
	 * @Description: 生产电子看板 
	 * @return
	 */
//	@RequestMapping("toMauDisplayList")
//	public String toMauDisplayList(){
//		return "mauManage/cendisplay/cendisplayList";
//	}
	
	/**
	 * @Description: 车间工单生产状态  
	 * @return
	 */
//	@RequestMapping("toCenMauDisplayWork")
//	public String toCenMauDisplayWork(){
//		return "mauManage/cendisplay/workOrderTable";
//	}
	/**
	 * 
	 * @Description: 去生产电子看板的呼叫叉车记录
	 * @return
	 */
	@RequestMapping("toMauDisplayCall")
	public String toCenDisplay(){
		return "mauManage/cendisplay/callforklift";
	}
	/**
	 * 
	 * @Description: 生产电子看板的机台情况页面   
	 * @return
	 */
//	@RequestMapping("toMauDisplayCrew")
//	public String toMauDisplayCrew(){
//		return "mauManage/cendisplay/crewcondition";
//	}
	/**
	 * 
	 * @Description: 去生产电子看板的机台故障   
	 * @return
	 */
	@RequestMapping("toMauDisplayMachineError")
	public String toMauDisplayMachineError(){
		return "mauManage/cendisplay/boradErrorTable";
	}
	
	/**
	 * 
	 * @Description: 去生产电子看板的生产异常   
	 * @return
	 */
//	@RequestMapping("toMauDisplayMauException")
//	public String toMauDisplayMauException(){
//		return "mauManage/cendisplay/mauExceptionTable";
//	}
	
	/**
	 * 
	 * @Description: 去工序列表
	 * @return
	 */
	@RequestMapping("toProcessList")
	public String toProcessList(){
		return "mauManage/cendisplay/processList";
	}
	
	
	
	
	
	/***********************************TG****************************/
	@Resource(name="mauCallForkliftRecordManageService")
	private MauCallForkliftRecordManageService services;
	
	//去车间电子看板页面
	@RequestMapping("toMauDisplayList")
	public String toMauDisplayList(){
		return "mauManage/cendisplay/mauDisplayList";
	}
	@RequestMapping("mauDisplayOne")
	public String mauDisplayOne(){
		return "mauManage/cendisplay/toMauDisplayListNew";
	}
	@RequestMapping("mauDisplayTwo")
	public String mauDisplayTwo(HttpServletRequest req,String datas){
		req.setAttribute("datas", datas);
		return "mauManage/cendisplay/toMauDisplayListTwo";
	}
	
	//显示机台状态数的websocket
	@RequestMapping({"toDisplayMachineNum"})
	public String toDisplayMachineNum(){
		return "mauManage/cendisplay/childpage/displayMachineNum";
	}
	
	//显示工单生产进度图
	@RequestMapping("toMauDisplayProductSchedule")
	public String toMauDisplayCrew(){
		return "mauManage/cendisplay/childpage/displayProductSchedule";
	}
	
	//显示工单不合格轴数
	@RequestMapping("toMauDisplayError")
	public String toCenMauDisplayWork(){
		return "mauManage/cendisplay/childpage/displayError";
	}
	
	//显示工单异常轴数
	@RequestMapping("toMauDisplayException")
	public String toMauDisplayMauException(){
		return "mauManage/cendisplay/childpage/displayException";
	}
	
	
	
	
	
	
	
	
}
