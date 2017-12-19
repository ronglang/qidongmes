package com.css.business.web.subsysmanu.mauManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauWaste;
import com.css.business.web.subsysmanu.mauManage.dao.MauWasteManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauWasteManageService")
public class MauWasteManageService extends BaseEntityManageImpl<MauWaste, MauWasteManageDAO> {
	@Resource(name="mauWasteManageDAO")
	//@Autowired
	private MauWasteManageDAO dao;
	
	
	@Override
	public MauWasteManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
