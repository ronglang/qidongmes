package com.css.business.web.subsysplan.plaManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysplan.bean.PlaAbnormalInfor;
import com.css.business.web.subsysplan.plaManage.service.PlaAbnormalInforManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/plaAbnormalInforManageAction")
public class PlaAbnormalInforManageAction extends BaseSpringSupportAction<PlaAbnormalInfor, PlaAbnormalInforManageService> {
	
	//@Autowired
	private PlaAbnormalInforManageService service;
	
	@Override
	public PlaAbnormalInforManageService getEntityManager() {
		return service;
	}

	public PlaAbnormalInforManageService getService() {
		return service;
	}

	@Resource(name="plaAbnormalInforManageService")
	public void setService(PlaAbnormalInforManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaAbnormalInforEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaAbnormalInforForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaAbnormalInforList";
	}
	
	

}
