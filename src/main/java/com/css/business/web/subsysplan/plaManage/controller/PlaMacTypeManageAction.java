package com.css.business.web.subsysplan.plaManage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.subsysplan.bean.PlaMacType;
import com.css.business.web.subsysplan.plaManage.service.PlaMacTypeManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.dao.support.Page;
/**
 * 
* @Title: PlaMacTypeManageAction.java   
* @Package com.css.business.web.subsysplan.plaManage.controller   
* @Description:机台信息   
* @author   rb
* @date 2017年7月11日 上午9:32:42   
* @company  SMTC
 */
@Controller
@RequestMapping("/plaMacTypeManageAction")
@Deprecated
public class PlaMacTypeManageAction extends BaseSpringSupportAction<PlaMacType, PlaMacTypeManageService> {
	
	//@Autowired
	private PlaMacTypeManageService service;
	
	@Override
	public PlaMacTypeManageService getEntityManager() {
		return service;
	}

	public PlaMacTypeManageService getService() {
		return service;
	}

	@Resource(name="plaMacTypeManageService")
	public void setService(PlaMacTypeManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMacTypeEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMacTypeForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "plaManage/plaMacTypeList";
	}
	
	/**
	 * 
	 * @Description: 各机台数量   
	 * @param area 区域
	 * @return
	 */
	@RequestMapping("getMacCount")
	@ResponseBody
	public Map<String,String>getMacCount(String area){
		Map<String,String>map = new HashMap<String, String>();
		Long errorCount = service.queryCount("故障",area);
		Long allCount = service.queryCount("",area);
		Long stopCount = service.queryCount("停机",area);
		Long serviceCount = service.queryCount("开机",area);
		map.put("errorCount", errorCount+"");
		map.put("allCount", allCount+"");
		map.put("stopCount", stopCount+"");
		map.put("serviceCount", serviceCount+"");
		return map;
	}
	/**
	 * 
	 * @Description: 去区域机台电子看板    
	 * @param req
	 * @param area 区域
	 * @return
	 */
	@RequestMapping("toAreaBoard")
	public String toAreaBoard(HttpServletRequest req, String area){
		req.setAttribute("area", area);
		return "mauManage/mauareadisplay/areatop";
	}
}
