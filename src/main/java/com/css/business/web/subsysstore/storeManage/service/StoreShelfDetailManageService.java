package com.css.business.web.subsysstore.storeManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysstore.bean.StoreShelfDetail;
import com.css.business.web.subsysstore.storeManage.dao.StoreShelfDetailManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("storeShelfDetailManageService")
public class StoreShelfDetailManageService extends BaseEntityManageImpl<StoreShelfDetail, StoreShelfDetailManageDAO> {
	@Resource(name="storeShelfDetailManageDAO")
	//@Autowired
	private StoreShelfDetailManageDAO dao;
	
	
	@Override
	public StoreShelfDetailManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
