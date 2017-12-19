package com.css.business.web.subsysmanu.mauManage.dao;


import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauMachineMaintain;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("mauMachineMaintainManageDAO")
public class MauMachineMaintainManageDAO extends BaseEntityDaoImpl<MauMachineMaintain>  {

	public void updateMachineMaintainRemark1(String hql, String status,
			Integer id) {
		Query query = this.createQuery(hql, "",status,id,"1");
		query.executeUpdate();
		
	}

	public void updateMachineMaintainRemark(String hql, String remark,
			String status, Integer id) {
		Query query = this.createQuery(hql, remark,status,id,"1");
		query.executeUpdate();
		
	}

	public void updateMachineMaintainRepairBy(String hql, String maintainBy,
			Integer id) {
		Query query = this.createQuery(hql, maintainBy,"1",id,"0");
		query.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	public List<String> getMachineCode(String hql) {
		 List<String> list =null;
		try {
			list=this.createSQLQuery(hql).list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return list;
	}

	
	

}
