package com.css.common.web.workflow.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.workflow.bean.WfYewuProcess;
import com.css.common.web.workflow.service.WfYewuProcessManageService;
@Controller
@RequestMapping("/wfYewuProcessManageAction")
public class WfYewuProcessManageAction extends BaseSpringSupportAction<WfYewuProcess, WfYewuProcessManageService> {
	
	//@Autowired
	private WfYewuProcessManageService service;
	
	@Override
	public WfYewuProcessManageService getEntityManager() {
		return service;
	}

	public WfYewuProcessManageService getService() {
		return service;
	}

	@Resource(name="wfYewuProcessManageService")
	public void setService(WfYewuProcessManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfYewuProcessEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfYewuProcessForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfYewuProcessList";
	}
	
	@RequestMapping({ "toWorkflow_admin" })
	public String toWorkflow_admin(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "workflow/wf_admin";
	}

}
