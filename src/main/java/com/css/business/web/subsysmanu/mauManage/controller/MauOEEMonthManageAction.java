package com.css.business.web.subsysmanu.mauManage.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauOEEMonth;
import com.css.business.web.subsysmanu.mauManage.service.MauOEEMonthManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * 
* @Title: MauOEEMonthManageAction.java   
* @Package com.css.business.web.subsysmanu.mauManage.controller   
* @Description: OEE 月份统计  
* @author   rb
* @date 2017年9月29日 下午4:21:59   
* @company  SMTC
 */
@Controller
@RequestMapping("/mauOEEMonthManageAction")
public class MauOEEMonthManageAction extends BaseSpringSupportAction<MauOEEMonth, MauOEEMonthManageService> {
	
	//@Autowired
	private MauOEEMonthManageService service;
	
	@Override
	public MauOEEMonthManageService getEntityManager() {
		return service;
	}

	public MauOEEMonthManageService getService() {
		return service;
	}

	@Resource(name="mauOEEMonthManageService")
	public void setService(MauOEEMonthManageService service) {
		this.service = service;
	}
	
	private Gson gson = new Gson();
	
	
	/**
	 * 
	 * @Description:分页查询
	 * @param request
	 * @param page
	 * @param param
	 * @return
	 */
	@RequestMapping("getListPage")
	@ResponseBody
	public Page getListPage(HttpServletRequest request,Page page,String param){
		Map<String,String>map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Page queryPage = service.getListPage(page,map);
		return queryPage;
	}
}
