package com.css.business.web.sysManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.sysManage.bean.SysTelLocation;
import com.css.business.web.sysManage.dao.SysTelLocationManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("sysTelLocationManageService")
public class SysTelLocationManageService extends BaseEntityManageImpl<SysTelLocation, SysTelLocationManageDAO> {
	@Resource(name="sysTelLocationManageDAO")
	//@Autowired
	private SysTelLocationManageDAO dao;
	
	
	@Override
	public SysTelLocationManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
