package com.css.business.web.subsysplan.plaManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaRework;
import com.css.business.web.subsysplan.plaManage.dao.PlaReworkManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaReworkManageService")
public class PlaReworkManageService extends BaseEntityManageImpl<PlaRework, PlaReworkManageDAO> {
	@Resource(name="plaReworkManageDAO")
	//@Autowired
	private PlaReworkManageDAO dao;
	
	
	@Override
	public PlaReworkManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
