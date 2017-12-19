package com.css.business.web.subsyscraft.craManage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsyscraft.bean.CraBomTheory;
import com.css.business.web.subsyscraft.craManage.service.CraBomTheoryManageService;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.commcon.util.SessionUtils;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/craBomTheoryManageAction")
public class CraBomTheoryManageAction extends BaseSpringSupportAction<CraBomTheory, CraBomTheoryManageService> {
	
	@Resource(name="craBomTheoryManageService")
	private CraBomTheoryManageService service;
	
	@Override
	public CraBomTheoryManageService getEntityManager() {
		return service;
	}

	public CraBomTheoryManageService getService() {
		return service;
	}

	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craBomTheoryEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craBomTheoryForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craBomTheoryList";
	}
	
	@RequestMapping({ "toTablePage" })
	public String toTablePage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "craManage/craBomTheoryTable";
	}
	
	/*@RequestMapping({"getTheroyList"})
	@ResponseBody
	public HashMap<String, Object> getTheroyList(){
		String msg = service.saveCraBomTheory();
		return JsonWrapper.successWrapper(null, msg);
	}*/
	
	@RequestMapping({"getTheroyGrid"})
	@ResponseBody
	public Page getTheroyGrid(Page p,String paramType,String thirdProp,String proGgxh,String proColor){
		try {
			Page page = service.getTheoryGrid(p,paramType,thirdProp,proGgxh,proColor);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping({"saveTheroy"})
	@ResponseBody
	public HashMap<String, Object> saveTheroy(HttpServletRequest req,Integer id,String value){
		// TODO 
		SysUser user = SessionUtils.getUser(req);
		CraBomTheory craBomTheory = new CraBomTheory();
		try {
			craBomTheory.setId(id);
			craBomTheory.setParamValue(value);
			craBomTheory.setCreateBy(user.getAccount());
			service.updateCraBomTheory(craBomTheory);
			return JsonWrapper.successWrapper(craBomTheory, "");
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(craBomTheory, "");
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getParamType"})
	@ResponseBody
	public Map[] getParamType(){
		Map[] map = service.getParamType();
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getSEQName"})
	@ResponseBody
	public Map[] getSEQName(){
		Map[] map = service.getSEQName();
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getProGgxh"})
	@ResponseBody
	public Map[] getProGgxh(){
		Map[] map = service.getProGgxh();
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping({"getProColor"})
	@ResponseBody
	public Map[] getProColor(String proGgxh){
		Map[] map = service.getProColor(proGgxh);
		return map;
	}
	
	/**
	 * 初始化 BOM参数表中的数据
	 */
	@RequestMapping({"initializeTheroy"})
	@ResponseBody
	public HashMap<String, Object> initializeTheroy(){
		service.initializeTheroy();
		return JsonWrapper.successWrapper(null, "");
	}
	
	/**
	 * 根据thirdprop 查询工序的名称
	 */
	@RequestMapping({"getSeqName"})
	@ResponseBody
	public String getSeqName(String thirdprop){
		String seqName = service.getSeqName(thirdprop);
		return seqName;
	}

	
}
