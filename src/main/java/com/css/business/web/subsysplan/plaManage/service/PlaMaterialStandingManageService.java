package com.css.business.web.subsysplan.plaManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaMaterialStanding;
import com.css.business.web.subsysplan.plaManage.dao.PlaMaterialStandingManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaMaterialStandingManageService")
public class PlaMaterialStandingManageService extends BaseEntityManageImpl<PlaMaterialStanding, PlaMaterialStandingManageDAO> {
	@Resource(name="plaMaterialStandingManageDAO")
	//@Autowired
	private PlaMaterialStandingManageDAO dao;
	
	
	@Override
	public PlaMaterialStandingManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
