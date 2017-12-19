package com.css.business.web.subsysstore.storeManage.controller;

import java.text.ParseException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauForklift;
import com.css.business.web.subsysstore.storeManage.service.StoreForkliftManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/storeForkliftManageAction")
public class StoreForkliftManageAction extends BaseSpringSupportAction<MauForklift, StoreForkliftManageService> {
	
	//@Autowired
	private StoreForkliftManageService service;
	
	@Override
	public StoreForkliftManageService getEntityManager() {
		return service;
	}

	public StoreForkliftManageService getService() {
		return service;
	}

	@Resource(name="storeForkliftManageService")
	public void setService(StoreForkliftManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
	    return "storeManage/mauForkliftEdit";
	}
	@RequestMapping({"toDelete"})
	@ResponseBody
	public String toDelete(String id){
		//String[] str=id.split(",");
		service.delete(id);
		return "success"; 
	}
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/mauForkliftForm";
	} 
	@RequestMapping({"toShowPage"})
	public String toShowPage(){
		return "storeManage/mauForkliftList";
	}
	@RequestMapping({ "toListPage" })
	@ResponseBody
	public Page toListPage(HttpServletRequest request, Page pageParam,MauForklift manuForklift) throws ParseException{
	    String startTime=request.getParameter("startTime");
	    String endTime= request.getParameter("endTime");
	    Page page= service.queryForkliftPageService(pageParam, manuForklift,startTime,endTime);
	    return page;
	} 
	@RequestMapping({"toSaveData"})
	@ResponseBody 
	public String toSaveData(MauForklift manuForklift){ 
		return service.saveOrUpdate(manuForklift);
	} 
	
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getFtype"})
	@ResponseBody
	public Map[] getFtype(){
		Map[] ftype = service.getFtype();
		return ftype;
	}
	
}
