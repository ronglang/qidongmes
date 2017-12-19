package com.css.business.web.subsysstore.storeManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysstore.bean.StorePstockDetail;
import com.css.business.web.subsysstore.storeManage.dao.StorePstockDetailManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("storePstockDetailManageService")
public class StorePstockDetailManageService extends BaseEntityManageImpl<StorePstockDetail, StorePstockDetailManageDAO> {
	@Resource(name="storePstockDetailManageDAO")
	//@Autowired
	private StorePstockDetailManageDAO dao;
	
	
	@Override
	public StorePstockDetailManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
