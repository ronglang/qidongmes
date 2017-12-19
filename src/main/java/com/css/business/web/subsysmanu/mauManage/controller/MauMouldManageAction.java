package com.css.business.web.subsysmanu.mauManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauMould;
import com.css.business.web.subsysmanu.mauManage.service.MauMouldManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

/**
 * @Title: MauMouldManageAction.java
 * @Package com.css.business.web.subsysmanu.mauManage.controller
 * @Description: 模具管理action
 * @author TG
 * @date 2017年9月08日 上午11：24
 * @company SMTC
 */
@Controller
@RequestMapping("/mauMouldManageAction")
public class MauMouldManageAction extends BaseSpringSupportAction<MauMould, MauMouldManageService>  {
	@Autowired
	private MauMouldManageService service;
	@Override
	public MauMouldManageService getEntityManager() {
		return service;
	}
	
	/**
	 * 去模具管理页面
	 * @return
	 */
	@RequestMapping({"toMouldList"})
	public String toMouldList(){
		return "mauManage/maufault/mauMouldList";
	}
	
	private Gson gson = new Gson();
	
	/**
	 * 获取模具基础信息列表，附带查询分页
	 * @param page
	 * @param param
	 * @return
	 */
	@RequestMapping({"getMouldList"})
	@ResponseBody
	public Page getMouldList(Page page,String param){
		@SuppressWarnings("serial")
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Page queryPage = service.getMouldListService(page,map);
		
		return queryPage;
	}
	
	/**
	 * 获取模具基础信息的状态
	 * @return
	 */
	@RequestMapping({"getMouldStatus"})
	@ResponseBody
	public List<String> getMouldStatus(){
		return service.getMouldStatusService();
	}
	
	/**
	 * 添加模具基础信息
	 * @param param
	 * @return
	 */
	@RequestMapping({"saveData"})
	@ResponseBody
	public Map<String,String> saveData(HttpServletRequest request,String param){
		@SuppressWarnings("serial")
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Map<String,String> maps = service.saveDataService(request, map);
		return maps;
	}
	
	
	
	/**
	 * 添加维修计划
	 * @param param
	 * @return
	 */
	@RequestMapping({"saveMaintainData"})
	@ResponseBody
	public Map<String,String> saveMaintainData(HttpServletRequest request,String param){
		@SuppressWarnings("serial")
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Map<String,String> maps = new HashMap<String,String>();
		try{
			boolean flag = service.saveMaintainDataService(request, map);
			if(flag){
				maps.put("success", "操作成功！");
			}else{
				maps.put("error", "操作失败！");
			}
			
		}catch(Exception e){
			e.printStackTrace();
			maps.put("error", "操作失败！");
		}
		return maps;
	}
	
	/**
	 * 保存完成维修数据
	 * @param param
	 * @return
	 */
	@RequestMapping({"saveMaintainOverData"})
	@ResponseBody
	public Map<String,String> saveMaintainOverData(String param){
		@SuppressWarnings("serial")
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Map<String,String> maps = new HashMap<String,String>();
		try{
			service.overMaintainDataService(map);
			maps.put("success", "操作成功！");
		}catch(Exception e){
			e.printStackTrace();
			maps.put("error", "操作失败！");
		}
		return maps;
	}
	
	/**
	 * 保存修改后的值
	 * @param param
	 * @return
	 */
	@RequestMapping({"updateSaveData"})
	@ResponseBody
	public Map<String,String> updateSaveData(String param){
		@SuppressWarnings("serial")
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		return service.updateSaveDataService(map);
	}
	
	/**
	 * 删除模具基础数据
	 * @param param
	 * @return
	 */
	@RequestMapping({"clearData"})
	@ResponseBody
	public Map<String,String> clearData(String param){

		Map<String,String> maps = new HashMap<>();
		try {
			service.clearDataService(param);
			maps.put("success", "删除成功！");
		}catch(Exception e){
			maps.put("error", "删除失败！");
			e.printStackTrace();
		}
		return maps;
	}
	
