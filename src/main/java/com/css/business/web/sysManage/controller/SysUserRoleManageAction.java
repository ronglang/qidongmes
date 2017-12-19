package com.css.business.web.sysManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.sysManage.bean.SysUserRole;
import com.css.business.web.sysManage.service.SysUserRoleManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/sysUserRoleManageAction")
public class SysUserRoleManageAction extends BaseSpringSupportAction<SysUserRole, SysUserRoleManageService> {
	
	//@Autowired
	private SysUserRoleManageService service;
	
	@Override
	public SysUserRoleManageService getEntityManager() {
		return service;
	}

	public SysUserRoleManageService getService() {
		return service;
	}

	@Resource(name="sysUserRoleManageService")
	public void setService(SysUserRoleManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysUserRoleEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysUserRoleForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysUserRoleList";
	}
	
	

}
