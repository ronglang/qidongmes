package com.css.business.web.subsysplan.plaManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysplan.bean.PlaTask;
import com.css.business.web.subsysplan.plaManage.service.PlaTaskManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/plaTaskManageAction")
public class PlaTaskManageAction extends BaseSpringSupportAction<PlaTask, PlaTaskManageService> {
	
	//@Autowired
	private PlaTaskManageService service;
	
	@Override
	public PlaTaskManageService getEntityManager() {
		return service;
	}

	public PlaTaskManageService getService() {
		return service;
	}

	@Resource(name="plaTaskManageService")
	public void setService(PlaTaskManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaTaskEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaTaskForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaTaskList";
	}
	
	

}
