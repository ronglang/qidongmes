package com.css.business.web.sysManage.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.sysManage.bean.SysContactGroup;
import com.css.business.web.sysManage.service.SysContactGroupManageService;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
@Controller
@RequestMapping("/sysContactGroupManageAction")
public class SysContactGroupManageAction extends BaseSpringSupportAction<SysContactGroup, SysContactGroupManageService> {
	
	//@Autowired
	private SysContactGroupManageService service;
	
	@Override
	public SysContactGroupManageService getEntityManager() {
		return service;
	}

	public SysContactGroupManageService getService() {
		return service;
	}

	@Resource(name="sysContactGroupManageService")
	public void setService(SysContactGroupManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysContactGroupEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysContactGroupForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysContactGroupList";
	}
	
	

/**
	 * 生成树
	 * @return
	 * @throws Exception
	 */
	@RequestMapping({ "nodeTree" })
	@ResponseBody
	public HashMap<String, Object> nodeTree(HttpServletRequest request,String qcolName,String qcolValue)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().getTree(qcolName,qcolValue);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
		
	}}