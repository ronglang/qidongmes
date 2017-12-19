package com.css.business.web.subsysmanu.mauManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauPunchCard;
import com.css.business.web.subsysmanu.mauManage.dao.MauPunchCardManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauPunchCardManageService")
public class MauPunchCardManageService extends BaseEntityManageImpl<MauPunchCard, MauPunchCardManageDAO> {
	@Resource(name="mauPunchCardManageDAO")
	//@Autowired
	private MauPunchCardManageDAO dao;
	
	
	@Override
	public MauPunchCardManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
