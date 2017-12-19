package com.css.business.web.subsysplan.plaManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysplan.bean.PlaMaterialRequestPurchase;
import com.css.business.web.subsysplan.plaManage.service.PlaMaterialRequestPurchaseManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/plaMaterialRequestPurchaseManageAction")
public class PlaMaterialRequestPurchaseManageAction extends BaseSpringSupportAction<PlaMaterialRequestPurchase, PlaMaterialRequestPurchaseManageService> {
	
	//@Autowired
	private PlaMaterialRequestPurchaseManageService service;
	
	@Override
	public PlaMaterialRequestPurchaseManageService getEntityManager() {
		return service;
	}

	public PlaMaterialRequestPurchaseManageService getService() {
		return service;
	}

	@Resource(name="plaMaterialRequestPurchaseManageService")
	public void setService(PlaMaterialRequestPurchaseManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMaterialRequestPurchaseEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMaterialRequestPurchaseForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMaterialRequestPurchaseList";
	}
	
	

}
