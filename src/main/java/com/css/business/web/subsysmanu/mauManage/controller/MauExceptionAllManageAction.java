package com.css.business.web.subsysmanu.mauManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysmanu.bean.MauExceptionAll;
import com.css.business.web.subsysmanu.mauManage.service.MauExceptionAllManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/mauExceptionAllManageAction")
public class MauExceptionAllManageAction extends BaseSpringSupportAction<MauExceptionAll, MauExceptionAllManageService> {
	
	//@Autowired
	private MauExceptionAllManageService service;
	
	@Override
	public MauExceptionAllManageService getEntityManager() {
		return service;
	}

	public MauExceptionAllManageService getService() {
		return service;
	}

	@Resource(name="mauExceptionAllManageService")
	public void setService(MauExceptionAllManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauExceptionAllEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauExceptionAllForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauExceptionAllList";
	}
	
	

}
