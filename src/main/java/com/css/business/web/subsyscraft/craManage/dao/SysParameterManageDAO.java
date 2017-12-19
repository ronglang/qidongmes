package com.css.business.web.subsyscraft.craManage.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsyscraft.bean.SysParameter;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("sysParameterManageDAO")
public class SysParameterManageDAO extends BaseEntityDaoImpl<SysParameter>  {



/**
 * 获取下拉框的值
 *@data:2017年7月25日
@param hql
@param obj
@return
@autor:wl
 */
	public List<String> getSelectOption(String hql, String obj) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<>();
		try {
			 list = this.createQuery(hql, obj).list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

public List<SysParameter> getParamList(String sql, String wirediscPgxh,
		String wirePgxh) {
	 List<SysParameter> list = new ArrayList<>();
	try {
		list=this.createQuery(sql, wirediscPgxh,wirediscPgxh).list();
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return list;
}

public List<SysParameter> getParamValueList(String wirePgxh) {
	// TODO Auto-generated method stub
	List<SysParameter>  list =new ArrayList<>();
	try {
		String hql="from SysParameter where paraName=?";
		list=this.createQuery(hql, wirePgxh).list();
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return null;
}



}
