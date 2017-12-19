package com.css.business.web.subsysstore.storeManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
 
import com.css.business.web.subsysstore.bean.StorePstock;
import com.css.business.web.subsysstore.storeManage.service.StorePstockManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/storePstockManageAction")
public class StorePstockManageAction extends BaseSpringSupportAction<StorePstock, StorePstockManageService> {
	
	//@Autowired
	private StorePstockManageService service;
	
	@Override
	public StorePstockManageService getEntityManager() {
		return service;
	}

	public StorePstockManageService getService() {
		return service;
	}

	@Resource(name="storePstockManageService")
	public void setService(StorePstockManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storePstockEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storePstockForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storePstockList";
	}
	
	

}
