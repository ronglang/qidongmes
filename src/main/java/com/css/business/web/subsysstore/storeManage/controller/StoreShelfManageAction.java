package com.css.business.web.subsysstore.storeManage.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysstore.bean.StoreShelf;
import com.css.business.web.subsysstore.storeManage.service.StoreShelfManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/storeShelfManageAction")
public class StoreShelfManageAction extends BaseSpringSupportAction<StoreShelf, StoreShelfManageService> {
	
	//@Autowired
	private StoreShelfManageService service;
	
	@Override
	public StoreShelfManageService getEntityManager() {
		return service;
	}

	public StoreShelfManageService getService() {
		return service;
	}

	@Resource(name="storeShelfManageService")
	public void setService(StoreShelfManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeShelfEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeShelfForm";
	}
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "storeManage/storeShelfList";
	}
	@RequestMapping({"toSaveData"})
	@ResponseBody
	public String toSaveData(HttpServletRequest request,StoreShelf storeShelf,@RequestParam(value="id",required=false) Integer id) throws Exception{
		service.toSaveData(storeShelf);
		return "success";
	}
	@RequestMapping({"toDelete"})
	@ResponseBody
	public String toDelete(String id,HttpServletRequest request) throws Exception{
		service.delete(id);
		return "success";
	}
	@RequestMapping({"toDataGridPage"})
	@ResponseBody
	public Page toDataGridPage(HttpServletRequest request,Integer id,Page page,StoreShelf storeshelf) throws Exception{
		return service.toDateGridPageService(page,storeshelf);
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getFloor"})
	@ResponseBody
	public Map[] getFloor(){
		Map[] floor = service.getFloor();
		return floor;
	}
	
}

