package com.css.business.web.subsysplan.plaManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysplan.bean.PlaCapacityReport;
import com.css.business.web.subsysplan.plaManage.service.PlaCapacityReportManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/plaCapacityReportManageAction")
public class PlaCapacityReportManageAction extends BaseSpringSupportAction<PlaCapacityReport, PlaCapacityReportManageService> {
	
	//@Autowired
	private PlaCapacityReportManageService service;
	
	@Override
	public PlaCapacityReportManageService getEntityManager() {
		return service;
	}

	public PlaCapacityReportManageService getService() {
		return service;
	}

	@Resource(name="plaCapacityReportManageService")
	public void setService(PlaCapacityReportManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaCapacityReportEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaCapacityReportForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaCapacityReportList";
	}
	
	

}
