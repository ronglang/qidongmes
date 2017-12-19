package com.css.business.web.subsyscraft.craManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraParameter;
import com.css.business.web.subsyscraft.craManage.dao.CraParameterManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("craParameterManageService")
public class CraParameterManageService extends BaseEntityManageImpl<CraParameter, CraParameterManageDAO> {
	@Resource(name="craParameterManageDAO")
	//@Autowired
	private CraParameterManageDAO dao;
	
	
	@Override
	public CraParameterManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
