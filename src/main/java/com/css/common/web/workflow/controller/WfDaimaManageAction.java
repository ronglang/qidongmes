package com.css.common.web.workflow.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.workflow.bean.WfDaima;
import com.css.common.web.workflow.service.WfDaimaManageService;
@Controller
@RequestMapping("/wfDaimaManageAction")
public class WfDaimaManageAction extends BaseSpringSupportAction<WfDaima,WfDaimaManageService> {
	
	//@Autowired
	private WfDaimaManageService service;
	
	@Override
	public WfDaimaManageService getEntityManager() {
		return service;
	}

	public WfDaimaManageService getService() {
		return service;
	}

	@Resource(name="wfDaimaManageService")
	public void setService(WfDaimaManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "bsManage/bsDaimaEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "bsManage/bsDaimaForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "bsManage/bsDaimaList";
	}
	
	

}
