package com.css.business.web.subsyscraft.craManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsyscraft.bean.CraBomAttribute;
import com.css.business.web.subsyscraft.craManage.service.CraBomAttributeManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;

@Controller
@RequestMapping("/craBomAttributeManageAction")
public class CraBomAttributeManageAction extends BaseSpringSupportAction<CraBomAttribute, CraBomAttributeManageService> {
	
	//@Autowired    craBomAttributeManageService
	@Resource(name="craBomAttributeManageService")
	private CraBomAttributeManageService service;
	
	@Override
	public CraBomAttributeManageService getEntityManager() {
		return service;
	}

	public CraBomAttributeManageService getService() {
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
