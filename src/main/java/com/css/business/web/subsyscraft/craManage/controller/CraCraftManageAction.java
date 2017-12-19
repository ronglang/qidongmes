package com.css.business.web.subsyscraft.craManage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.bean.CraCraft;
import com.css.business.web.subsyscraft.craManage.service.CraCraftManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/craCraftManageAction")
public class CraCraftManageAction extends BaseSpringSupportAction<CraCraft, CraCraftManageService> {
	
	//@Autowired
	private CraCraftManageService service;
	
	@Override
	public CraCraftManageService getEntityManager() {
		return service;
	}

	public CraCraftManageService getService() {
		return service;
	}

	@Resource(name="craCraftManageService")
	public void setService(CraCraftManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craCraftEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craCraftForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craCraftList";
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getMateril"})
	@ResponseBody
	public Map[] getMateril(){
		Map[] materil = service.getMateril();
		return materil;
	}
	
	@RequestMapping({"getSelectMateril"})
	@ResponseBody
	public HashMap<String, Object> getSelectMateril(Page p){
		HashMap<String, Object> materGgxh = service.selectMaterGgxh();
		return materGgxh;
	}
	
	
	
}
