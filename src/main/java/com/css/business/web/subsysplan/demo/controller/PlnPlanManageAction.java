package com.css.business.web.subsysplan.demo.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlnPlan;
import com.css.business.web.subsysplan.demo.service.PlnPlanManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.dubbointerface.subsysstore.StoreManagerInter;
@Controller
@RequestMapping("/plnPlanManagerAction")
public class PlnPlanManageAction extends BaseSpringSupportAction<PlnPlan,PlnPlanManageService>{
	@Resource(name="plnPlanManagerService")
	private PlnPlanManageService service;
	
	@Autowired
	private StoreManagerInter si;
	
	
	//@Reference(version="1.0")
	//@Autowired
	//private ManuManagerInter manuService;
	
	@Override
	public PlnPlanManageService getEntityManager() {
		// TODO Auto-generated method stub
		return service;
	}
	
	@RequestMapping({ "toDemoPage" })
	public String toDemoPage(HttpServletRequest request, Integer id) {
		//String str = manuService.getStrDemao();
		//request.setAttribute("msg",str);
		return "demo/demoShow";
	}
	
	@RequestMapping({"getDemoMsg"})
	@ResponseBody
	public HashMap<String, Object> getDemoMsg(HttpServletRequest request){	
		//String str = manuService.getStrDemao();
		String str = service.getDemoMsg();
		si.saveStore(null);
		return JsonWrapper.successWrapper(str);
        
	}
}
