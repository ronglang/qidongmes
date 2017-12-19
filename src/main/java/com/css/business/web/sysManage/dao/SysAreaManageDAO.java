package com.css.business.web.sysManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.sysManage.bean.SysArea;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("sysAreaManageDAO")
public class SysAreaManageDAO extends BaseEntityDaoImpl<SysArea>  {

	
	@SuppressWarnings("unchecked")
	public List<SysArea> findSysArea(String pid){
		String hql = "from SysArea s where s.pcode = ?";
		List<SysArea> list = getHibernateTemplate().find(hql, pid);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<SysArea> ListByPidForApp(String pid){
		String sql="select * from sys_area where pcode=?";
		return this.createSQLQuery(sql, SysArea.class, pid).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<SysArea> getAreasByNameForApp(String areaName){
		String sql="select * from sys_area where aname like ?";
		return this.createSQLQuery(sql, SysArea.class, "%"+areaName+"%").list();
	}
	
	public String getAreasNameByCode(String areaCode){
		String sql="select areaName from SysArea where areaCode=?";
		Object o = this.createQuery(sql,areaCode).uniqueResult();
		if(o == null) return "";
		else return o.toString();
	}
}
