package com.css.business.web.subsysplan.plaManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysplan.bean.PlaSeqMaterDetail;
import com.css.business.web.subsysplan.plaManage.service.PlaSeqMaterDetailManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/plaSeqMaterDetailManageAction")
public class PlaSeqMaterDetailManageAction extends BaseSpringSupportAction<PlaSeqMaterDetail, PlaSeqMaterDetailManageService> {
	
	//@Autowired
	private PlaSeqMaterDetailManageService service;
	
	@Override
	public PlaSeqMaterDetailManageService getEntityManager() {
		return service;
	}

	public PlaSeqMaterDetailManageService getService() {
		return service;
	}

	@Resource(name="plaSeqMaterDetailManageService")
	public void setService(PlaSeqMaterDetailManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaSeqMaterDetailEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaSeqMaterDetailForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaSeqMaterDetailList";
	}
	
	

}
