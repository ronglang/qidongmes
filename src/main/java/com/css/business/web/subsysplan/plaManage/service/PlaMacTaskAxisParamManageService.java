package com.css.business.web.subsysplan.plaManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaMacTaskAxisParam;
import com.css.business.web.subsysplan.plaManage.dao.PlaMacTaskAxisParamDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;


@Service("plaMacTaskAxisParamManageService")
public class PlaMacTaskAxisParamManageService extends
BaseEntityManageImpl<PlaMacTaskAxisParam, PlaMacTaskAxisParamDAO> {

	@Resource(name = "plaMacTaskAxisParamDAO")
	private PlaMacTaskAxisParamDAO dao;
	
	@Override
	public PlaMacTaskAxisParamDAO getEntityDaoInf() {	
		return dao;
	}

}
