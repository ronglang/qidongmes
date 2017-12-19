package com.css.business.web.subsysmanu.mauManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauUnderShaft;
import com.css.business.web.subsysmanu.mauManage.dao.MauUnderShaftManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauUnderShaftManageService")
public class MauUnderShaftManageService extends BaseEntityManageImpl<MauUnderShaft, MauUnderShaftManageDAO> {
	@Resource(name="mauUnderShaftManageDAO")
	//@Autowired
	private MauUnderShaftManageDAO dao;
	
	
	@Override
	public MauUnderShaftManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
