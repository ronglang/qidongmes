package com.css.business.web.subsysmanu.demo.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.ManuEntity1;
import com.css.business.web.subsysmanu.demo.dao.ManuModule1ManageDao;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.css.dubbointerface.subsysmanu.ManuManagerInter;
/**
 * @TODO  : DEMO
 * @author: 翟春磊
 * @DATE  : 2017年4月12日
 */
@Service("manuModule1ManageService")
@Component  
public class ManuModule1ManageService extends BaseEntityManageImpl<ManuEntity1, ManuModule1ManageDao> implements ManuManagerInter{
	@Resource(name="manuModule1ManageDao")
	private ManuModule1ManageDao dao;
	@Override
	public String getStrDemao() {
		// TODO Auto-generated method stub
		return "manu_";
	}

	@Override
	public ManuModule1ManageDao getEntityDaoInf() {
		// TODO Auto-generated method stub
		return dao;
	}

}