	/**
	 * 保存报废数据
	 * @param param
	 * @return
	 */
	@RequestMapping({"saveRejectData"})
	@ResponseBody
	public Map<String,String> saveRejectData(String param){
		Map<String,String> maps = new HashMap<>();
		@SuppressWarnings("serial")
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		try {
			service.saveRejectDataService(map);
			maps.put("success", "报废成功！");
		}catch(Exception e){
			maps.put("error", "报废失败！");
			e.printStackTrace();
		}
		return maps;
	}
	
	
	
	/***************************模具维修记录操作方法*********************************/
	@RequestMapping({"toMouldMaintainList"})
	public String toMouldMainTainList(){
		return "mauManage/maufault/mauMouldMaintainList";
	}
	
	/**
	 * 获取模具维修记录的状态
	 * @return
	 */
	@RequestMapping({"getMouldMaintainStatus"})
	@ResponseBody
	public List<String> getMouldMainTainStatus(){
		return service.getMouldMaintainStatusService();
	}
	
	/**
	 * 获取模具维修记录信息列表，附带查询分页
	 * @param page
	 * @param param
	 * @return
	 */
	@RequestMapping({"getMouldMaintainList"})
	@ResponseBody
	public Page getMouldMaintainList(Page page,String param){
		@SuppressWarnings("serial")
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Page queryPage = service.getMouldMaintainListService(page,map);
		return queryPage;
	}
	
	/**
	 * 点击开始维修
	 * @param id 维修模具id
	 * @return
	 */
	@RequestMapping({"startMaintain"})
	@ResponseBody
	public Map<String,String> startMaintain(String id){
		Integer ids = Integer.valueOf(id);
		Map<String,String> map = new HashMap<>();
		try{
			service.startMaintainService(ids);
			map.put("success", "开始维修成功！");
		}catch(Exception e){
			e.printStackTrace();
			map.put("error","开始维修失败！");
		}
		return map;
	}
	
	/**
	 * 点击完成维修
	 * @param param
	 * @return
	 */
	@RequestMapping({"overMaintainData"})
	@ResponseBody
	public Map<String,String> overMaintainData(String param){
		@SuppressWarnings("serial")
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Map<String,String> maps = new HashMap<>();
		try{
			service.overMaintainDataService(map);
			maps.put("success", "提交成功！");
		}catch(Exception e){
			e.printStackTrace();
			maps.put("error", "提交失败！");
		}
		return maps;
	}
	
	/**
	 * 删除维修记录信息
	 * @param param
	 * @return
	 */
	@RequestMapping({"clearMaintainData"})
	@ResponseBody
	public Map<String,String> clearMaintainData(String param){
		Map<String,String> maps = new HashMap<>();
		try {
			service.clearMaintainDataService(param);
			maps.put("success", "删除成功！");
		}catch(Exception e){
			maps.put("error", "删除失败！");
			e.printStackTrace();
		}
		return maps;
	}
	
	/**
	 * 显示所有的模具编码
	 * @return
	 */
	@RequestMapping({"showAllData"})
	@ResponseBody
	public List<MauMould> showAllData(){
		return service.showAllDataService();
	}
	
	/**
	 * 根据模具code获取所有的模具name
	 * @return
	 */
	@RequestMapping({"showAllMauNameData"})
	@ResponseBody
	public List<MauMould> showAllMauNameData(String param){
		return service.showAllMauNameDataService(param);
	}
	
	/**
	 * 保存更改维修记录后的数据
	 * @param param
	 * @return
	 */
	@RequestMapping({"updateSaveMaintainData"})
	@ResponseBody
	public Map<String,String> updateSaveMaintainData(String param){
		MauMould mould = gson.fromJson(param, MauMould.class);
		Map<String,String> maps = new HashMap<>();
		try{
			service.updateSaveMaintainDataService(mould);
			maps.put("success", "修改成功！");
		}catch(Exception e){
			e.printStackTrace();
			maps.put("error", "修改失败！");
		}
		return maps;
	}
	
	
	
	
	
	
}
