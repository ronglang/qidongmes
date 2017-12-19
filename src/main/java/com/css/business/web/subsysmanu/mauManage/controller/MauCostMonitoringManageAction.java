package com.css.business.web.subsysmanu.mauManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysmanu.bean.MauCostMonitoring;
import com.css.business.web.subsysmanu.mauManage.service.MauCostMonitoringManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/mauCostMonitoringManageAction")
public class MauCostMonitoringManageAction extends BaseSpringSupportAction<MauCostMonitoring, MauCostMonitoringManageService> {
	
	//@Autowired
	private MauCostMonitoringManageService service;
	
	@Override
	public MauCostMonitoringManageService getEntityManager() {
		return service;
	}

	public MauCostMonitoringManageService getService() {
		return service;
	}

	@Resource(name="mauCostMonitoringManageService")
	public void setService(MauCostMonitoringManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauCostMonitoringEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauCostMonitoringForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauCostMonitoringList";
	}
	
	

}
