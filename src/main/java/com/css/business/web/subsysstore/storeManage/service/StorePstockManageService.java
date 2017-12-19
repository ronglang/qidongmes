package com.css.business.web.subsysstore.storeManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysstore.bean.StorePstock;
import com.css.business.web.subsysstore.storeManage.dao.StorePstockManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("storePstockManageService")
public class StorePstockManageService extends BaseEntityManageImpl<StorePstock, StorePstockManageDAO> {
	@Resource(name="storePstockManageDAO")
	//@Autowired
	private StorePstockManageDAO dao;
	
	
	@Override
	public StorePstockManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
