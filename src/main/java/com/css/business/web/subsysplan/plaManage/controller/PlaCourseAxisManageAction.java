package com.css.business.web.subsysplan.plaManage.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaCourseAxis;
import com.css.business.web.subsysplan.plaManage.service.PlaCourseAxisManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;

@Controller
@RequestMapping("/plaCourseAxisManageAction")
public class PlaCourseAxisManageAction extends
		BaseSpringSupportAction<PlaCourseAxis, PlaCourseAxisManageService> {

	// @Autowired
	private PlaCourseAxisManageService service;

	@Override
	public PlaCourseAxisManageService getEntityManager() {
		return service;
	}

	public PlaCourseAxisManageService getService() {
		return service;
	}

	@Resource(name = "plaCourseAxisManageService")
	public void setService(PlaCourseAxisManageService service) {
		this.service = service;
	}

	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/plaSingleMoveEdit";
	}

	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/plaSingleMoveForm";
	}

	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id) {
		request.setAttribute("id", id);
		return "plaManage/plaSingleMoveList";
	}
	
	private Gson gson = new Gson();
	@RequestMapping("getListPage")
	@ResponseBody
	public Page getListPage(HttpServletRequest request,Page page,String param){
		page = service.getListPage(page,param);
		return page;
				
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public Page getList(HttpServletRequest request,String param){
		List<Object> list = service.getList(param);
		Page page = new Page();
		page.setData(list);
		return page;
	}

}
