package com.css.business.web.subsysmanu.mauManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauProcessDany;
import com.css.business.web.subsysmanu.mauManage.dao.MauProcessDanyManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauProcessDanyManageService")
public class MauProcessDanyManageService extends BaseEntityManageImpl<MauProcessDany, MauProcessDanyManageDAO> {
	@Resource(name="mauProcessDanyManageDAO")
	private MauProcessDanyManageDAO dao;
	
	@Override
	public MauProcessDanyManageDAO getEntityDaoInf() {
		return dao;
	}
}
