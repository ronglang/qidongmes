package com.css.business.web.subsysplan.plaManage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaProductOrder;
import com.css.business.web.subsysplan.plaManage.service.PlaProductOrderManageService;
import com.css.business.web.subsysplan.plaManage.service.PlaWeekSeqHoursManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
@Controller
@RequestMapping("/plaProductOrderManageAction")
public class PlaProductOrderManageAction extends BaseSpringSupportAction<PlaProductOrder, PlaProductOrderManageService> {
	
	//@Autowired
	private PlaProductOrderManageService service;
	
	@Resource(name="plaWeekSeqHoursManageService")
	private PlaWeekSeqHoursManageService ser;
	
	@Override
	public PlaProductOrderManageService getEntityManager() {
		return service;
	}

	public PlaProductOrderManageService getService() {
		return service;
	}

	@Resource(name="plaProductOrderManageService")
	public void setService(PlaProductOrderManageService service) {
		this.service = service;
	}
	
	private Gson gson = new Gson();
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaProductOrderEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaProductOrderForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaProductOrderList";
	}
	
	@RequestMapping({"toOrderTable"})
	public String toOrderTable(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaProductOrderTable";
	}
	
	/**
	 * 查询生产令，展示到前台
	 * @param request
	 * @param p
	 * @return
	 */
	@RequestMapping({"getOrderTable"})
	@ResponseBody
	public Page getOrderTable(HttpServletRequest request,Page p){
		Page page = service.getPlaProductOrder(p);
		return page;
	}
	
	/**
	 * 分解生产令
	 * 查询物料是否充足
	 * 计算生产中各个工序所需要用的时间
	 * @return
	 */
	@RequestMapping({"getDecompose"})
	@ResponseBody
	public HashMap<String, Object> getDecompose(HttpServletRequest req,String li,ArrayList<Object> list){
		HashMap<String, Object> map;
		try {
			SysUser user = SessionUtils.getUser(req);
			map = service.getMater(li);
			if((boolean) map.get("success")){
				ser.savePlaWeekSeqHours_3(user,li,list);
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage());
		}
		
	}
	
	@RequestMapping({"test"})
	@ResponseBody
	public String get(){
		service.test();
		return null;
	}
	
	/**
	 * 
	 * @Description: 分页查询   
	 * @param req
	 * @param page 分页
	 * @param param 条件查询
	 * @return
	 */
	@RequestMapping("getPageList")
	@ResponseBody
	public Page getPageList(HttpServletRequest req,Page page ,String param){
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Page queryPage = page;
		try {
			queryPage = service.getPageList(page,map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return queryPage;
	}

}
