package com.css.business.web.subsysplan.plaManage.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaMember;
import com.css.business.web.subsysplan.plaManage.service.PlaMemberManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/plaMemberManageAction")
public class PlaMemberManageAction extends BaseSpringSupportAction<PlaMember, PlaMemberManageService> {
	
	//@Autowired
	private PlaMemberManageService service;
	
	@Override
	public PlaMemberManageService getEntityManager() {
		return service;
	}

	public PlaMemberManageService getService() {
		return service;
	}

	@Resource(name="plaMemberManageService")
	public void setService(PlaMemberManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMemberEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMemberForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMemberList";
	}
	
	@RequestMapping(value={ "loadPlaMember" })
	@ResponseBody
	public  HashMap<String,Object> loadPlaMember(String page,String pagesize){
		
		return service.getPlaMemberService(page,pagesize);
	}
	
	@RequestMapping(value={"/deletePlaMemberByIds"})
	@ResponseBody
	public String deletePlaMemberByIds(int[] ids){
		int [] a=ids;
		service.deletePlaMemberByIdsService(a);
		return "删除成功";
	}
	
	@RequestMapping(value={"/updatePlaMember"})
	@ResponseBody
	public String updatePlaMember(PlaMember row){
		service.updatePlaMemberService(row);
		return "更新成功";
	}
	
	@RequestMapping(value={"/addPlaMember"})
	@ResponseBody
	public String addPlaMember(PlaMember row){
		service.addPlaMemberService(row);
		return "新增成功";
	}
	
	@RequestMapping(value={ "serchPlaMemberByName" })
	@ResponseBody
	public  HashMap<String,Object> serchPlaMemberByName(String page,String pagesize,HttpServletRequest requst){
		String names = requst.getParameter("name");
		return service.serchPlaMemberByNameService(page,pagesize,names);
	}

}
