package com.css.business.web.subsysmanu.mauManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauFinishProduct;
import com.css.business.web.subsysmanu.mauManage.dao.MauFinishProductManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauFinishProductManageService")
public class MauFinishProductManageService extends BaseEntityManageImpl<MauFinishProduct, MauFinishProductManageDAO> {
	@Resource(name="mauFinishProductManageDAO")
	//@Autowired
	private MauFinishProductManageDAO dao;
	
	
	@Override
	public MauFinishProductManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
