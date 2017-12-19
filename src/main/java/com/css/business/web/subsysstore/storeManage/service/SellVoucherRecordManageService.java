package com.css.business.web.subsysstore.storeManage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysstore.bean.SellVoucherRecord;
import com.css.business.web.subsysstore.storeManage.dao.SellVoucherRecordManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("sellVoucherRecordManageService")
public class SellVoucherRecordManageService extends BaseEntityManageImpl<SellVoucherRecord, SellVoucherRecordManageDAO> {

	@Autowired
	private SellVoucherRecordManageDAO sellVoucherRecordManageDAO;
	
	@Override
	public SellVoucherRecordManageDAO getEntityDaoInf() {
		return sellVoucherRecordManageDAO;
	}
	
}
