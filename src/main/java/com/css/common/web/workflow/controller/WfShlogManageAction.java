package com.css.common.web.workflow.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.workflow.bean.WfShlog;
import com.css.common.web.workflow.service.WfShlogManageService;
@Controller
@RequestMapping("/wfShlogManageAction")
public class WfShlogManageAction extends BaseSpringSupportAction<WfShlog, WfShlogManageService> {
	
	//@Autowired
	private WfShlogManageService service;
	
	@Override
	public WfShlogManageService getEntityManager() {
		return service;
	}

	public WfShlogManageService getService() {
		return service;
	}

	@Resource(name="wfShlogManageService")
	public void setService(WfShlogManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfShlogEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfShlogForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfShlogList";
	}
	
	

}
