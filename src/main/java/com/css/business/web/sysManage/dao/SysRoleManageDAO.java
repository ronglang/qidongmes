package com.css.business.web.sysManage.dao;



import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.sysManage.bean.SysRole;
import com.css.business.web.sysManage.bean.SysRoleMenu;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("sysRoleManageDAO")
public class SysRoleManageDAO extends BaseEntityDaoImpl<SysRole>  {


	
	//创建新的角色
	public void saveRoleInfo(SysRole sysRole) {
		this.getHibernateTemplate().save(sysRole);
	}

	//设置角色的权限
	public void saveRoleResource(SysRoleMenu sysRoleMenu) {
		this.getHibernateTemplate().save(sysRoleMenu);
	}

	public void upDateRole(SysRole sysRole) {
		this.getHibernateTemplate().update(sysRole);
		
	}

	//根据RoleID删除以前的数据
	public void deletRoleResourceById(Integer params) {
		
		
		//this.getHibernateTemplate().delete(entity);
		
	}
	
	public SysRole findEntityByRoleId(Integer roleId) {
		String sql = "SELECT * FROM sys_role t where id=?";
		@SuppressWarnings("rawtypes")
		List list=	this.createSQLQuery(sql, SysRole.class, roleId).list();
		return (SysRole) (list.size() > 0 ? list.get(0):null);
	}

}
