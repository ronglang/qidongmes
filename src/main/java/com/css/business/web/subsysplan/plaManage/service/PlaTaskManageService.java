package com.css.business.web.subsysplan.plaManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaTask;
import com.css.business.web.subsysplan.plaManage.dao.PlaTaskManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaTaskManageService")
public class PlaTaskManageService extends BaseEntityManageImpl<PlaTask, PlaTaskManageDAO> {
	@Resource(name="plaTaskManageDAO")
	//@Autowired
	private PlaTaskManageDAO dao;
	
	
	@Override
	public PlaTaskManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
