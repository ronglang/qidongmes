package com.css.business.web.subsysmanu.mauManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauExceptionAll;
import com.css.business.web.subsysmanu.mauManage.dao.MauExceptionAllManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauExceptionAllManageService")
public class MauExceptionAllManageService extends BaseEntityManageImpl<MauExceptionAll, MauExceptionAllManageDAO> {
	@Resource(name="mauExceptionAllManageDAO")
	//@Autowired
	private MauExceptionAllManageDAO dao;
	
	
	@Override
	public MauExceptionAllManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
