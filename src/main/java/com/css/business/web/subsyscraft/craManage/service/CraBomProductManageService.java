package com.css.business.web.subsyscraft.craManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraBomProduct;
import com.css.business.web.subsyscraft.craManage.dao.CraBomProductManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("craBomProductManageService")
public class CraBomProductManageService extends BaseEntityManageImpl<CraBomProduct, CraBomProductManageDAO> {
	@Resource(name="craBomProductManageDAO")
	//@Autowired
	private CraBomProductManageDAO dao;
	
	
	@Override
	public CraBomProductManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
