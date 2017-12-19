package com.css.business.web.subsysstore.storeManage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysstore.bean.SellVoucherRecord;
import com.css.business.web.subsysstore.storeManage.service.SellVoucherRecordManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/sellVoucherRecordManageAction")
public class SellVoucherRecordManageAction extends BaseSpringSupportAction<SellVoucherRecord, SellVoucherRecordManageService> {

	@Autowired
	private SellVoucherRecordManageService sellVoucherRecordManageService;
	@Override
	public SellVoucherRecordManageService getEntityManager() {
		return sellVoucherRecordManageService;
	}
	
}
