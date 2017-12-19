package com.css.business.web.subsysmanu.mauManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauElectricityPrice;
import com.css.business.web.subsysmanu.mauManage.dao.MauElectricityPriceManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauElectricityPriceManageService")
public class MauElectricityPriceManageService extends BaseEntityManageImpl<MauElectricityPrice, MauElectricityPriceManageDAO> {
	@Resource(name="mauElectricityPriceManageDAO")
	//@Autowired
	private MauElectricityPriceManageDAO dao;
	
	
	@Override
	public MauElectricityPriceManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
