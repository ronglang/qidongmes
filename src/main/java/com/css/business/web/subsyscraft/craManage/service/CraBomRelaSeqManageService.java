package com.css.business.web.subsyscraft.craManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraBomRelaSeq;
import com.css.business.web.subsyscraft.craManage.dao.CraBomRelaSeqManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("craBomRelaSeqService")
public class CraBomRelaSeqManageService extends BaseEntityManageImpl<CraBomRelaSeq,CraBomRelaSeqManageDAO> {
	@Resource(name="craBomRelaSeqManageDAO")
	//@Autowired
	private CraBomRelaSeqManageDAO dao;
	
	
	@Override
	public CraBomRelaSeqManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
