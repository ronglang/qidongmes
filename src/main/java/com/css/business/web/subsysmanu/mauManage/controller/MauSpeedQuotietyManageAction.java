package com.css.business.web.subsysmanu.mauManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauSpeedQuotiety;
import com.css.business.web.subsysmanu.mauManage.service.MauSpeedQuotietyManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;

@Controller
@RequestMapping("/mauSpeedQuotietyManageAction")
public class MauSpeedQuotietyManageAction extends
		BaseSpringSupportAction<MauSpeedQuotiety, MauSpeedQuotietyManageService> {

	@Resource(name="mauSpeedQuotietyManageService")
	private MauSpeedQuotietyManageService service;

	@Override
	public MauSpeedQuotietyManageService getEntityManager() {
		return service;
	}

	public MauSpeedQuotietyManageService getService() {
		return service;
	}

	public void setService(MauSpeedQuotietyManageService service) {
		this.service = service;
	}


	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "mauManage/mauMachineList";
	}
	
	@RequestMapping("queryMauSpeedQuotiety")
	@ResponseBody
	public Page queryMauSpeedQuotiety(Page p,String macCode){
		Page page = service.queryMauSpeedQuotiety(p, macCode);
		return page;
	}

	
}
