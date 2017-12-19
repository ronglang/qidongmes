package com.css.business.web.subsysplan.plaManage.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaProductOrderAxis;
import com.css.business.web.subsysplan.plaManage.service.PlaProductOrderAxisManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/plaProductOrderAxisManageAction")
public class PlaProductOrderAxisManageAction extends BaseSpringSupportAction<PlaProductOrderAxis, PlaProductOrderAxisManageService> {
	
	//@Autowired
	private PlaProductOrderAxisManageService service;
	
	@Override
	public PlaProductOrderAxisManageService getEntityManager() {
		return service;
	}

	public PlaProductOrderAxisManageService getService() {
		return service;
	}

	@Resource(name="plaProductOrderAxisManageService")
	public void setService(PlaProductOrderAxisManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaProductOrderAxisEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaProductOrderAxisForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaProductOrderAxisList";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getAxisCode"})
	@ResponseBody
	public Map[] getAxisCode(){
		Map[] str = service.gettAxisCode();
		return str;
	}

}
