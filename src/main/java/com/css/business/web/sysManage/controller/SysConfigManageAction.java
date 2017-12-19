package com.css.business.web.sysManage.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.sysManage.bean.SysConfig;
import com.css.business.web.sysManage.service.SysConfigManageService;
import com.css.common.util.Constant;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
@Controller
@RequestMapping("/sysConfigManageAction")
public class SysConfigManageAction extends BaseSpringSupportAction<SysConfig, SysConfigManageService> {
	
	//@Autowired
	private SysConfigManageService service;
	
	@Override
	public SysConfigManageService getEntityManager() {
		return service;
	}

	public SysConfigManageService getService() {
		return service;
	}

	@Resource(name="sysConfigManageService")
	public void setService(SysConfigManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysConfigEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysConfigForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysConfigList";
	}
	
	/**
	 * @TODO: 取系统当前配置的数据年度
	 * @author: zhaichunlei
	 & @DATE : 2016年9月18日
	 * @param request
	 * @return
	 */
	@RequestMapping({ "getCurrentDataYear" })
	@ResponseBody
	public HashMap<String, Object> getCurrentDataYear(HttpServletRequest request)  {
		Integer dataYear = Integer.parseInt(( ((SysConfig)(Constant.CONFIG.get("DATA_YEAR"))).getValue()).toString());
		return JsonWrapper.successWrapper(dataYear);
	}
}
