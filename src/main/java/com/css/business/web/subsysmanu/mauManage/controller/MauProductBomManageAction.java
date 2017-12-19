package com.css.business.web.subsysmanu.mauManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysmanu.bean.MauProductBom;
import com.css.business.web.subsysmanu.mauManage.service.MauProductBomManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/mauProductBomManageAction")
public class MauProductBomManageAction extends BaseSpringSupportAction<MauProductBom, MauProductBomManageService> {
	
	//@Autowired
	private MauProductBomManageService service;
	
	@Override
	public MauProductBomManageService getEntityManager() {
		return service;
	}

	public MauProductBomManageService getService() {
		return service;
	}

	@Resource(name="mauProductBomManageService")
	public void setService(MauProductBomManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauProductBomEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauProductBomForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauProductBomList";
	}
	
	

}
