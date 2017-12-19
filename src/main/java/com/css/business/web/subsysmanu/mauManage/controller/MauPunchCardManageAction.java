package com.css.business.web.subsysmanu.mauManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysmanu.bean.MauPunchCard;
import com.css.business.web.subsysmanu.mauManage.service.MauPunchCardManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/mauPunchCardManageAction")
public class MauPunchCardManageAction extends BaseSpringSupportAction<MauPunchCard, MauPunchCardManageService> {
	
	//@Autowired
	private MauPunchCardManageService service;
	
	@Override
	public MauPunchCardManageService getEntityManager() {
		return service;
	}

	public MauPunchCardManageService getService() {
		return service;
	}

	@Resource(name="mauPunchCardManageService")
	public void setService(MauPunchCardManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauPunchCardEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauPunchCardForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauPunchCardList";
	}
	
	

}
