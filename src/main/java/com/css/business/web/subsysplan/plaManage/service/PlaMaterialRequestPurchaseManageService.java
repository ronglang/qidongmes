package com.css.business.web.subsysplan.plaManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaMaterialRequestPurchase;
import com.css.business.web.subsysplan.plaManage.dao.PlaMaterialRequestPurchaseManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaMaterialRequestPurchaseManageService")
public class PlaMaterialRequestPurchaseManageService extends BaseEntityManageImpl<PlaMaterialRequestPurchase, PlaMaterialRequestPurchaseManageDAO> {
	@Resource(name="plaMaterialRequestPurchaseManageDAO")
	//@Autowired
	private PlaMaterialRequestPurchaseManageDAO dao;
	
	
	@Override
	public PlaMaterialRequestPurchaseManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
