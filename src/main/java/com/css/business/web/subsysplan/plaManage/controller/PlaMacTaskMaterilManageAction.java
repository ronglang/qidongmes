package com.css.business.web.subsysplan.plaManage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaMacTaskMateril;
import com.css.business.web.subsysplan.plaManage.service.PlaMacTaskMaterilManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;


@Controller
@RequestMapping("/plaMacTaskMaterilManageAction")
public class PlaMacTaskMaterilManageAction  extends
BaseSpringSupportAction<PlaMacTaskMateril,PlaMacTaskMaterilManageService>{

	@Autowired
	private PlaMacTaskMaterilManageService service;
	
	public void setQualityUndoDetailService(PlaMacTaskMaterilManageService service){
		this.service = service;
	}
	
	@Override
	public PlaMacTaskMaterilManageService getEntityManager() {
		return service;
	}
	
	@RequestMapping({ "toTablePage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/plaMacTaskMaterilTable";
	}
	
	@RequestMapping({"getPlaMacTaskMateril"})
	@ResponseBody
	public Page getPlaMacTaskMateril(Page p ,String stime,String dtime){
		Page page = service.getPlaMacTaskMateril(p, stime, dtime);
		return page;
	}
	
}
