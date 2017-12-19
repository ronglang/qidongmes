package com.css.business.web.subsysplan.plaManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysplan.bean.PlaSingleMoveDetail;
import com.css.business.web.subsysplan.plaManage.service.PlaSingleMoveDetailManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/plaSingleMoveDetailManageAction")
public class PlaSingleMoveDetailManageAction extends BaseSpringSupportAction<PlaSingleMoveDetail, PlaSingleMoveDetailManageService> {
	
	//@Autowired
	private PlaSingleMoveDetailManageService service;
	
	@Override
	public PlaSingleMoveDetailManageService getEntityManager() {
		return service;
	}

	public PlaSingleMoveDetailManageService getService() {
		return service;
	}

	@Resource(name="plaSingleMoveDetailManageService")
	public void setService(PlaSingleMoveDetailManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaSingleMoveDetailEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaSingleMoveDetailForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaSingleMoveDetailList";
	}
	
	

}
