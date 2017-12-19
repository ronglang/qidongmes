package com.css.business.web.subsysplan.plaManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysplan.bean.PlaProductStanding;
import com.css.business.web.subsysplan.plaManage.service.PlaProductStandingManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/plaProductStandingManageAction")
public class PlaProductStandingManageAction extends BaseSpringSupportAction<PlaProductStanding, PlaProductStandingManageService> {
	
	//@Autowired
	private PlaProductStandingManageService service;
	
	@Override
	public PlaProductStandingManageService getEntityManager() {
		return service;
	}

	public PlaProductStandingManageService getService() {
		return service;
	}

	@Resource(name="plaProductStandingManageService")
	public void setService(PlaProductStandingManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaProductStandingEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaProductStandingForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaProductStandingList";
	}
	
	

}
