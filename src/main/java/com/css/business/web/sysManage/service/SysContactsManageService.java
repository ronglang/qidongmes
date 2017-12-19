package com.css.business.web.sysManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.sysManage.bean.SysContacts;
import com.css.business.web.sysManage.dao.SysContactsManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("sysContactsManageService")
public class SysContactsManageService extends BaseEntityManageImpl<SysContacts, SysContactsManageDAO> {
	@Resource(name="sysContactsManageDAO")
	//@Autowired
	private SysContactsManageDAO dao;
	
	
	@Override
	public SysContactsManageDAO getEntityDaoInf() {
		return dao;
	}
	

	
}
