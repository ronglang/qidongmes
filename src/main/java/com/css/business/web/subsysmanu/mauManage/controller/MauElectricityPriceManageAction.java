package com.css.business.web.subsysmanu.mauManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysmanu.bean.MauElectricityPrice;
import com.css.business.web.subsysmanu.mauManage.service.MauElectricityPriceManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/mauElectricityPriceManageAction")
public class MauElectricityPriceManageAction extends BaseSpringSupportAction<MauElectricityPrice, MauElectricityPriceManageService> {
	
	//@Autowired
	private MauElectricityPriceManageService service;
	
	@Override
	public MauElectricityPriceManageService getEntityManager() {
		return service;
	}

	public MauElectricityPriceManageService getService() {
		return service;
	}

	@Resource(name="mauElectricityPriceManageService")
	public void setService(MauElectricityPriceManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauElectricityPriceEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauElectricityPriceForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauElectricityPriceList";
	}
	
	

}
