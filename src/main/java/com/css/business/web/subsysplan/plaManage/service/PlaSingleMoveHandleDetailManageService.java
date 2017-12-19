package com.css.business.web.subsysplan.plaManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaSingleMoveHandleDetail;
import com.css.business.web.subsysplan.plaManage.dao.PlaSingleMoveHandleDetailManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaSingleMoveHandleDetailManageService")
public class PlaSingleMoveHandleDetailManageService extends BaseEntityManageImpl<PlaSingleMoveHandleDetail, PlaSingleMoveHandleDetailManageDAO> {
	@Resource(name="plaSingleMoveHandleDetailManageDAO")
	//@Autowired
	private PlaSingleMoveHandleDetailManageDAO dao;
	
	
	@Override
	public PlaSingleMoveHandleDetailManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
