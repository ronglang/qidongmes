package com.css.business.web.subsysstore.storeManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysstore.bean.StoreObjDetail;
import com.css.business.web.subsysstore.storeManage.service.StoreObjDetailManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/storeObjDetailManageAction")
public class StoreObjDetailManageAction extends BaseSpringSupportAction<StoreObjDetail, StoreObjDetailManageService> {
	
	//@Autowired
	private StoreObjDetailManageService service;
	
	@Override
	public StoreObjDetailManageService getEntityManager() {
		return service;
	}

	public StoreObjDetailManageService getService() {
		return service;
	}

	@Resource(name="storeObjDetailManageService")
	public void setService(StoreObjDetailManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeObjDetailEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeObjDetailForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeObjDetailList";
	}
	
	

}
