package com.css.common.web.workflow.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.workflow.bean.WfSqlc;
import com.css.common.web.workflow.service.WfSqlcManageService;
@Controller
@RequestMapping("/wfSqlcManageAction")
public class WfSqlcManageAction extends BaseSpringSupportAction<WfSqlc, WfSqlcManageService> {
	
	//@Autowired
	private WfSqlcManageService service;
	
	@Override
	public WfSqlcManageService getEntityManager() {
		return service;
	}

	public WfSqlcManageService getService() {
		return service;
	}

	@Resource(name="wfSqlcManageService")
	public void setService(WfSqlcManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfSqlcEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfSqlcForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "wfManage/wfSqlcList";
	}
	
	

}
