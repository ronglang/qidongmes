package com.css.business.web.sysManage.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.css.business.web.sysManage.bean.SysOrgRole;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("sysOrgRoleManageDAO")
public class SysOrgRoleManageDAO extends BaseEntityDaoImpl<SysOrgRole>  {
	/**
	 * 
	 * 执行sql查询数据
	 * */
	public Page getOrmRoleDataPage(Page param, StringBuilder sql) {
		
		List list = this.getHibernateTemplate().find(sql.toString());
		//查询数据库总条数
		String hql ="select count(o.id) from SysOrgRole o";
		List countTotal = this.getHibernateTemplate().find(hql);
		Long total =0L;
		if(countTotal.size()>0){
			 total=(Long)countTotal.get(0);
		}
		Page p = new Page(param.getPageNo(),total,param.getPagesize(),list);
		return p;
	}
/**
 * 
 * 修改
 * */
	public void updateOrgRoleById(StringBuilder sql) {
		
		Query query = this.createQuery(sql.toString());
		query.executeUpdate();
		
	}



}
