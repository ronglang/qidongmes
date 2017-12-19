package com.css.business.web.subsysstatistics.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysstatistics.bean.StatCourse;
import com.css.business.web.subsysstatistics.service.ExceptionStatVoManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
@Controller
@RequestMapping("/exceptionStatVoManageAction")
public class ExceptionStatVoManageAction extends BaseSpringSupportAction< StatCourse, ExceptionStatVoManageService> {
	@Resource(name="exceptionStatVoManageService")
	private ExceptionStatVoManageService service;

	/* 
	 * @return
	 */
	@Override
	public ExceptionStatVoManageService getEntityManager() {
		// TODO Auto-generated method stub
		return service;
	}
	Gson gson = new Gson(); 
	
	/*异常统计报表页面*/
	@RequestMapping({"exceptionStat"})
	public String toExceptionList(){
		return "totalQuery/exceptionForm/exceptionList";
	}
	
	/**
	 * 
	 * @Description:    ExceptionStatVo 分页展示
	 * @param request
	 * @param param
	 * @param page
	 * @return
	 */
	@RequestMapping("getExceptionVoPage")
	@ResponseBody
	public Page getExceptionVoPage(HttpServletRequest request ,String param,Page page){
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		Page queryPage = page;
		try {
			if (map != null) {
				queryPage = service.getExceptionVoPage(map, page);
			} else {
				queryPage = service.getExceptionVoPage(new HashMap<String, String>(), page);
			}
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return queryPage;
	}
	
	
	@RequestMapping({"exportExceptionList"})
	@ResponseBody
	public HashMap<String, Object> exportExceptionList(HttpServletRequest req,HttpServletResponse response,Page page,String param){
		Gson gson = new Gson();
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		try {
			service.exportExceptionListService(response, page, map);
			return JsonWrapper.successWrapper("成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage()); 
		}
	
	}
	
	

}
