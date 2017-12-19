package com.css.business.web.subsysstore.storeManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysstore.bean.StoreDgCkDetail;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("storeDgCkDetailManageDAO")
public class StoreDgCkDetailManageDAO extends BaseEntityDaoImpl<StoreDgCkDetail>  {

	@SuppressWarnings("unchecked")
	public List<StoreDgCkDetail> queryDetailByCode(String hql,
			String outBoundCode, String objGgxh, Integer materialId) {
		// TODO Auto-generated method stub
		 List<StoreDgCkDetail>  list=null;
		try {
			list=this.createQuery(hql, outBoundCode,objGgxh,materialId).list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	
	

}
