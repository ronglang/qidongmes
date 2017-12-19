package com.css.business.web.subsysplan.plaManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaProductStanding;
import com.css.business.web.subsysplan.plaManage.dao.PlaProductStandingManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaProductStandingManageService")
public class PlaProductStandingManageService extends BaseEntityManageImpl<PlaProductStanding, PlaProductStandingManageDAO> {
	@Resource(name="plaProductStandingManageDAO")
	//@Autowired
	private PlaProductStandingManageDAO dao;
	
	
	@Override
	public PlaProductStandingManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
