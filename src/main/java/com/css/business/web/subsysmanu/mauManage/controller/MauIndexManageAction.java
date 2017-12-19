package com.css.business.web.subsysmanu.mauManage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.mauManage.service.MauMachineManageService;
import com.css.business.web.subsysplan.plaManage.bean.PlaMachineDisplayVo;
import com.google.gson.Gson;

@Controller
@RequestMapping("/mauIndexManageAction")
public class MauIndexManageAction {
	
	@Autowired
	private MauMachineManageService service;
	
	private Gson gson = new Gson();
	
	//去首页面，暂时停用
	@RequestMapping("indexMain")
	private String toIndexMain(){
		return "indexMain";
	}
	
	//去工单任务完成情况页面图
	@RequestMapping("workOrder")
	public String toWorkOrderTask(){
		return "indexWorkOrder";
	}
	
	//去机台状态数量图
	@RequestMapping("machineStatusNum")
	public String toMachineStatusNum(){
		return "indexMachineStatusNum";
	}
	
	//去合同到期情况页面
	@RequestMapping("contractTime")
	public String contractTime(){
		return "indexContractTime";
	}
	
	//获取机台状态数量
	@RequestMapping("getMacStatusNum")
	@ResponseBody
	public String getMacStatusNum(){
		List<Object[]> list = service.getMacStateNum();
		List<String> X  = new ArrayList<>();
		List<String> Y = new ArrayList<>();
		PlaMachineDisplayVo vo = new PlaMachineDisplayVo();
		if (list!=null && list.size()>0) {
			for (Object obj[] :  list) {
				String s = obj[0].toString();
				String s2 = obj[1].toString();
				X.add(s);
				Y.add(s2);
			}
		}
		vo.setDataList(Y);
		vo.setAreas(X);
		return gson.toJson(vo);
	}
	
	
	
	
	
	
}
