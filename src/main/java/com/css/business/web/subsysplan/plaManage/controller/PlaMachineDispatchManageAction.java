package com.css.business.web.subsysplan.plaManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysplan.bean.PlaMachineDispatch;
import com.css.business.web.subsysplan.plaManage.service.PlaMachineDispatchManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/plaMachineDispatchManageAction")
public class PlaMachineDispatchManageAction extends BaseSpringSupportAction<PlaMachineDispatch, PlaMachineDispatchManageService> {
	
	//@Autowired
	private PlaMachineDispatchManageService service;
	
	@Override
	public PlaMachineDispatchManageService getEntityManager() {
		return service;
	}

	public PlaMachineDispatchManageService getService() {
		return service;
	}

	@Resource(name="plaMachineDispatchManageService")
	public void setService(PlaMachineDispatchManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMachineDispatchEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMachineDispatchForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMachineDispatchList";
	}
	
	

}
