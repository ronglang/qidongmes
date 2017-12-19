package com.css.business.web.subsysstatistics.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaCourse;
import com.css.business.web.subsysstatistics.service.PlanDetialManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/planDetialManageAction")
public class PlanDetialManageAction extends
		BaseSpringSupportAction<PlaCourse, PlanDetialManageService> {

	@Resource(name = "planDetialManageService")
	private PlanDetialManageService service;

	@Override
	public PlanDetialManageService getEntityManager() {
		// TODO Auto-generated method stub
		return service;
	}

	Gson gson = new Gson();
	/**
	 * 
	 * @Description: 去主页面
	 * @return
	 */
	@RequestMapping("toPageList")
	public String toPageList(HttpServletRequest request) {
		return "totalQuery/planDetial/PlanTable";
	}
	/**
	 * 
	 * @Description: 去详情页面
	 * @return
	 */
	@RequestMapping("toDetailPageList")
	public String toDetailPageList(HttpServletRequest request,String param) {
		request.setAttribute("data",param);
		return "totalQuery/planDetial/PlanDetailTable";
	}
	/**
	 * 
	 * @Description: 去材料详情页
	 * @return
	 */
	@RequestMapping("toMaterDetailPageList")
	public String toMaterDetailPageList(HttpServletRequest request,String param) {
		request.setAttribute("data",param);
		return "totalQuery/planDetial/PlanMaterDetail";
	}

	/**
	 * 
	 * @Description: 显示工序路线详情
	 * @return
	 */
	@RequestMapping("toSeqDetail")
	public String toSeqDetail() {

		return "";
	}

	/**
	 * 
	 * @Description: 查询周计划中当天的生产情况     生产令id,proggxh ,轴数,工作日    
	 * @param request
	 * @param param
	 * @param page
	 * @return
	 */
	@RequestMapping("getPageList")
	@ResponseBody
	public Page getPageList(HttpServletRequest request, String param, Page page) {
		Map<String,String>map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		//默认当天的
		Page queryPage = page;
		try {
			queryPage = service.queryPageList(page,map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return queryPage;
	}
	
	/**
	 * 
	 * @Description: 获得当日的生产情况  拆分表,机台计划表,机台进度表   
	 * @param request
	 * @param param 生产令id 工作日
	 * @param page
	 * @return
	 */
	@RequestMapping("getDetailPageList")
	@ResponseBody
	public Page getDetailPageList(HttpServletRequest request, String param, Page page){
		Map<String,String>map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		//默认当天的
		Page queryPage = page;
		try {
			queryPage = service.queryDetailPageList(page,map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return queryPage;
	}
	
	/**
	 * 
	 * @Description: 材料的详情   
	 * @param request
	 * @param param
	 * @param page
	 * @return
	 */
	@RequestMapping("getMaterDetailPageList")
	@ResponseBody
	public Page getMaterDetailPageList(HttpServletRequest request, String param, Page page){
		Map<String,String>map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Page queryPage = page;
		try {
			queryPage = service.queryMaterDetailPageList(page,map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return queryPage;
	}

}
