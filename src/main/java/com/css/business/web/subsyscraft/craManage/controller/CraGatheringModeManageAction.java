package com.css.business.web.subsyscraft.craManage.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.util.JSONUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.bean.CraGatheringMode;
import com.css.business.web.subsyscraft.craManage.service.CraGatheringModeManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
/**
 * 集绞方式控制类
 * @author Administrator
 *
 */
@RequestMapping({"craGatheringModeManageAction"})
@Controller
public class CraGatheringModeManageAction extends BaseSpringSupportAction<CraGatheringMode, CraGatheringModeManageService>{

	@Autowired
	private CraGatheringModeManageService craGatheringModeManageService;
	
	
	@Override
	public CraGatheringModeManageService getEntityManager() {
		// TODO Auto-generated method stub
		return craGatheringModeManageService;
	}

	/**
	 * 集绞材料编辑页面，需要输入材料
	 * @return 
	 */
	@RequestMapping({"getCraGatheringModeMaterial"})
	public String getCraGatheringModeMaterial(HttpServletRequest request,HttpServletResponse response){
		String relationId = request.getParameter("relationId");
		request.setAttribute("relationId",relationId);
		
		
		return "craManage/craSetGround";
	}
	
	@RequestMapping({"dataGrid"})
	@ResponseBody
	public Page dataGrid(HttpServletRequest request,HttpServletResponse response,Page page){
		String relationId = request.getParameter("relationId");
		System.out.println("集绞------------------------------------------------"+relationId);
		List<Object> list = new ArrayList<Object>();
		CraGatheringMode bean = null;
		for(int i = 0;i < 10;i++){
			bean = new CraGatheringMode();
			bean.setAmount(i);
			bean.setProGgxh("规格型号");
			bean.setCreateBy("创建人");
			bean.setCreateDate(new Timestamp(System.currentTimeMillis()));
			bean.setGatheringMode("3*150+2*120");
			bean.setProColor("颜色");
			bean.setProCraftCode("产品工艺编码");
			list.add(bean);
		}
		
		List<CraGatheringMode> dataList = craGatheringModeManageService.getJiJiao(relationId);
		list.clear();
		list.addAll(dataList);
		page.setData(list);
		return page;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping({"saveOrUpdateList"})
	@ResponseBody
	public HashMap<String,Object> saveOrUpdateList (HttpServletRequest request,HttpServletResponse response){
		String jsonDataList = request.getParameter("data");
		//转成对象之后，保存相应的数据
		List<CraGatheringMode> list = null;
		JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd HH:mm:ss" })); 
		JSONArray jsonArray = JSONArray.fromObject(jsonDataList);//把String转换为json 
		list = JSONArray.toList(jsonArray,CraGatheringMode.class);
		craGatheringModeManageService.getEntityDaoInf().saveOrUpdateBatch(list);
		return JsonWrapper.successWrapper("保存成功");
	}
}
