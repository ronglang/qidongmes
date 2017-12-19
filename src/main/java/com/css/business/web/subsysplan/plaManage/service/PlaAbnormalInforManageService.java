package com.css.business.web.subsysplan.plaManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaAbnormalInfor;
import com.css.business.web.subsysplan.plaManage.dao.PlaAbnormalInforManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaAbnormalInforManageService")
public class PlaAbnormalInforManageService extends BaseEntityManageImpl<PlaAbnormalInfor, PlaAbnormalInforManageDAO> {
	@Resource(name="plaAbnormalInforManageDAO")
	//@Autowired
	private PlaAbnormalInforManageDAO dao;
	
	
	@Override
	public PlaAbnormalInforManageDAO getEntityDaoInf() {
		return dao;
	}
	
	
	
}
