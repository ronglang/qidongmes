package com.css.business.web.sysManage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.css.business.web.sysManage.bean.SysCommdic;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("sysCommdicManageDAO")
public class SysCommdicManageDAO extends BaseEntityDaoImpl<SysCommdic>  {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<SysCommdic> findByArea(String hql ,Object...value ){
		List<SysCommdic> list = getHibernateTemplate().find(hql, value);
		return list;
	}
}
