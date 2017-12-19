package com.css.business.web.subsyscraft.craManage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraProSeqRelation;
import com.css.business.web.subsyscraft.craManage.dao.CraProSeqRelationManageDao;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;


@Service("craProSeqRelationManageService")
public class CraProSeqRelationManageService extends BaseEntityManageImpl<CraProSeqRelation, CraProSeqRelationManageDao>{

	@Resource(name = "craProSeqRelationManageDao")
	private CraProSeqRelationManageDao dao;

	@Override
	public CraProSeqRelationManageDao getEntityDaoInf() {
		// TODO Auto-generated method stub
		return dao;
	}
	public List<CraProSeqRelation> getCraProSeqRelation(String proGgxh){
		String hql = " from CraProSeqRelation where proGgxh =? ";
		@SuppressWarnings("unchecked")
		List<CraProSeqRelation> list = dao.createQuery(hql, proGgxh).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<CraProSeqRelation> getCraProSeqRelationCode(){
		String hql = " from CraProSeqRelation ";
		List<CraProSeqRelation> list = dao.createQuery(hql).list();
		return list;
	}
	
}
