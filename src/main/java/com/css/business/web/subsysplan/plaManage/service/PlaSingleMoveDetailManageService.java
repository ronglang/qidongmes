package com.css.business.web.subsysplan.plaManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaSingleMoveDetail;
import com.css.business.web.subsysplan.plaManage.dao.PlaSingleMoveDetailManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaSingleMoveDetailManageService")
public class PlaSingleMoveDetailManageService extends BaseEntityManageImpl<PlaSingleMoveDetail, PlaSingleMoveDetailManageDAO> {
	@Resource(name="plaSingleMoveDetailManageDAO")
	//@Autowired
	private PlaSingleMoveDetailManageDAO dao;
	
	
	@Override
	public PlaSingleMoveDetailManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
