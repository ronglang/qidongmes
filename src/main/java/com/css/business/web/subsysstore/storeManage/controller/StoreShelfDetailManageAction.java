package com.css.business.web.subsysstore.storeManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysstore.bean.StoreShelfDetail;
import com.css.business.web.subsysstore.storeManage.service.StoreShelfDetailManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/storeShelfDetailManageAction")
public class StoreShelfDetailManageAction extends BaseSpringSupportAction<StoreShelfDetail, StoreShelfDetailManageService> {
	
	//@Autowired
	private StoreShelfDetailManageService service;
	
	@Override
	public StoreShelfDetailManageService getEntityManager() {
		return service;
	}

	public StoreShelfDetailManageService getService() {
		return service;
	}

	@Resource(name="storeShelfDetailManageService")
	public void setService(StoreShelfDetailManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeShelfDetailEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeShelfDetailForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeShelfDetailList";
	}
	
	

}
