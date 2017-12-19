package com.css.business.web.subsysplan.plaManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaCapacityReport;
import com.css.business.web.subsysplan.plaManage.dao.PlaCapacityReportManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaCapacityReportManageService")
public class PlaCapacityReportManageService extends BaseEntityManageImpl<PlaCapacityReport, PlaCapacityReportManageDAO> {
	@Resource(name="plaCapacityReportManageDAO")
	//@Autowired
	private PlaCapacityReportManageDAO dao;
	
	
	@Override
	public PlaCapacityReportManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
