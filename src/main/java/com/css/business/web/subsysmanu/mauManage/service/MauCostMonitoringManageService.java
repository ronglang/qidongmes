package com.css.business.web.subsysmanu.mauManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauCostMonitoring;
import com.css.business.web.subsysmanu.mauManage.dao.MauCostMonitoringManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauCostMonitoringManageService")
public class MauCostMonitoringManageService extends BaseEntityManageImpl<MauCostMonitoring, MauCostMonitoringManageDAO> {
	@Resource(name="mauCostMonitoringManageDAO")
	//@Autowired
	private MauCostMonitoringManageDAO dao;
	
	
	@Override
	public MauCostMonitoringManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
