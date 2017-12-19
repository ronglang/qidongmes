package com.css.business.web.subsysstore.storeManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysstore.bean.StoreCoilSasDetail;
import com.css.business.web.subsysstore.storeManage.service.StoreCoilSasDetailManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/storeCoilSasDetailManageAction")
public class StoreCoilSasDetailManageAction extends BaseSpringSupportAction<StoreCoilSasDetail, StoreCoilSasDetailManageService> {
	
	private StoreCoilSasDetailManageService service;
	
	@Override
	public StoreCoilSasDetailManageService getEntityManager() {
		return service;
	}

	public StoreCoilSasDetailManageService getService() {
		return service;
	}

	@Resource(name="storeCoilSasDetailManageService")
	public void setService(StoreCoilSasDetailManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeCoilSasDetailEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeCoilSasDetailForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeCoilSasDetailList";
	}
	
	

}
