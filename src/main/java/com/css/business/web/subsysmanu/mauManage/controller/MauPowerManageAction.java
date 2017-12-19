package com.css.business.web.subsysmanu.mauManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauPower;
import com.css.business.web.subsysmanu.mauManage.service.MauPowerManageService;
import com.css.common.web.apachemq.bean.MauPowerVo;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/mauPowerManageAction")
public class MauPowerManageAction extends BaseSpringSupportAction<MauPower, MauPowerManageService> {
	
	//@Autowired
	private MauPowerManageService service;
	
	@Override
	public MauPowerManageService getEntityManager() {
		return service;
	}

	public MauPowerManageService getService() {
		return service;
	}

	@Resource(name="mauPowerManageService")
	public void setService(MauPowerManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauPowerEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauPowerForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauPowerList";
	}
	
	@RequestMapping({"getEchartsDate"})
	@ResponseBody
	public MauPowerVo getEchartsDate(String macCode,String employee,String wsCode){
		MauPowerVo vo = service.getEchartsDate(macCode, employee, wsCode);
		return vo;
	}
	
	

}
