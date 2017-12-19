package com.css.business.web.subsysmanu.mauManage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauException;
import com.css.business.web.subsysmanu.mauManage.service.MauExceptionManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
/**
 * 
* @Title: MauExceptionManageAction.java   
* @Package com.css.business.web.subsysmanu.mauManage.controller   
* @Description: 生产异常信息   
* @author   rb
* @date 2017年7月10日 下午5:48:52   
* @company  SMTC
 */
@Controller
@RequestMapping("/mauExceptionManageAction")
public class MauExceptionManageAction extends BaseSpringSupportAction<MauException, MauExceptionManageService> {
	
	//@Autowired
	private MauExceptionManageService service;
	
	
	@Override
	public MauExceptionManageService getEntityManager() {
		return service;
	}

	public MauExceptionManageService getService() {
		return service;
	}

	@Resource(name="mauExceptionManageService")
	public void setService(MauExceptionManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauExceptionEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauExceptionForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "mauManage/mauExceptionList";
	}
	
	@RequestMapping("getPage")
	@ResponseBody
	public Page getPage(HttpServletRequest req ,Page page){
		Page queryPage = page;
		try {
			queryPage = service.queryPage(page);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return queryPage;
	}
	
	

}
