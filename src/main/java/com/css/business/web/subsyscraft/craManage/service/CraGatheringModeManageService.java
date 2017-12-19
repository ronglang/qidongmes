package com.css.business.web.subsyscraft.craManage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraGatheringMode;
import com.css.business.web.subsyscraft.bean.CraProSeqRelation;
import com.css.business.web.subsyscraft.craManage.dao.CraGatheringModeManageDao;
import com.css.business.web.subsyscraft.craManage.dao.CraProSeqRelationManageDao;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
@Service("craGatheringModeManageService")
public class CraGatheringModeManageService extends BaseEntityManageImpl<CraGatheringMode, CraGatheringModeManageDao>{

	@Autowired
	private CraGatheringModeManageDao craGatheringModeManageDao;
	
	@Autowired
	private CraProSeqRelationManageDao craProSeqRelationManageDao;
	@Override
	public CraGatheringModeManageDao getEntityDaoInf() {
		// TODO Auto-generated method stub
		return craGatheringModeManageDao;
	}
	
	/**
	 * 根据relationId查询集绞芯线
	 * @param relationId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CraGatheringMode> getJiJiao(String relationId) {
		if(relationId==null||relationId.isEmpty()){
			throw new RuntimeException("relationId为空");
		}
//		CraProSeqRelation bean = craProSeqRelationManageDao.get(relationId);
		//查询集绞内容
		String hql = " from CraGatheringMode where relationId = ? ";
		List<CraGatheringMode> list = craGatheringModeManageDao.getHibernateTemplate().find(hql,Integer.parseInt(relationId));
		
		
		return list;
	}

}
