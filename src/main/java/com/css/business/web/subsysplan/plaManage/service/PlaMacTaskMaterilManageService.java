package com.css.business.web.subsysplan.plaManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaMacTaskMateril;
import com.css.business.web.subsysplan.plaManage.dao.PlaMacTaskMaterilDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;


@Service("plaMacTaskMaterilManageService")
public class PlaMacTaskMaterilManageService extends
BaseEntityManageImpl<PlaMacTaskMateril, PlaMacTaskMaterilDAO> {

	@Resource(name = "plaMacTaskMaterilDAO")
	private PlaMacTaskMaterilDAO dao;
	
	@Override
	public PlaMacTaskMaterilDAO getEntityDaoInf() {	
		return dao;
	}

	
	public Page getPlaMacTaskMateril(Page p,String stime,String dtime){
		String hql = " from PlaMacTaskMateril where 1=1 ";
		if(stime!=null&&!"".equals(stime)){
			hql +=" and ptime >='"+stime+"' ";
		}
		if(dtime!=null&&!"".equals(dtime)){
			hql +=" and ptime <='"+dtime+"' ";
		}
		hql +=" order by ptime ";
		//dao.pageSQLQuery(sql, p.getPageNo(), p.getPagesize())
		Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
		return page;  
	}
	
}
