package com.css.business.web.subsysmanu.mauManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauParam;
import com.css.business.web.subsysmanu.mauManage.dao.MauParamManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauParamManageService")
public class MauParamManageService extends BaseEntityManageImpl<MauParam, MauParamManageDAO> {
	@Resource(name= "mauParamManageDAO")
	private MauParamManageDAO dao;
	@Override
	public MauParamManageDAO getEntityDaoInf() {
		return dao;
	}

	
	public MauParam getByMacCode(String macCode){
		return dao.getBymacCode(macCode);
	}
	
	
	
}
