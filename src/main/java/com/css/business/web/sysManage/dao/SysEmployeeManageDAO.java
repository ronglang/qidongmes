package com.css.business.web.sysManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.sysManage.bean.SysEmployee;
import com.css.common.web.syscommon.controller.support.QueryCondition;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("sysEmployeeManageDAO")
public class SysEmployeeManageDAO extends BaseEntityDaoImpl<SysEmployee> {

	@SuppressWarnings("unchecked")
	public List<SysEmployee> queryUserNoAccount(QueryCondition qc) {

		String sql = "SELECT * FROM H_PERSON t WHERE not EXISTS(SELECT * FROM sys_user v WHERE t.tel = v.tel)";
		// String sql =
		// "SELECT * FROM SYS_EMPLOYEE t WHERE not EXISTS(SELECT * FROM Vw_user_employee v WHERE t.ID = v.employee_id)";
		return this.createSQLQuery(sql, SysEmployee.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<SysEmployee> queryUserNoAccounts(QueryCondition qc, Integer id) {

		String sql = "SELECT * FROM Sys_Employee t WHERE not EXISTS(SELECT * FROM sys_user v WHERE t.tel = v.tel) UNION"
				+ " SELECT * FROM Sys_Employee e WHERE e.ID=(SELECT u.person_id FROM SYS_USER u WHERE u.ID="
				+ id + ")";
		return this.createSQLQuery(sql, SysEmployee.class).list();
	}
	
	public List<SysEmployee> getEmployeeByRfid(String rfid){
		String hql = " from SysEmployee where rfid = ? ";
		@SuppressWarnings("unchecked")
		List<SysEmployee> list = this.createQuery(hql).list();
		return list;
	}
	
	
}
