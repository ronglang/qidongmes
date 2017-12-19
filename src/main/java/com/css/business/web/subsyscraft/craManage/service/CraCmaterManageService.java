package com.css.business.web.subsyscraft.craManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraCmater;
import com.css.business.web.subsyscraft.craManage.dao.CraCmaterManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("craCmaterManageService")
public class CraCmaterManageService extends BaseEntityManageImpl<CraCmater, CraCmaterManageDAO> {
	@Resource(name="craCmaterManageDAO")
	//@Autowired
	private CraCmaterManageDAO dao;
	
	
	@Override
	public CraCmaterManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
