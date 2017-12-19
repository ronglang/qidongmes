package com.css.business.web.subsysplan.plaManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaRequestPurchaseDetailed;
import com.css.business.web.subsysplan.plaManage.dao.PlaRequestPurchaseDetailedManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaRequestPurchaseDetailedManageService")
public class PlaRequestPurchaseDetailedManageService extends BaseEntityManageImpl<PlaRequestPurchaseDetailed, PlaRequestPurchaseDetailedManageDAO> {
	@Resource(name="plaRequestPurchaseDetailedManageDAO")
	//@Autowired
	private PlaRequestPurchaseDetailedManageDAO dao;
	
	
	@Override
	public PlaRequestPurchaseDetailedManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
	
	
}
