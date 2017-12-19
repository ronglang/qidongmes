package com.css.business.web.sysManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.sysManage.bean.SysOrg;
import com.css.common.web.syscommon.controller.support.QueryCondition;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("sysOrgManageDAO")
public class SysOrgManageDAO extends BaseEntityDaoImpl<SysOrg>  {

	//保存、更新机构信息
	public void saveOrgInfo(SysOrg sysOrg) {
		this.getHibernateTemplate().saveOrUpdate(sysOrg);
		this.getSessionFactory().getCurrentSession().clear();
		this.getSessionFactory().getCurrentSession().merge(sysOrg);
	}
	
	public SysOrg findById(Integer id){
		SysOrg sysOrg = this.get(id);
		return sysOrg;
	}
	
	//查询机构部门编码
	public List<SysOrg> findOrgListSize(String parentId) {
		Integer length = parentId.length()+4;
		String hql = "FROM SysOrg where orgCode LIKE '"+parentId+"%' and Len(orgCode) = "+length+" ORDER BY orgCode DESC ";
		
		@SuppressWarnings("unchecked")
		List<SysOrg> tempList = this.getHibernateTemplate().find(hql);
		return tempList;
	}

	public List<SysOrg> querys(QueryCondition qc) {
		String hql = "FROM SysOrg t1 where t1.orgCode not in(select t.orgCode from ResmanageSend t ) ";
		@SuppressWarnings("unchecked")
		List<SysOrg> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	//根据部门编码获取部门对象信息
	public SysOrg findByOrgCode(String orgCode) {
		String sql = "SELECT * FROM SYS_ORG WHERE ORG_CODE='"+orgCode+"'";
		@SuppressWarnings("unchecked")
		List<SysOrg> list  = this.createSQLQuery(sql, SysOrg.class).list();
		return (list.size() > 0 ? list.get(0):null);
	}

	public void saveOrg(SysOrg sysOrg){
		List<SysOrg> list = this.findOrgListSize(sysOrg.getPcode());// 查询机构部门编码
		if (list.size() > 0) {
			SysOrg s = list.get(0);
			String ss = s.getOrgCode();
			Long sss = Long.parseLong(ss) + 1;
			sysOrg.setOrgCode(String.valueOf(sss));
		} else {
			Long old = Long.parseLong(sysOrg.getPcode() + "0001");
			String newOrgCode = String.valueOf(old);
			sysOrg.setOrgCode(newOrgCode);
		}

		for (SysOrg o : list) {
			if (o.getId().equals(sysOrg.getId())) {
				//this.getSessionFactory().getCurrentSession().evict(o);
				this.getHibernateTemplate().evict(o);
			}
		}
		//this.saveOrgInfo(sysOrg);
		this.save(sysOrg);
		this.getHibernateTemplate().flush();
	}

	public Page getDataListPage(Page param, StringBuilder sql) {
		List find = this.getHibernateTemplate().find(sql.toString());
		//int pageNo, long totalSize, int pageSize, List data
		String hql ="select count(o.id) from SysOrg o ";
		List toatl = this.getHibernateTemplate().find(hql);
		Long object=0L;
		if(toatl.size()>0){
			
			object =(Long) toatl.get(0);
		}
		Page p = new Page(param.getPageNo(),object,param.getPagesize(),find);
		p.setPage(param.getPage());
		return p;
	}
}
