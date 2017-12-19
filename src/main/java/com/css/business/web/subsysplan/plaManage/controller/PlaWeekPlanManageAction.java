package com.css.business.web.subsysplan.plaManage.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaWeekPlan;
import com.css.business.web.subsysplan.plaManage.service.PlaWeekPlanManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
@Controller
@RequestMapping("/plaWeekPlanManageAction")
public class PlaWeekPlanManageAction extends BaseSpringSupportAction<PlaWeekPlan, PlaWeekPlanManageService> {
	
	//@Autowired
	private PlaWeekPlanManageService service;
	
	private Gson gson=new Gson();
	@Override
	public PlaWeekPlanManageService getEntityManager() {
		return service;
	}

	public PlaWeekPlanManageService getService() {
		return service;
	}

	@Resource(name="plaWeekPlanManageService")
	public void setService(PlaWeekPlanManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaWeekPlanEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaWeekPlanForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaWeekPlanList";
	}
	
	@RequestMapping({ "toPlaWeekPlanTable" })
	public String toPlaWeekPlanTable(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaWeekPlanTable";
	}
	
	@RequestMapping({"getPlaWeekPlan"})
	@ResponseBody
	public Page getPlaWeekPlan(Page p,String param){
		
		Map<String,String> map=gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		
		Page page = service.getPalWeekPlan(p,map);
		return page;
	}
	
	/**
	 * 获取所有的状态
	 * @return
	 */
	@RequestMapping("getStatusList")
	@ResponseBody
	public List<String> getStatusList(){
		return service.getStatusListService();
	}
	

}
