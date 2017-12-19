package com.css.business.web.subsyscraft.craManage.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.craManage.service.ProcessModuleManageService;
import com.css.business.web.subsyscraft.vo.VOBean;

@Controller
@RequestMapping("/processModuleManageAction")
public class ProcessModuleManageAction {

	@Autowired
	private ProcessModuleManageService processModuleManageService;
	
	@RequestMapping({ "test" })
	@ResponseBody
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		return "craManage/craCmaterEdit";
	}
	/**
	 * 
	 * 工艺参数树和grid
	 * @return
	 */
	@RequestMapping({ "processModuleIndex" })
	public String toIndex(){
		return "craManage/processModule/processModule";
	}
	/**
	 * 工艺材料树和grid
	 * @return
	 */
	@RequestMapping({"processModuleIndexMaterial"})
	public String toIndexPage(){
		return "craManage/processModule/processModuleMaterial";
	}
	/**
	 * 集绞page
	 * @return
	 */
	@RequestMapping({"setGround"})
	public String toJiJiaoPage(){
		return "craManage/processModule/setGround";
	}
	
	
	@RequestMapping({ "toTree" })
	@ResponseBody
	public List<VOBean> toTree(HttpServletRequest request){
		
		List<VOBean> list = new ArrayList<VOBean>();
		list.clear();
		list = processModuleManageService.getVoTreeList();
		return list;
	}
}
