package com.css.business.web.sysManage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.sysManage.bean.SysMenu;
import com.css.business.web.sysManage.bean.SysRole;
import com.css.business.web.sysManage.bean.SysRoleMenu;
import com.css.business.web.sysManage.dao.SysRoleManageDAO;
import com.css.business.web.sysManage.dao.SysRoleMenuManageDAO;
import com.css.business.web.sysManage.dao.SysUserManageDAO;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.bean.TreeVO;
import com.css.common.web.syscommon.controller.support.QueryCondition;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("sysRoleManageService")
@SuppressWarnings("all")
public class SysRoleManageService extends
		BaseEntityManageImpl<SysRole, SysRoleManageDAO> {
	@Resource(name = "sysRoleManageDAO")
	// @Autowired
	private SysRoleManageDAO dao;
	
	@Resource(name="sysRoleMenuManageDAO")
	//@Autowired
	private SysRoleMenuManageDAO menuDao;
	
	@Resource(name="sysUserManageDAO")
	//@Autowired
	private SysUserManageDAO userDao;
	
	@Resource(name = "sysRoleMenuManageService")
	private SysRoleMenuManageService SysRoleMenuManageService;
	
	@Resource(name = "sysUserRoleManageService")
	private SysUserRoleManageService sysUserRoleManageService;
	
	@Resource(name = "sysMenuManageService")
	private SysMenuManageService resourceService;
	
	@Override
	public SysRoleManageDAO getEntityDaoInf() {
		return dao;
	}

	//新建、更新角色以及角色权限
	@Transactional(readOnly = false)
	public void saveRoleInfo(String roleName,String roleRemark,String datas,Integer params) {
		SysRole sysRole=null;
		sysRole = new SysRole();
		sysRole.setName(roleName);
		sysRole.setRemark(roleRemark);
		if(params==null){//新建角色
			dao.saveRoleInfo(sysRole);
			if(!StringUtil.isEmpty(datas)){
				String[] args = datas.split(",");//截取字符串，获取权限的列表数据
				for(int x=0;x<args.length;x++){
					if(args[x] == null || "null".equals(args[x]))continue;
					SysRoleMenu SysRoleMenu = new SysRoleMenu();
					SysRoleMenu.setMenuId(Integer.parseInt(args[x]));
					SysRoleMenu.setRoleId(sysRole.getId());
					dao.saveRoleResource(SysRoleMenu);//保存角色权限到角色-权限表
					}
			}
		}else{//编辑角色
			SysRoleMenuManageService.deletRoleResourceByRoleId(params);// 删除数据
			sysRole.setId(params);
			dao.upDateRole(sysRole);
			if(!StringUtil.isEmpty(datas)){
				String[] args = datas.split(",");//截取字符串，获取权限的列表数据
				for(int x=0;x<args.length;x++){
					if(args[x] == null || "null".equals(args[x]))continue;
					SysRoleMenu SysRoleMenu = new SysRoleMenu();
					SysRoleMenu.setMenuId(Integer.parseInt(args[x]));
					SysRoleMenu.setRoleId(sysRole.getId());
					dao.saveRoleResource(SysRoleMenu);//保存角色权限到角色-权限表
					}
			}
		}
	}

	@Transactional(readOnly = true)
	public List<TreeVO> getTree(String code, String dvalue) throws Exception {
		QueryCondition qc = new QueryCondition(SysRole.class);
		if(!StringUtil.isEmpty(code)){
			qc.addCondition(code, "=",dvalue);
		}
		List <SysRole> list = this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for(SysRole s : list){
			TreeVO vo = new TreeVO(s.getId().toString(),s.getName().toString());
			tree.add(vo);
		}
		return tree;
	}

	@Transactional(readOnly = true)
	public List<TreeVO> getTrees(String colName, String colValue, String id) throws Exception {
		List<String> idslist = new ArrayList<String>();
		if(!StringUtil.isEmpty(id)){
			List<String> idslists = sysUserRoleManageService.queryById(id);
				if(idslists!=null){
					idslist.addAll(idslists);
				}
			}
		QueryCondition qc = new QueryCondition(SysRole.class);
		if(!StringUtil.isEmpty(colName)){
			qc.addCondition(colName, "like",colValue+"%");
		}
		List<SysRole> list=this.query(qc);
		List<TreeVO> tree = new ArrayList<TreeVO>();
		for(SysRole s : list){
			TreeVO vo = new TreeVO(s.getId().toString(),s.getName());
			if(idslist.contains(s.getId().toString())){
				vo.setChecked(true);
			}
			tree.add(vo);
		}
		return tree;
	}

	//根据roleId获取角色对象
	@Transactional(readOnly = true)
	public SysRole findEntityByRoleId(Integer roleId) {
		return dao.findEntityByRoleId(roleId);
	}
	
	
	//根据用户roleID获取所有的资源列表
	@Transactional(readOnly = true)
	public String findResourceById(Integer roleId) {
		String string = "";
		List<SysRoleMenu> list = SysRoleMenuManageService.QueryLists(roleId);
		if(list != null){
			for(SysRoleMenu entity:list){
				SysMenu resource = resourceService.findResourceEntityById(entity.getMenuId());
				if(resource != null){
					string = string+resource.getName()+"\n";
				}
			}
		}
		return string;
	}
	
	/**
	 * 删除
	 * @param request
	 * @param vo
	 * @return
	 * @throws Exception 
	 */
	@Transactional(readOnly=false)
	public void delete2(SysRole role, String ids) throws Exception{
		if(ids == null || ids.length() == 0 ) return;
		String is[] = ids.split(",");
		for(String idst : is){
			if(idst == null || idst.length() == 0 ) continue;
			Integer Id = Integer.parseInt(idst);
			SysRole r = dao.get(Id);
			if(r != null){
				Integer roleId = r.getId();
				Integer id = r.getId();
				userDao.deleteBySql("delete from sys_user_role where role_id=?",roleId);
				menuDao.deleteBySql("delete from sys_role_menu where role_id=?",roleId);
				dao.deleteBySql("delete from sys_role where id=? ",id);
				
				dao.removeById(id);
			}
		}
	}
}
