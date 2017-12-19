package com.css.business.web.sysManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.sysManage.bean.SysUserOptionLog;
import com.css.business.web.sysManage.service.SysUserOptionLogManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/sysUserOptionLogManageAction")
public class SysUserOptionLogManageAction extends BaseSpringSupportAction<SysUserOptionLog, SysUserOptionLogManageService> {
	
	//@Autowired
	private SysUserOptionLogManageService service;
	
	@Override
	public SysUserOptionLogManageService getEntityManager() {
		return service;
	}

	public SysUserOptionLogManageService getService() {
		return service;
	}

	@Resource(name="sysUserOptionLogManageService")
	public void setService(SysUserOptionLogManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysUserOptionLogEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysUserOptionLogForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysUserOptionLogList";
	}
	
	

}
