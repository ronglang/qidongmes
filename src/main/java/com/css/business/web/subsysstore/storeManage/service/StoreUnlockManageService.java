package com.css.business.web.subsysstore.storeManage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysstore.bean.StoreUnlock;
import com.css.business.web.subsysstore.storeManage.dao.StoreUnlockManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("storeUnlockManageService")
public class StoreUnlockManageService extends BaseEntityManageImpl<StoreUnlock, StoreUnlockManageDAO> 
		{
	@Autowired
	private StoreUnlockManageDAO storeUnlockManageDAO;
	@Override
	public StoreUnlockManageDAO getEntityDaoInf() {
		// TODO Auto-generated method stub
		return storeUnlockManageDAO;
	}
	
}
