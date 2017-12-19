package com.css.business.web.sysManage.controller;

import javax.annotation.Resource;
import javax.servlet.AsyncContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.sysManage.bean.SysOrg;
import com.css.business.web.sysManage.service.SysOrgManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/sysOrgManageAction")
public class SysOrgManageAction extends BaseSpringSupportAction<SysOrg, SysOrgManageService> {
	
	//@Autowired
	private SysOrgManageService service;
	
	@Override
	public SysOrgManageService getEntityManager() {
		return service;
	}

	public SysOrgManageService getService() {
		return service;
	}

	@Resource(name="sysOrgManageService")
	public void setService(SysOrgManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysOrgEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysOrgForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysOrgList";
	}
	/**
	 * 主頁展示所有数据
	 * */
	@RequestMapping({ "getGridPage" })
	@ResponseBody
	public Page getGridPage(HttpServletRequest request,Page param, Integer id,String orgName){
		request.setAttribute("id", id);
		Page p = service.getDataListPage(param,orgName);
		
		return p;
	}
	@RequestMapping({"nodeTree"})
	@ResponseBody
	public void nodeTree(HttpServletRequest request,HttpServletResponse rep){
		

	}

}
