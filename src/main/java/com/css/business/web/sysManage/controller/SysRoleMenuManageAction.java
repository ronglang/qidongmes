package com.css.business.web.sysManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.sysManage.bean.SysMenuVO;
import com.css.business.web.sysManage.bean.SysRoleMenu;
import com.css.business.web.sysManage.service.SysRoleMenuManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;
@Controller
@RequestMapping("/sysRoleMenuManageAction")
public class SysRoleMenuManageAction extends BaseSpringSupportAction<SysRoleMenu, SysRoleMenuManageService> {
	
	//@Autowired
	private SysRoleMenuManageService service;
	
	@Override
	public SysRoleMenuManageService getEntityManager() {
		return service;
	}

	public SysRoleMenuManageService getService() {
		return service;
	}

	@Resource(name="sysRoleMenuManageService")
	public void setService(SysRoleMenuManageService service) {
		this.service = service;
	}
	
	@RequestMapping({ "toAddEditPage" })
	public String toSaveEditPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysRoleMenuEdit";
	}
	
	@RequestMapping({ "toDetailPage" })
	public String toDetailPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysRoleMenuForm";
	}
	
	@RequestMapping({ "toListPage" })
	public String toListPage(HttpServletRequest request, Integer id){
		request.setAttribute("id", id);
		return "sysManage/sysRoleMenuList";
	}
	
	//编辑角色权限
		@RequestMapping({ "tosysAuthorityManagementEditPage" })
		public String tosysAuthorityManagementEditPage(HttpServletRequest request, String idlist){
			request.setAttribute("idlist", idlist);
			return "sysManage/sysAuthorityManagementEdit";
		}
		
		//编辑角色权限
		@RequestMapping({ "setRoleRource" })
		@ResponseBody
		public HashMap<String, Object> setRoleRource(HttpServletRequest request, String roleIdlist,String newRoleList){
			try {
				service.setRoleRource(roleIdlist,newRoleList);
				return JsonWrapper.successWrapper("权限修改成功");
			} catch (Exception e) {
				log.error("系统异常，请联系管理员！",e);
				return JsonWrapper.failureWrapperMsg("系统异常，请联系管理员！");
			}
			
		}
		
		/**
		 * @TODO:查询角色的权限
		 * @author: zhaichunlei
		 & @DATE : 2016年12月15日
		 * @param request
		 * @param paramPage
		 * @param entity
		 * @return
		 * @throws Exception
		 */
		@RequestMapping({ "queryRoleMenu" })
		@ResponseBody
		public Map<String, Object> queryRoleMenu(HttpServletRequest request,SysMenuVO entity) throws Exception {
			//paramPage = service.queryRoleMenu(request,paramPage, entity);
			List<SysMenuVO> lst =  service.queryRoleMenu(request, entity);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("Rows", lst);
			//return paramPage;
			return map;
		}
}
