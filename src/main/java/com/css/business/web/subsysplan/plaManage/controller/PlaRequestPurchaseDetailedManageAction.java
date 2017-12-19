package com.css.business.web.subsysplan.plaManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysplan.bean.PlaRequestPurchaseDetailed;
import com.css.business.web.subsysplan.plaManage.service.PlaRequestPurchaseDetailedManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/plaRequestPurchaseDetailedManageAction")
public class PlaRequestPurchaseDetailedManageAction extends BaseSpringSupportAction<PlaRequestPurchaseDetailed, PlaRequestPurchaseDetailedManageService> {
	
	//@Autowired
	private PlaRequestPurchaseDetailedManageService service;
	
	@Override
	public PlaRequestPurchaseDetailedManageService getEntityManager() {
		return service;
	}

	public PlaRequestPurchaseDetailedManageService getService() {
		return service;
	}

	@Resource(name="plaRequestPurchaseDetailedManageService")
	public void setService(PlaRequestPurchaseDetailedManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaRequestPurchaseDetailedEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaRequestPurchaseDetailedForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaRequestPurchaseDetailedList";
	}
	
	
	

}
