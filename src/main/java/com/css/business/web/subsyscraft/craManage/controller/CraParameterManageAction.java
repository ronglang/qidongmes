package com.css.business.web.subsyscraft.craManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsyscraft.bean.CraParameter;
import com.css.business.web.subsyscraft.craManage.service.CraParameterManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/craParameterManageAction")
public class CraParameterManageAction extends BaseSpringSupportAction<CraParameter, CraParameterManageService> {
	
	//@Autowired
	private CraParameterManageService service;
	
	@Override
	public CraParameterManageService getEntityManager() {
		return service;
	}

	public CraParameterManageService getService() {
		return service;
	}

	@Resource(name="craParameterManageService")
	public void setService(CraParameterManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craParameterEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craParameterForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craParameterList";
	}
	
	

}
