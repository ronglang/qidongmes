package com.css.business.web.subsysstore.storeManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysstore.bean.StoreMrecord;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("storeMrecordManageDAO")
public class StoreMrecordManageDAO extends BaseEntityDaoImpl<StoreMrecord>  {

	
	
	@SuppressWarnings("unchecked")
	public List<StoreMrecord> getMaterialIdByMatName(String hql,
			String objName, String objGgxh) {
		// TODO Auto-generated method stub
		List<StoreMrecord> list=null;
		try {
			list=this.createQuery(hql, objName,objGgxh).list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<StoreMrecord> getStock(String rfid) {
		String hql = " from StoreMrecord where rfid = '" + rfid + "' ";
		List<StoreMrecord> list = this.createQuery(hql).list();
		return list;
	}
	
}
