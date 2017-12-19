package com.css.business.web.subsysmanu.mauManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauParam;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("mauParamManageDAO")
public class MauParamManageDAO extends BaseEntityDaoImpl<MauParam>{

	public MauParam getBymacCode(String macCode) {
		String hql = "from MauParam where macCode = '"+macCode+"' order by createDate DESC";
		List<MauParam> list = this.createQuery(hql).list();
		if (list != null  && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
