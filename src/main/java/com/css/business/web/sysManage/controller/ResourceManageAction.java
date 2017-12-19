package com.css.business.web.sysManage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.css.business.web.sysManage.bean.MenuTree;
import com.css.business.web.sysManage.service.ResourceManageService;
import com.css.commcon.util.SessionUtils;
import com.css.common.annotation.Auth;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
import com.css.common.web.syscommon.controller.support.JsonWrapper;


/**
 * 
 *  菜单管理  controller   
 *  
 * @author aliang    
 * @version 1.0  
 * @created 2015年4月16日 下午2:52:51
 */
@Controller
@RequestMapping("/resourceManager")
public class ResourceManageAction  extends BaseSpringSupportAction<com.css.business.web.sysManage.bean.SysMenu, ResourceManageService> {
	
	@Resource(name="resourceManageService")
	private ResourceManageService service;
	
	
	/**
	 * 
	 * 查询当前用户对应的菜单
	 * 
	 * @param user
	 * @return
	 */
	@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	@RequestMapping("/queryUserMenu")
	@ResponseBody
	public java.util.List<MenuTree> queryUserMenu(HttpServletRequest request){
		java.util.List<MenuTree> menus=null;
		try {
			menus=SessionUtils.getUser(request).getUserMenuTree();
			System.out.println(1);
		} catch (Exception e) {
			log.error("生成用户菜单错误，请联系管理员",e);
		}
		return menus;
	}
	
	

	/**
	 * 查询所有资源 并以树形结构输出
	 * @return
	 */
	@Auth(verifyLogin=false,verifyURL=false,verifyToken=false)
	@RequestMapping("/queryResourceAll")
	@ResponseBody
	public Map<String, Object> queryResourceAll(){
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			java.util.List<MenuTree>  menus=service.queryResourceAll();
			map.put("Rows", menus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 资源管理首页
	 * @return
	 */
	@RequestMapping("/toListPage")
	public String toListPage(){
		return "resourceManage/index";
	}
	
	/**
	 * 资源管理添加，编辑页面
	 * @return
	 */
	@RequestMapping("/toSysresourceAddEditPage")
	public String toSysresourceAddEditPage(){
		return "resourceManage/edit";
	}
	
	
	@Override
	public ResourceManageService getEntityManager() {
		return service;
	} 

	/**
	 * 查询所有菜单资源 并以树形结构输出
	 * @return
	 */
	@RequestMapping({ "nodeTree" })
	@ResponseBody
	public HashMap<String, Object> nodeTree(String qcolName,String qcolValue)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().getTree(qcolName,qcolValue);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
		
	}

	/**
	 * 查询所有菜单资源 并以树形结构输出
	 * @return
	 */
	@RequestMapping({ "nodeTrees" })
	@ResponseBody
	public HashMap<String, Object> nodeTrees(String qcolName,String qcolValue,String idlist)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().getTrees(qcolName,qcolValue,idlist);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
		
	}
	
	/**
	 * 查询所有菜单资源 并以树形结构输出
	 * @return
	 */
	@RequestMapping({ "nodeTreesId" })
	@ResponseBody
	public HashMap<String, Object> nodeTreesId(String qcolName,String qcolValue,String idlist)  {
		List<TreeVO> list;
		try {
			list = this.getEntityManager().getTreesId(qcolName,qcolValue,idlist);
			return JsonWrapper.successWrapper(list);
		} catch (Exception e) {
			this.log.error("查询树数据错误",e);
			return JsonWrapper.successWrapper("查询树数据错误");
		}
		
	}
	
	@RequestMapping("/toResoureConfigPage")
	public String toResoureConfigPage(){
		return "resourceManage/resoureConfigEdit";
	}
		
	}

