package com.css.business.web.sysManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.sysManage.bean.SysUserOptionLog;
import com.css.business.web.sysManage.dao.SysUserOptionLogManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("sysUserOptionLogManageService")
public class SysUserOptionLogManageService extends BaseEntityManageImpl<SysUserOptionLog, SysUserOptionLogManageDAO> {
	@Resource(name="sysUserOptionLogManageDAO")
	//@Autowired
	private SysUserOptionLogManageDAO dao;
	
	
	@Override
	public SysUserOptionLogManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
