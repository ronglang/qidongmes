package com.css.business.web.subsysplan.plaManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysplan.bean.PlaWeekSeqHours;
import com.css.business.web.subsysplan.plaManage.service.PlaWeekSeqHoursManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/plaWeekSeqHoursManageAction")
public class PlaWeekSeqHoursManageAction extends BaseSpringSupportAction<PlaWeekSeqHours, PlaWeekSeqHoursManageService> {
	
	//@Autowired
	private PlaWeekSeqHoursManageService service;
	
	@Override
	public PlaWeekSeqHoursManageService getEntityManager() {
		return service;
	}

	public PlaWeekSeqHoursManageService getService() {
		return service;
	}

	@Resource(name="plaWeekSeqHoursManageService")
	public void setService(PlaWeekSeqHoursManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaWeekSeqHoursEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaWeekSeqHoursForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaWeekSeqHoursList";
	}
	
	

}
