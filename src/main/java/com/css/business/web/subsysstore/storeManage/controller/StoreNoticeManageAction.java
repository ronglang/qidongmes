package com.css.business.web.subsysstore.storeManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysstore.bean.StoreNotice;
import com.css.business.web.subsysstore.storeManage.service.StoreNoticeManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/storeNoticeManageAction")
public class StoreNoticeManageAction extends BaseSpringSupportAction<StoreNotice, StoreNoticeManageService> {
	
	//@Autowired
	private StoreNoticeManageService service;
	
	@Override
	public StoreNoticeManageService getEntityManager() {
		return service;
	}

	public StoreNoticeManageService getService() {
		return service;
	}

	@Resource(name="storeNoticeManageService")
	public void setService(StoreNoticeManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeNoticeEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeNoticeForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeNoticeList";
	}
	
	

}
