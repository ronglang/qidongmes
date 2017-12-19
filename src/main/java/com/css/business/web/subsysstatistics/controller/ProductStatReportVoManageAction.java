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
import com.css.business.web.subsysstatistics.service.ProductStatReportVoManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 
* @Title: ProductStatReportVoManageAction.java   
* @Package com.css.business.web.subsysstatistics.controller   
* @Description: 生产报表  
* @author   rb
* @date 2017年8月4日 下午3:04:03   
* @company  SMTC
 */
@Controller
@RequestMapping("/productStatReportVoManageAction")
public class ProductStatReportVoManageAction extends BaseSpringSupportAction< StatCourse,ProductStatReportVoManageService> {
	@Resource(name="productStatReportVoManageService")
	private ProductStatReportVoManageService service;

	/* 
	 * @return
	 */
	@Override
	public ProductStatReportVoManageService getEntityManager() {
		// TODO Auto-generated method stub
		return service;
	}
	Gson gson = new Gson(); 
	
	@RequestMapping({"productstat"})
	public String toProductStat(){
		return "totalQuery/productStat/productStatList";
	}
	@RequestMapping({"getProductStat"})
	@ResponseBody
	public Page getProductStatReportVoPage(HttpServletRequest request,Page page, String param){
		 Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		 Page queryPage = page;
		 try {
			 if(map != null){
				 queryPage = service.getReportPage(map, page);
			 }else{
				 queryPage = service.getReportPage(new HashMap<String,String>(), page);
			 }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		 return queryPage;
	}
	
	
	@RequestMapping({"exportProductStat"})
	@ResponseBody
	public HashMap<String, Object> exportProductStat(HttpServletRequest req,HttpServletResponse response,Page page,String param){
		Gson gson = new Gson();
		Map<String,String> map = gson.fromJson(param, new TypeToken<Map<String,String>>(){}.getType());
		try {
			service.exportProductStatService(response, page, map);
			return JsonWrapper.successWrapper("成功"); 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg(e.getMessage()); 
		}
		
    }
	
	
	
	
	
	
	
	
	
	
}
