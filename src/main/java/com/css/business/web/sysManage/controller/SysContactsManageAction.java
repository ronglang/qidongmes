package com.css.business.web.sysManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.sysManage.bean.SysContacts;
import com.css.business.web.sysManage.service.SysContactsManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/sysContactsManageAction")
public class SysContactsManageAction extends BaseSpringSupportAction<SysContacts, SysContactsManageService> {
	
	//@Autowired
	private SysContactsManageService service;
	
	@Override
	public SysContactsManageService getEntityManager() {
		return service;
	}

	public SysContactsManageService getService() {
		return service;
	}

	@Resource(name="sysContactsManageService")
	public void setService(SysContactsManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysContactsEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysContactsForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysContactsList";
	}
	
	@RequestMapping({ "toCallTelPage" })
	public String toCallTelPage(HttpServletRequest request, String tel){
		request.setAttribute("tel", tel);
		return "sysManage/sysContactsCallTel";
	}

}
