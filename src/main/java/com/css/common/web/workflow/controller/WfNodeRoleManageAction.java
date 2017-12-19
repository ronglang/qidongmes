package com.css.common.web.workflow.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.workflow.bean.WfNodeRole;
import com.css.common.web.workflow.service.WfNodeRoleManageService;
@Controller
@RequestMapping("/wfNodeRoleManageAction")
public class WfNodeRoleManageAction extends BaseSpringSupportAction<WfNodeRole, WfNodeRoleManageService> {
	
	//@Autowired
	private WfNodeRoleManageService service;
	
	@Override
	public WfNodeRoleManageService getEntityManager() {
		return service;
	}

	public WfNodeRoleManageService getService() {
		return service;
	}

	@Resource(name="wfNodeRoleManageService")
	public void setService(WfNodeRoleManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfNodeRoleEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfNodeRoleForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfNodeRoleList";
	}
	
	

}
