package com.css.business.web.subsysstore.storeManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysstore.bean.StoreNotice;
import com.css.business.web.subsysstore.storeManage.dao.StoreNoticeManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("storeNoticeManageService")
public class StoreNoticeManageService extends BaseEntityManageImpl<StoreNotice, StoreNoticeManageDAO> {
	@Resource(name="storeNoticeManageDAO")
	//@Autowired
	private StoreNoticeManageDAO dao;
	
	
	@Override
	public StoreNoticeManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
