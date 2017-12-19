package com.css.business.web.subsysplan.plaManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysplan.bean.PlaSingleMoveHandleDetail;
import com.css.business.web.subsysplan.plaManage.service.PlaSingleMoveHandleDetailManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/plaSingleMoveHandleDetailManageAction")
public class PlaSingleMoveHandleDetailManageAction extends BaseSpringSupportAction<PlaSingleMoveHandleDetail, PlaSingleMoveHandleDetailManageService> {
	
	//@Autowired
	private PlaSingleMoveHandleDetailManageService service;
	
	@Override
	public PlaSingleMoveHandleDetailManageService getEntityManager() {
		return service;
	}

	public PlaSingleMoveHandleDetailManageService getService() {
		return service;
	}

	@Resource(name="plaSingleMoveHandleDetailManageService")
	public void setService(PlaSingleMoveHandleDetailManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaSingleMoveHandleDetailEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaSingleMoveHandleDetailForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaSingleMoveHandleDetailList";
	}
	
	

}
