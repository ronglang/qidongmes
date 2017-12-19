package com.css.business.web.subsysstore.storeManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysstore.bean.StoreCoilSa;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("storeCoilSaManageDAO")
public class StoreCoilSaManageDAO extends BaseEntityDaoImpl<StoreCoilSa>  {
	
	public String getAreaName(String areaRfid){
		String hql = " from StoreCoilSa where areaRfid = ? ";
		@SuppressWarnings("unchecked")
		List<StoreCoilSa> list = this.createQuery(hql,areaRfid).list();
		if(list.size()<1 || list ==null){
			return null;
		}
		return list.get(0).getArea_name();
	}
	

}
