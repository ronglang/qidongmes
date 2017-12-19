package com.css.business.web.subsyscraft.craManage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraSeqMiddle;
import com.css.business.web.subsyscraft.craManage.dao.CraSeqMiddleManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("craSeqMiddleManageService")
public class CraSeqMiddleManageService  extends BaseEntityManageImpl<CraSeqMiddle,CraSeqMiddleManageDAO>{

	@Autowired
	private CraSeqMiddleManageDAO craSeqMiddleManageDAO;
	@Override
	public CraSeqMiddleManageDAO getEntityDaoInf() {
		// TODO Auto-generated method stub
		return craSeqMiddleManageDAO;
	}

}
