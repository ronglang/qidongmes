package com.css.business.web.sysManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.sysManage.bean.SysUserRole;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("sysUserRoleManageDAO")
public class SysUserRoleManageDAO extends BaseEntityDaoImpl<SysUserRole>  {

	public List<SysUserRole> QueryLists(Integer id) {
		String hql = "FROM SysUserRole where 1=1 ";
		
		if (id != null) {
			hql += " AND userId=?";
		}
		@SuppressWarnings("unchecked")
		List<SysUserRole> tempList = this.getHibernateTemplate().find(hql,id);
		return tempList;
	}

	public void deletUserRoleById(Integer id) {
		SysUserRole sysUserRole =this.get(id);
		this.getHibernateTemplate().delete(sysUserRole);
	}

	public List<SysUserRole> findEntityByUserId(Integer userId) {
		String hql = "FROM SysUserRole where 1=1 ";
		if (userId != null) {
			hql += " AND userId=?";
		}
		@SuppressWarnings("unchecked")
		List<SysUserRole> tempList = this.getHibernateTemplate().find(hql,userId);
		return tempList;
	}

	

}
