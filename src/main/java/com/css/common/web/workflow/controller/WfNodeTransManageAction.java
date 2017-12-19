package com.css.common.web.workflow.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.workflow.bean.WfNodeTrans;
import com.css.common.web.workflow.service.WfNodeTransManageService;
@Controller
@RequestMapping("/wfNodeTransManageAction")
public class WfNodeTransManageAction extends BaseSpringSupportAction<WfNodeTrans, WfNodeTransManageService> {
	
	//@Autowired
	private WfNodeTransManageService service;
	
	@Override
	public WfNodeTransManageService getEntityManager() {
		return service;
	}

	public WfNodeTransManageService getService() {
		return service;
	}

	@Resource(name="wfNodeTransManageService")
	public void setService(WfNodeTransManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfNodeTransEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfNodeTransForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfNodeTransList";
	}
	
	

}
