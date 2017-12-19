package com.css.business.web.subsyscraft.craManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraBomAttribute;
import com.css.business.web.subsyscraft.craManage.dao.CraBomAttributeManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("craBomAttributeManageService")
public class CraBomAttributeManageService extends BaseEntityManageImpl<CraBomAttribute, CraBomAttributeManageDAO> {
	@Resource(name="craBomAttributeManageDAO")
	//@Autowired
	private CraBomAttributeManageDAO dao;
	
	
	@Override
	public CraBomAttributeManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
