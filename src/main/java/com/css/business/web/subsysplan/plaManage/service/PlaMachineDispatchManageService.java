package com.css.business.web.subsysplan.plaManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaMachineDispatch;
import com.css.business.web.subsysplan.plaManage.dao.PlaMachineDispatchManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaMachineDispatchManageService")
public class PlaMachineDispatchManageService extends BaseEntityManageImpl<PlaMachineDispatch, PlaMachineDispatchManageDAO> {
	@Resource(name="plaMachineDispatchManageDAO")
	//@Autowired
	private PlaMachineDispatchManageDAO dao;
	
	
	@Override
	public PlaMachineDispatchManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
