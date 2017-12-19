package com.css.business.web.subsysstore.storeManage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysstore.bean.StoreUnlock;
import com.css.business.web.subsysstore.storeManage.service.StoreUnlockManageService;
import com.css.common.web.syscommon.controller.BaseSpringSupportAction;
@Controller
@RequestMapping("/storeUnlockManageAction")
public class StoreUnlockManageAction extends BaseSpringSupportAction<StoreUnlock, StoreUnlockManageService> {

	@Autowired
	private StoreUnlockManageService storeUnlockManageService;
	
	@Override
	public StoreUnlockManageService getEntityManager() {
		
		return storeUnlockManageService;
	}
	
}
