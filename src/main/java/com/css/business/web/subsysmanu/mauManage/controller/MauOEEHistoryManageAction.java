package com.css.business.web.subsysmanu.mauManage.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysmanu.bean.MauOEEHistory;
import com.css.business.web.subsysmanu.mauManage.service.MauOEEHistoryManageService;
import com.css.business.web.syswebsoket.bean.EchartsVo;
import com.css.commcon.util.YorkUtil;
import com.css.common.util.DateUtil;
import com.css.common.util.EhCacheFactory;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
import com.css.common.web.syscommon.dao.support.Page;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
@Controller
@RequestMapping("/mauOEEHistoryManageAction")
public class MauOEEHistoryManageAction extends BaseSpringSupportAction<MauOEEHistory, MauOEEHistoryManageService> {
	
	//@Autowired
	private MauOEEHistoryManageService service;
	
	@Override
	public MauOEEHistoryManageService getEntityManager() {
		return service;
	}

	public MauOEEHistoryManageService getService() {
		return service;
	}
	@Resource(name="mauOEEHistoryManageService")
	public void setService(MauOEEHistoryManageService service) {
		this.service = service;
	}
	private Gson gson = new Gson(); 
	
	/** 去列表页面 */
	@RequestMapping("toGetHistoryList")
	public String toGetHistoryList(){
		return "mauManage/mauOEEHistory/mauOEEHistoryList";
	}
	
	/** 实际去看某条记录 */
	@RequestMapping("toHistoryChart")
	public String toHistoryChart(HttpServletRequest request,String id){
		request.setAttribute("id", id);
		return "mauManage/mauOEEHistory/mauOEEHistoryChart";
	}
	
	/**
	 * 
	 * @Description:分页查询
	 * @param request
	 * @param param
	 * @param page
	 * @return
	 */
	@RequestMapping("getListPage")
	@ResponseBody
	public Page getListPage(HttpServletRequest request,String param,Page page){
		Page queryPage = page;
		try {
			queryPage = service.getListPage(page,param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryPage;
	}
	
	
	/**
	 * 
	 * @Description:
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("getById")
	@ResponseBody
	public HashMap<String, Object> getById(HttpServletRequest request,String id){
		try {
			MauOEEHistory mauOEEHistory = service.get(Integer.parseInt(id));
			//request.setAttribute("history", mauOEEHistory);
			return JsonWrapper.successWrapper(mauOEEHistory);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("查找信息异常");
		}
	}
	
	@RequestMapping("getChartsIds")
	@ResponseBody
	public HashMap<String, Object>getChartsIds(HttpServletRequest request,String ids){
		try {
			EchartsVo vo = service.getChartsIds(ids);
			return JsonWrapper.successWrapper(vo);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonWrapper.failureWrapperMsg("查找信息异常");
		}
	}
	
	
	public void setHistory(){
		EhCacheFactory factory  = EhCacheFactory.getInstance();
		String todayMac = factory.getWebsocketCmdCache(YorkUtil.Memcache_当日上传机台);
		List<String>macCodes = gson.fromJson(todayMac, new TypeToken<List<String>>(){}.getType());
		//String[] macCodes = {"DA","DE","VB","ED","SI","RC"};
		for (String code : macCodes) {
			String cmdCache = factory.getWebsocketCmdCache(YorkUtil.Memcache_机台_实时_Echart+"_"+code);
			if (cmdCache != null ) {
				MauOEEHistory h = new MauOEEHistory();
				h.setCourseCode(DateUtil.format(new Date(), "yyyyMMdd"));
				h.setCreateDate(new Date());
				h.setProgxh(DateUtil.format(new Date(), "yyyyMMdd"));
				h.setEchartsVO(cmdCache);
				h.setMacCode(code);
				try {
					service.save(h);
					factory.removeWebsocketCmdCache(YorkUtil.Memcache_机台_实时_Echart+"_"+code);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		factory.removeWebsocketCmdCache(YorkUtil.Memcache_当日上传机台);
		
	}
}
