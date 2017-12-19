package com.css.business.web.subsysstore.storeManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysstore.bean.StorePstockDetail;
import com.css.business.web.subsysstore.storeManage.service.StorePstockDetailManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/storePstockDetailManageAction")
public class StorePstockDetailManageAction extends BaseSpringSupportAction<StorePstockDetail, StorePstockDetailManageService> {
	
	//@Autowired
	private StorePstockDetailManageService service;
	
	@Override
	public StorePstockDetailManageService getEntityManager() {
		return service;
	}

	public StorePstockDetailManageService getService() {
		return service;
	}

	@Resource(name="storePstockDetailManageService")
	public void setService(StorePstockDetailManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storePstockDetailEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storePstockDetailForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storePstockDetailList";
	}
	
	

}
