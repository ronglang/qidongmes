package com.css.business.web.sysManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.sysManage.bean.SysMenu;
import com.css.business.web.sysManage.dao.SysMenuManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("sysMenuManageService")
public class SysMenuManageService extends BaseEntityManageImpl<SysMenu, SysMenuManageDAO> {
	@Resource(name="sysMenuManageDAO")
	//@Autowired
	private SysMenuManageDAO dao;
	
	
	@Override
	public SysMenuManageDAO getEntityDaoInf() {
		return dao;
	}
	

	public SysMenu findResourceEntityById(Integer id) {
		
		return this.get(id);
	}
}
