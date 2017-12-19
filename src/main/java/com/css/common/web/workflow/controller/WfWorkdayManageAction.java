package com.css.common.web.workflow.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.workflow.bean.WfWorkday;
import com.css.common.web.workflow.service.WfWorkdayManageService;
@Controller
@RequestMapping("/wfWorkdayManageAction")
public class WfWorkdayManageAction extends BaseSpringSupportAction<WfWorkday, WfWorkdayManageService> {
	
	//@Autowired
	private WfWorkdayManageService service;
	
	@Override
	public WfWorkdayManageService getEntityManager() {
		return service;
	}

	public WfWorkdayManageService getService() {
		return service;
	}

	@Resource(name="wfWorkdayManageService")
	public void setService(WfWorkdayManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfWorkdayEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfWorkdayForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfWorkdayList";
	}
	
	

}
