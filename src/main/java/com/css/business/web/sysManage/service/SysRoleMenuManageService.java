package com.css.business.web.sysManage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.business.web.sysManage.bean.SysMenuVO;
import com.css.business.web.sysManage.bean.SysRoleMenu;
import com.css.business.web.sysManage.dao.SysRoleMenuManageDAO;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("sysRoleMenuManageService")
public class SysRoleMenuManageService extends BaseEntityManageImpl<SysRoleMenu, SysRoleMenuManageDAO> {
	@Resource(name="sysRoleMenuManageDAO")
	//@Autowired
	private SysRoleMenuManageDAO dao;
	
	
	@Override
	public SysRoleMenuManageDAO getEntityDaoInf() {
		return dao;
	}


	//根据角色ID查询角色权限表的数据
	public List<SysRoleMenu> QueryLists(Integer params) {
		return dao.QueryLists(params);
	}

	//根据角色ID删除角色权限表的数据
	@Transactional(readOnly = false)
	public void deletRoleResourceByRoleId(Integer roleId) {
		dao.deletRoleResourceByRoleId(roleId);
		
	}

	/**
	 * 
	 * @param roleIdlist  被选中的数据的roleId集合
	 * @param newRoleList 新的权限集合
	 */
	@Transactional(readOnly = false)
	public void setRoleRource(String roleIdlist,String newRoleList) {
		String[] args1 = roleIdlist.split(",");//得到选中需要操作的list列表的roleId
		String[] args2 =null;
		if(newRoleList!=""){
			args2 = newRoleList.split(",");//得到新的权限列表数据
		}
		
		for(int i=0;i<args1.length;i++){
			//删除旧的权限数据
			dao.deletRoleResourceByRoleId(Integer.parseInt(args1[i]));//根据roleId删除权限表中相应数据
		}
		
		/*Session session = this.dao.getSessionFactory().getCurrentSession();
		Transaction tx= session.beginTransaction();*/
		if(newRoleList!=""){
			for(int i=0;i<args1.length;i++){
				for(int x=0;x<args2.length;x++){
					//添加新的权限
					SysRoleMenu SysRoleMenu = new SysRoleMenu();
					SysRoleMenu.setRoleId(Integer.parseInt(args1[i]));
					SysRoleMenu.setMenuId(Integer.parseInt(args2[x]));
					/*session.save(SysRoleMenu);*/
					dao.save(SysRoleMenu);
				}
			}
		}
	/*	tx.commit();
		session.close();*/
	}

	//根据roleId查询出resourceId并封装为集合返回
	@Transactional(readOnly = true)
	public List<String> queryById(String roleId) {
		if(StringUtil.isEmpty(roleId) || "null".equals(roleId)){
			return null;
		}
		List<SysRoleMenu> list = dao.QueryLists(Integer.parseInt(roleId));
		List<String> idlist =new ArrayList<String>();
		if(list.size()>0){
			for(SysRoleMenu SysRoleMenu:list){
				 idlist.add(SysRoleMenu.getMenuId().toString());
			}
			return idlist;
			}
		return null;
		
	}
	
	/**
	 * @TODO: 菜单管理，查询菜单
	 * @author: zhaichunlei
	 & @DATE : 2016年12月16日
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@Transactional(readOnly = true)
	public List<SysMenuVO> queryRoleMenu(HttpServletRequest request,SysMenuVO e)throws Exception{
		return dao.queryRoleMenu(e);
	}
}
