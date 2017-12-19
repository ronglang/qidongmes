package com.css.business.web.subsysstore.storeManage.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysstore.bean.StoreLockControl;
import com.css.business.web.subsysstore.storeManage.service.StoreLockControlManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
@Controller
@RequestMapping("/storeLockControlManageAction")
public class StoreLockControlManageAction extends BaseSpringSupportAction<StoreLockControl, StoreLockControlManageService> {

	@Autowired
	private StoreLockControlManageService storeLockControlManageService;
	
	@Override
	public StoreLockControlManageService getEntityManager() {
		return storeLockControlManageService;
	}
	
	@RequestMapping({"lockControlPage"})
	public String lockControlPage(){
		return "storeManage/lockControl/storeLockControl";
	}
	
	@RequestMapping({"lockControlPageData"})
	@ResponseBody
	public Page lockControlPageData(Page page,HttpServletRequest request,StoreLockControl entity){
		try {
			page = super.dataGridPage(request, page, entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	@RequestMapping({"edit"})
	public String lockControlEditPage(HttpServletRequest request,Integer id){
		request.setAttribute("id", id);
		return "storeManage/lockControl/storeLockControlEdit";
	}
	@RequestMapping({"editData"})
	@ResponseBody
	public HashMap<String,Object> getBean(HttpServletRequest request,Integer id ){
		StoreLockControl bean = storeLockControlManageService.get(id);
		return JsonWrapper.successWrapper(bean);
	}
	
	@RequestMapping({"saveBean"})
	@ResponseBody
	public HashMap<String,Object> saveBean(HttpServletRequest request,StoreLockControl bean){
		bean.setAmount(bean.getAls()*bean.getAxisNumber());
		try {
			storeLockControlManageService.save(bean);
			return JsonWrapper.successWrapper("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonWrapper.failureWrapper("保存失败");
		
		
	}
}
