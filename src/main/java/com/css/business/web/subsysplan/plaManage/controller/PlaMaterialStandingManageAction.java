package com.css.business.web.subsysplan.plaManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysplan.bean.PlaMaterialStanding;
import com.css.business.web.subsysplan.plaManage.service.PlaMaterialStandingManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/plaMaterialStandingManageAction")
public class PlaMaterialStandingManageAction extends BaseSpringSupportAction<PlaMaterialStanding, PlaMaterialStandingManageService> {
	
	//@Autowired
	private PlaMaterialStandingManageService service;
	
	@Override
	public PlaMaterialStandingManageService getEntityManager() {
		return service;
	}

	public PlaMaterialStandingManageService getService() {
		return service;
	}

	@Resource(name="plaMaterialStandingManageService")
	public void setService(PlaMaterialStandingManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMaterialStandingEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMaterialStandingForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMaterialStandingList";
	}
	
	

}
