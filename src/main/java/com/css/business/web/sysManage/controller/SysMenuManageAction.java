package com.css.business.web.sysManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.sysManage.bean.SysMenu;
import com.css.business.web.sysManage.service.SysMenuManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/sysMenuManageAction")
public class SysMenuManageAction extends BaseSpringSupportAction<SysMenu, SysMenuManageService> {
	
	//@Autowired
	private SysMenuManageService service;
	
	@Override
	public SysMenuManageService getEntityManager() {
		return service;
	}

	public SysMenuManageService getService() {
		return service;
	}

	@Resource(name="sysMenuManageService")
	public void setService(SysMenuManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysMenuEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysMenuForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysMenuList";
	}
	
	

}
