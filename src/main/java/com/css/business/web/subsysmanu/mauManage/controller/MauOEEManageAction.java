package com.css.business.web.subsysmanu.mauManage.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WriteException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauOEE;
import com.css.business.web.subsysmanu.bean.MauOEEVO;
import com.css.business.web.subsysmanu.mauManage.service.MauOEEManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * 
* @Title: MauOEEManageAction.java   
* @Package com.css.business.web.subsysmanu.mauManage.controller   
* @Description: OEE分析ACTION   
* @author   rb
* @date 2017年8月29日 下午2:33:08   
* @company  SMTC
 */
@Controller
@RequestMapping("/mauOEEManageAction")
public class MauOEEManageAction extends BaseSpringSupportAction<MauOEE, MauOEEManageService> {
	
	//@Autowired
	private MauOEEManageService service;
	
	@Override
	public MauOEEManageService getEntityManager() {
		return service;
	}

	public MauOEEManageService getService() {
		return service;
	}

	@Resource(name="mauOEEManageService")
	public void setService(MauOEEManageService service) {
		this.service = service;
	}
	private Gson gson = new  Gson();
	
	
	@RequestMapping("toPageList")
	public String toPageList(){
		return "mauManage/mauOEE/mauOEEList";
	}
	
	@RequestMapping("toSingleOEEChart")
	public String toSingleOEEChart(){
		return "mauManage/mauOEE/mauOEEChart";
	}
	
	@RequestMapping("toAllOEEChart")
	public String toAllOEEChart(){
		return "mauManage/mauOEE/mauAllOEEChart";
	}
	
	@RequestMapping("toTotalChart")
	public String toTotalChart(){
		return "mauManage/mauOEE/mauTotalChart";
	}
	
	/**
	 * 
	 * @Description: 分页查询   
	 * @param request
	 * @param page
	 * @param param
	 * @return
	 */
	@RequestMapping("getPageList")
	@ResponseBody
	public Page getPageList(HttpServletRequest request,Page page,String param){
		Map<String,String>map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Page queryPage = page;
		try {
			queryPage = service.getPageList(map,page);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return queryPage;
	}
	
	/**
	 * 导出
	 */
	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletRequest request,HttpServletResponse response,String param){
		Map<String,String>map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		try {
			service.exportExcel(response,map);
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @Description: 获得单个机台某个时间段的各统计数据的展示   
	 * @param request
	 * @param param
	 * @return
	 */
	@RequestMapping("getSingleMacChart")
	@ResponseBody
	public HashMap<String, Object> getSingleMacChart(HttpServletRequest request,String param){
		Map<String,String>map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		MauOEEVO vo = service.getSingleMacChart(map);
		if (vo != null) {
			return JsonWrapper.successWrapper(vo);
		}
		return JsonWrapper.failureWrapperMsg("没有相关信息");
		
	}
	
	@RequestMapping("getAllMacChart")
	@ResponseBody
	public HashMap<String, Object> getAllMacChart(HttpServletRequest request,String param){
		Map<String,String>map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		MauOEEVO vo = service.getAllMacChart(map);
		if (vo != null) {
			return JsonWrapper.successWrapper(vo);
		}
		return JsonWrapper.failureWrapperMsg("没有相关信息");
		
	}
	
	/**
	 * 
	 * @Description: 找到macCode   
	 * @param request
	 * @return
	 */
	@RequestMapping("getMacCodes")
	@ResponseBody
	public HashMap<String, Object>getMacCodes(HttpServletRequest request){
		List<String>list = service.getMaccodes();
		if (list !=null) {
			return JsonWrapper.successWrapper(list);
		}else {
			return JsonWrapper.failureWrapperMsg("没找到相关数据");
		}
	}
	
}

