package com.css.business.web.subsysplan.plaManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaSeqMaterDetail;
import com.css.business.web.subsysplan.plaManage.dao.PlaSeqMaterDetailManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaSeqMaterDetailManageService")
public class PlaSeqMaterDetailManageService extends BaseEntityManageImpl<PlaSeqMaterDetail, PlaSeqMaterDetailManageDAO> {
	@Resource(name="plaSeqMaterDetailManageDAO")
	//@Autowired
	private PlaSeqMaterDetailManageDAO dao;
	
	
	@Override
	public PlaSeqMaterDetailManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
