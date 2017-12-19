package com.css.business.web.subsysmanu.mauManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauProductBom;
import com.css.business.web.subsysmanu.mauManage.dao.MauProductBomManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauProductBomManageService")
public class MauProductBomManageService extends BaseEntityManageImpl<MauProductBom, MauProductBomManageDAO> {
	@Resource(name="mauProductBomManageDAO")
	//@Autowired
	private MauProductBomManageDAO dao;
	
	
	@Override
	public MauProductBomManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
