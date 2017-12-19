package com.css.business.web.subsyscraft.craManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsyscraft.bean.CraCmater;
import com.css.business.web.subsyscraft.craManage.service.CraCmaterManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/craCmaterManageAction")
public class CraCmaterManageAction extends BaseSpringSupportAction<CraCmater, CraCmaterManageService> {
	
	//@Autowired
	@Resource(name="craCmaterManageService")
	private CraCmaterManageService service;
	
	@Override
	public CraCmaterManageService getEntityManager() {
		return service;
	}

	public CraCmaterManageService getService() {
		return service;
	}

//	@Resource(name="craCmaterManageService")
//	public void setService(CraCmaterManageService service) {
//		this.service = service;
//	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craCmaterEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craCmaterForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craCmaterList";
	}
	
	

}
