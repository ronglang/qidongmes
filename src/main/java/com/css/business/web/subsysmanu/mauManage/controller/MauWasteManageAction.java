package com.css.business.web.subsysmanu.mauManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysmanu.bean.MauWaste;
import com.css.business.web.subsysmanu.mauManage.service.MauWasteManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/mauWasteManageAction")
public class MauWasteManageAction extends BaseSpringSupportAction<MauWaste, MauWasteManageService> {
	
	//@Autowired
	private MauWasteManageService service;
	
	@Override
	public MauWasteManageService getEntityManager() {
		return service;
	}

	public MauWasteManageService getService() {
		return service;
	}

	@Resource(name="mauWasteManageService")
	public void setService(MauWasteManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauWasteEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauWasteForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauWasteList";
	}
	
	

}
