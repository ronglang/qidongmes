package com.css.common.web.workflow.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.workflow.bean.WfMenuProcess;
import com.css.common.web.workflow.service.WfMenuProcessManageService;
@Controller
@RequestMapping("/wfMenuProcessManageAction")
public class WfMenuProcessManageAction extends BaseSpringSupportAction<WfMenuProcess, WfMenuProcessManageService> {
	
	//@Autowired
	private WfMenuProcessManageService service;
	
	@Override
	public WfMenuProcessManageService getEntityManager() {
		return service;
	}

	public WfMenuProcessManageService getService() {
		return service;
	}

	@Resource(name="wfMenuProcessManageService")
	public void setService(WfMenuProcessManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfMenuProcessEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfMenuProcessForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfMenuProcessList";
	}
	
	

}
