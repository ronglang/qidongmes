package com.css.business.web.subsysplan.plaManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaDispatchDetails;
import com.css.business.web.subsysplan.plaManage.dao.PlaDispatchDetailsManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaDispatchDetailsManageService")
public class PlaDispatchDetailsManageService extends BaseEntityManageImpl<PlaDispatchDetails, PlaDispatchDetailsManageDAO> {
	@Resource(name="plaDispatchDetailsManageDAO")
	//@Autowired
	private PlaDispatchDetailsManageDAO dao;
	
	
	@Override
	public PlaDispatchDetailsManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
