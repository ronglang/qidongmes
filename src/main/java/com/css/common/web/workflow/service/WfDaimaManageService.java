package com.css.common.web.workflow.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.css.common.web.workflow.bean.WfDaima;
import com.css.common.web.workflow.dao.WfDaimaManageDAO;

@Service("wfDaimaManageService")
public class WfDaimaManageService extends BaseEntityManageImpl<WfDaima, WfDaimaManageDAO> {
	@Resource(name="wfDaimaManageDAO")
	//@Autowired
	private WfDaimaManageDAO dao;
	
	
	@Override
	public WfDaimaManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
