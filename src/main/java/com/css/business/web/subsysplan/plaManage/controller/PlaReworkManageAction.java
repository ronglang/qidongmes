package com.css.business.web.subsysplan.plaManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysplan.bean.PlaRework;
import com.css.business.web.subsysplan.plaManage.service.PlaReworkManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/plaReworkManageAction")
public class PlaReworkManageAction extends BaseSpringSupportAction<PlaRework, PlaReworkManageService> {
	
	//@Autowired
	private PlaReworkManageService service;
	
	@Override
	public PlaReworkManageService getEntityManager() {
		return service;
	}

	public PlaReworkManageService getService() {
		return service;
	}

	@Resource(name="plaReworkManageService")
	public void setService(PlaReworkManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaReworkEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaReworkForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaReworkList";
	}
	
	

}
