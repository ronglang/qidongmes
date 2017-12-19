package com.css.business.web.subsysstore.storeManage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysstore.bean.StoreLockControl;
import com.css.business.web.subsysstore.storeManage.dao.StoreLockControlManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("storeLockControlManageService")
public class StoreLockControlManageService extends BaseEntityManageImpl<StoreLockControl, StoreLockControlManageDAO> {

	@Autowired
	private StoreLockControlManageDAO storeLockControlManageDAO;
	
	@Override
	public StoreLockControlManageDAO getEntityDaoInf() {
		return storeLockControlManageDAO;
	}
	
}
