package com.css.business.web.sysManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.sysManage.bean.SysTelLocation;
import com.css.business.web.sysManage.service.SysTelLocationManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/sysTelLocationManageAction")
public class SysTelLocationManageAction extends BaseSpringSupportAction<SysTelLocation, SysTelLocationManageService> {
	
	//@Autowired
	private SysTelLocationManageService service;
	
	@Override
	public SysTelLocationManageService getEntityManager() {
		return service;
	}

	public SysTelLocationManageService getService() {
		return service;
	}

	@Resource(name="sysTelLocationManageService")
	public void setService(SysTelLocationManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysTelLocationEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysTelLocationForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysTelLocationList";
	}
	
	

}
