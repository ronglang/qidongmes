package com.css.business.web.subsysplan.plaManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysplan.bean.PlaMachinePlanMater;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanMaterManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;

/**
 * @TODO  : 
 * @author: 翟春磊
 * @DATE  : 2017年7月11日
 */
@Controller
@RequestMapping("/plaMachinePlanMaterManageAction")
public class PlaMachinePlanMaterManageAction extends BaseSpringSupportAction<PlaMachinePlanMater, PlaMachinePlanMaterManageService> {
	
	//@Autowired
	private PlaMachinePlanMaterManageService service;
	
	@Override
	public PlaMachinePlanMaterManageService getEntityManager() {
		return service;
	}

	public PlaMachinePlanMaterManageService getService() {
		return service;
	}

	@Resource(name="plaMachinePlanMaterManageService")
	public void setService(PlaMachinePlanMaterManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMaterEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMaterForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMaterList";
	}
	
	

}
