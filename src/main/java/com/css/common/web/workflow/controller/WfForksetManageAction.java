package com.css.common.web.workflow.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.workflow.bean.WfForkset;
import com.css.common.web.workflow.service.WfForksetManageService;
@Controller
@RequestMapping("/wfForksetManageAction")
public class WfForksetManageAction extends BaseSpringSupportAction<WfForkset, WfForksetManageService> {
	
	//@Autowired
	private WfForksetManageService service;
	
	@Override
	public WfForksetManageService getEntityManager() {
		return service;
	}

	public WfForksetManageService getService() {
		return service;
	}

	@Resource(name="wfForksetManageService")
	public void setService(WfForksetManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfForksetEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfForksetForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfForksetList";
	}
	
	

}
