package com.css.business.web.sysManage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.sysManage.bean.MenuTree;
import com.css.business.web.sysManage.bean.SysMenu;
import com.css.business.web.sysManage.bean.SysUser;
import com.css.business.web.sysManage.dao.ResourceManageDAO;
import com.css.common.enums.ServiceErrorCode;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.support.QueryCondition;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

/**
 * 
 * TODO 资源业务类8
 * 
 * @author huanghao 2015年6月30日上午9:20:24
 */
@Service("resourceManageService")
public class ResourceManageService
		extends
		BaseEntityManageImpl<com.css.business.web.sysManage.bean.SysMenu, ResourceManageDAO> {

	@Resource(name = "resourceManageDAO")
	private ResourceManageDAO dao;
	
	//private ConcurrentHashMap<String,List<SysMenu>> portalmenus = new ConcurrentHashMap<String,List<SysMenu>> ();
	private ConcurrentHashMap<String,List<SysMenu>> submenus = new ConcurrentHashMap<String,List<SysMenu>> ();
	
	@Resource(name = "sysRoleMenuManageService")
	private SysRoleMenuManageService sysRoleMenuManageService;

	public ResourceManageDAO getEntityDaoInf() {
		return dao;
	}

	/**
	 * 查询用户菜单资 源
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public HashMap <String,Object> querySysMenuBySysUser(SysUser user)
			throws Exception {
		List<SysMenu> list = null;
		List<SysMenu> sysReourcelist = null;
		if (user.isAdmin()) {
			QueryCondition qc = new QueryCondition(SysMenu.class);
			qc.addCondition("menuType","=", "1");
			qc.setOrderBy("sort");
			//list = this.queryAll();
			list = this.query(qc);
			sysReourcelist = list;
		} else {
			//sysReourcelist = this.queryAll();
			QueryCondition qc = new QueryCondition(SysMenu.class);
			qc.addCondition("menuType","=", "1");
			qc.setOrderBy("sort");
			sysReourcelist = this.query(qc);
			list = this.dao.querySysMenuBySysUserId(user.getId());
		}
		List<MenuTree> usermenu = setMenuTree(list,"-1");
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(ServiceErrorCode.USER_MENU.getCode(), usermenu);
		map.put("menu", usermenu);
		map.put(ServiceErrorCode.USER_RESOURCE.getCode(), list);
		map.put(ServiceErrorCode.SYSTEM_REOURCE_LIST.getCode(), sysReourcelist);
		return map;
	}
	
	/**
	 * 查询用户按钮资 源
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public HashMap <String,String> querySysMenuBtnBySysUser(SysUser user)
			throws Exception {
		List<SysMenu> list =  this.dao.querySysMenuBtnBySysUser(user.getId());
		//没有权限的按钮
		List<SysMenu> list_not =  this.dao.querySysMenuBtnBySysUser_not(user.getId());
		
		HashMap<String,String> map = new HashMap<String,String>();
		StringBuffer sb = new StringBuffer("");
		StringBuffer sb_not = new StringBuffer("");
		for(SysMenu s : list){
			sb.append("'"+s.getCode()+"',");
		}
		for(SysMenu s : list_not){
			sb_not.append("'"+s.getCode()+"',");
		}
		if(sb.length() > 0) map.put("menu_btn", sb.substring(0, sb.length() - 1));
		else	map.put("menu_btn", sb.toString());
		if(sb_not.length() > 0) map.put("menu_btn_not", sb_not.substring(0, sb_not.length() - 1));
		else 	map.put("menu_btn_not", sb_not.toString());
		return map;
	}

	
	/**
	 * 查询子级
	 * List<SysMenu>
	 * 2015年9月1日 下午4:36:27
	 * @param pid
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<SysMenu> queryMenuByPcode(String code)throws Exception{
		List<SysMenu> list = submenus.get(code);
		if(list != null && list.size() > 0){
			return list;
		}else{
			QueryCondition qc = new QueryCondition(SysMenu.class);
			qc.addCondition("pcode", "=", code);
			qc.setOrderBy("sort");
			list = this.query(qc);
			submenus.put(code, list);
		}
		return list;
	}
	
	
	/**
	 * 根据用户查询系统资源
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<SysMenu> searchSysMenuBySysUser(SysUser user)
			throws Exception {
		List<SysMenu> list = null;
		if (user.isAdmin()) {
			list = this.queryAll();
		} else {
			list = this.dao.querySysMenuBySysUserId(user.getId());
		}
		return list;
	}
	
	
	private List<MenuTree> setMenuTree(List<SysMenu> list,String pcode) {
		System.setProperty("java.util.Arrays.useLegacyMergeSort", "true");
		Collections.sort(list);
		List<MenuTree> pmenu = new ArrayList<MenuTree>();
		List<MenuTree> smenu = new ArrayList<MenuTree>();

		for (SysMenu s : list) {
			if("1".equals(s.getMenuType()) ){//菜单资源
				MenuTree menuTree = new MenuTree();
				menuTree.setId(s.getCode());
				menuTree.setText(s.getName());
				menuTree.setImage(s.getImage());
				menuTree.setUrl(s.getUrl());
				menuTree.setPid(s.getPcode());
				menuTree.setIsexpand(false);
				menuTree.setSort(s.getSort());
				menuTree.setName(s.getName());
				menuTree.setMenuType("1".equals(s.getMenuType()) ? "菜单" : "按钮"); 
				if (pcode.equals(s.getPcode()) ) {
					pmenu.add(menuTree);
				} else {
					smenu.add(menuTree);
				}
			}
		}

		for (MenuTree pm : pmenu) {
			setMenuTree(pm, smenu);
		}

		return pmenu;
	}

	private void setMenuTree(MenuTree pm, List<MenuTree> smenu) {
		for (MenuTree sm : smenu) {
			String smPid = sm.getPid();
			String pmId = pm.getId();
			if (smPid.equals(pmId)) {
				if (pm.getChildren() == null) {
					pm.setChildren(new ArrayList<MenuTree>());
					pm.getChildren().add(sm);
				} else {
					pm.getChildren().add(sm);
				}
				setMenuTree(sm, smenu);
			}
		}
	}

	/**
	 * 查询出全部菜单并转换成树形结构
	 * 
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public java.util.List<MenuTree> queryResourceAll() throws Exception {
		return setMenuTree(this.queryAll(),"-1");
	}

	// 查询树
	@Transactional(readOnly = true)
	public List<TreeVO> getTree(String colName, String colValue)
			throws Exception {
		QueryCondition qc = new QueryCondition(SysMenu.class);
		if (!StringUtil.isEmpty(colName)) {
			qc.addCondition(colName, "like", colValue + "%");
		}
		List<SysMenu> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for (SysMenu s : list) {
			TreeVO vo = new TreeVO(s.getCode().toString(), s.getName(),s.getPcode());
			tree.add(vo);
		}
		return tree;
	}

	// 查询树
	@Transactional(readOnly = true)
	public List<TreeVO> getTrees(String colName, String colValue, String idlist)
			throws Exception {
		List<String> idslist = new ArrayList<String>();
		if (!StringUtil.isEmpty(idlist)) {
			String[] idlists = idlist.split(",");
			for (String string : idlists) {
				List<String> idslists = sysRoleMenuManageService.queryById(string);
				if (idslists != null) {
					idslist.addAll(idslists);
				}
			}
		}

		QueryCondition qc = new QueryCondition(SysMenu.class);
		if (!StringUtil.isEmpty(colName)) {
			qc.addCondition(colName, "like", colValue + "%");
		}
		List<SysMenu> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for (SysMenu s : list) {
			TreeVO vo = new TreeVO(s.getCode().toString(), s.getName(),s.getPcode());
			if (idslist.contains(s.getId())) {
				vo.setChecked(true);
			}
			tree.add(vo);
		}
		return tree;
	}

	
	@Transactional(readOnly = true)
	public List<TreeVO> getTreesId(String colName, String colValue, String idlist)
			throws Exception {
		List<String> idslist = new ArrayList<String>();
		if (!StringUtil.isEmpty(idlist)) {
			String[] idlists = idlist.split(",");
			for (String string : idlists) {
				List<String> idslists = sysRoleMenuManageService.queryById(string);
				if (idslists != null) {
					idslist.addAll(idslists);
				}
			}
		}

		QueryCondition qc = new QueryCondition(SysMenu.class);
		if (!StringUtil.isEmpty(colName)) {
			qc.addCondition(colName, "like", colValue + "%");
		}
		List<SysMenu> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for (SysMenu s : list) {
			TreeVO vo = new TreeVO(s.getCode()+"",s.getName(), s.getPcode().toString(),s.getId().toString());
			if (idslist.contains(s.getId()+"")) {
				vo.setChecked(true);
			}
			tree.add(vo);
		}
		return tree;
	}
}
