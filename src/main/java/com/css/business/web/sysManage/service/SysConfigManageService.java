package com.css.business.web.sysManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.sysManage.bean.SysConfig;
import com.css.business.web.sysManage.dao.SysConfigManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

/**
 * @TODO  : 配置管理 
 * @author: 翟春磊
 * @DATE  : 2017年6月23日
 */
@Service("sysConfigManageService")
public class SysConfigManageService extends BaseEntityManageImpl<SysConfig, SysConfigManageDAO> {
	@Resource(name="sysConfigManageDAO")
	//@Autowired
	private SysConfigManageDAO dao;
	
	
	@Override
	public SysConfigManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
