package com.css.business.web.subsysmanu.mauManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauAxis;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("mauAxisManageDAO")
public class MauAxisManageDAO extends
		BaseEntityDaoImpl<MauAxis> {

	
	public Integer getExternalDiameterByAxisName(String axisName){
		if(axisName==null || "".equals(axisName)){
			return 0;
		}
		String hql = " from MauAxis where axisName = ? ";
		@SuppressWarnings("unchecked")
		List<MauAxis> list = this.createQuery(hql, axisName).list();
		if(list.size()<1||list==null){
			return 0;
		}
		return list.get(0).getExternalDiameter();
	}
	
	public MauAxis getMauAxis(String axisName){
		String hql = " from MauAxis where axisName = ? and ( rfid IS  NULL OR rfid ='') ";
		@SuppressWarnings("unchecked")
		List<MauAxis> list = this.createQuery(hql, axisName).list();
		if(list.size()<1 || list==null){
			return null;
		}
		return list.get(0);
	}
	

}
