package com.css.business.web.subsysmanu.mauManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysmanu.bean.MauFinishProduct;
import com.css.business.web.subsysmanu.mauManage.service.MauFinishProductManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/mauFinishProductManageAction")
public class MauFinishProductManageAction extends BaseSpringSupportAction<MauFinishProduct, MauFinishProductManageService> {
	
	//@Autowired
	private MauFinishProductManageService service;
	
	@Override
	public MauFinishProductManageService getEntityManager() {
		return service;
	}

	public MauFinishProductManageService getService() {
		return service;
	}

	@Resource(name="mauFinishProductManageService")
	public void setService(MauFinishProductManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauFinishProductEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauFinishProductForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauFinishProductList";
	}
	
	

}
