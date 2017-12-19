package com.css.business.web.subsysplan.plaManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysplan.bean.PlaDispatchDetails;
import com.css.business.web.subsysplan.plaManage.service.PlaDispatchDetailsManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/plaDispatchDetailsManageAction")
public class PlaDispatchDetailsManageAction extends BaseSpringSupportAction<PlaDispatchDetails, PlaDispatchDetailsManageService> {
	
	//@Autowired
	private PlaDispatchDetailsManageService service;
	
	@Override
	public PlaDispatchDetailsManageService getEntityManager() {
		return service;
	}

	public PlaDispatchDetailsManageService getService() {
		return service;
	}

	@Resource(name="plaDispatchDetailsManageService")
	public void setService(PlaDispatchDetailsManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaDispatchDetailsEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaDispatchDetailsForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaDispatchDetailsList";
	}
	
	

}
