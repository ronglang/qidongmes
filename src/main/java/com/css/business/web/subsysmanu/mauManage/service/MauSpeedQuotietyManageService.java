package com.css.business.web.subsysmanu.mauManage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauSpeedQuotiety;
import com.css.business.web.subsysmanu.mauManage.dao.MauSpeedQuotietyManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauSpeedQuotietyManageService")
public class MauSpeedQuotietyManageService extends
		BaseEntityManageImpl<MauSpeedQuotiety, MauSpeedQuotietyManageDAO> {
	@Resource(name = "mauSpeedQuotietyManageDAO")
	private MauSpeedQuotietyManageDAO dao;
	

	@Override
	public MauSpeedQuotietyManageDAO getEntityDaoInf() {
		return dao;
	}

	public void saveMauSpeedQuotiety(MauSpeedQuotiety quotiety){
		dao.save(quotiety);
	}
	
	public void updateMauSpeedQuotiety(MauSpeedQuotiety quotiety) throws Exception{
		dao.updateByCon(quotiety, false);
	}
	
	public Page queryMauSpeedQuotiety(Page p,String macCode){
		String hql = " from MauSpeedQuotiety where 1=1 ";
		if(macCode!=null&&!"".equals(macCode)){
			hql += " AND macCode = '"+macCode+"' ";
		}
		Page page = dao.pageQuery(hql, p.getPageNo(), p.getPagesize());
		return page;
	}
	
}
