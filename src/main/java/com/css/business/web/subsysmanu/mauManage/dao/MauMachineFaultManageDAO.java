package com.css.business.web.subsysmanu.mauManage.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauMachineFault;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.sun.jmx.snmp.Timestamp;

@Repository("mauMachineFaultManageDAO")
public class MauMachineFaultManageDAO extends BaseEntityDaoImpl<MauMachineFault>  {

	
	/**
	 * 
	 *@data:2017年8月3日
	@param repairBy
	@param macCode
	@autor:wl
	 */
	public void updateMauMachineFaultByMacCode(String hql,String repairBy, String macCode) {
		try {
			Query query = this.createQuery(hql, repairBy,"1",macCode,"0");
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

	public void finishRepairMachine(String hql,String macCode,java.sql.Timestamp  timestamp) {
		try {
			Query query = this.createQuery(hql, timestamp,"2",macCode,"1");
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

	public List<MauMachineFault> getListFault(String hql) {
		List<MauMachineFault> list=null;
		try {
			list=this.createQuery(hql, "0").list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return list;
	}

	public void updateMachineFaultRepairBy(String hql,String repairBy, Integer id) {
		Query query = this.createQuery(hql, repairBy,"1",id,"0");
		query.executeUpdate();
		// TODO Auto-generated method stub
		
	}

	public void updateMachineFaultRemark(String hql, String remark,
			String status, Integer id) {
		Query query = this.createQuery(hql, remark,status,id,"1");
		query.executeUpdate();
		
	}

	public void updateMachineFaultRemark1(String hql, String status, Integer id) {
		
		Query query = this.createQuery(hql, "",status,id,"1");
		query.executeUpdate();
		
		// TODO Auto-generated method stub
		
	}

}
