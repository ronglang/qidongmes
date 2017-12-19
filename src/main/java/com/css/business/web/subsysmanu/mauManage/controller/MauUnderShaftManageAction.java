package com.css.business.web.subsysmanu.mauManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysmanu.bean.MauUnderShaft;
import com.css.business.web.subsysmanu.mauManage.service.MauUnderShaftManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/mauUnderShaftManageAction")
public class MauUnderShaftManageAction extends BaseSpringSupportAction<MauUnderShaft, MauUnderShaftManageService> {
	
	//@Autowired
	private MauUnderShaftManageService service;
	
	@Override
	public MauUnderShaftManageService getEntityManager() {
		return service;
	}

	public MauUnderShaftManageService getService() {
		return service;
	}

	@Resource(name="mauUnderShaftManageService")
	public void setService(MauUnderShaftManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauUnderShaftEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauUnderShaftForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauUnderShaftList";
	}
	
	

}
