package com.css.business.web.subsysstore.storeManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysstore.bean.StoreObjDetail;
import com.css.business.web.subsysstore.storeManage.dao.StoreObjDetailManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("storeObjDetailManageService")
public class StoreObjDetailManageService extends BaseEntityManageImpl<StoreObjDetail, StoreObjDetailManageDAO> {
	@Resource(name="storeObjDetailManageDAO")
	//@Autowired
	private StoreObjDetailManageDAO dao;
	
	
	@Override
	public StoreObjDetailManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
