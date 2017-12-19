package com.css.business.web.subsysstore.storeManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysstore.bean.StoreCoilSaDetail;
import com.css.business.web.subsysstore.storeManage.service.StoreCoilSaDetailManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/storeCoilSaDetailManageAction")
public class StoreCoilSaDetailManageAction extends BaseSpringSupportAction<StoreCoilSaDetail, StoreCoilSaDetailManageService> {
	
	//@Autowired
	private StoreCoilSaDetailManageService service;
	
	@Override
	public StoreCoilSaDetailManageService getEntityManager() {
		return service;
	}

	public StoreCoilSaDetailManageService getService() {
		return service;
	}

	@Resource(name="storeCoilSaDetailManageService")
	public void setService(StoreCoilSaDetailManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeCoilSaDetailEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeCoilSaDetailForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeCoilSaDetailList";
	}
	
	

}
