package com.css.business.web.subsysstore.storeManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysstore.bean.StoreCoilSasDetail;
import com.css.business.web.subsysstore.storeManage.dao.StoreCoilSasDetailManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("storeCoilSasDetailManageService")
public class StoreCoilSasDetailManageService extends BaseEntityManageImpl<StoreCoilSasDetail, StoreCoilSasDetailManageDAO> {
	@Resource(name="storeCoilSasDetailManageDAO")
	//@Autowired
	private StoreCoilSasDetailManageDAO dao;
	
	
	@Override
	public StoreCoilSasDetailManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
