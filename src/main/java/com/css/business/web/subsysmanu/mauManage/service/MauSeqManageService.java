package com.css.business.web.subsysmanu.mauManage.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauSeq;
import com.css.business.web.subsysmanu.mauManage.dao.MauSeqManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauSeqManageService")
public class MauSeqManageService extends BaseEntityManageImpl<MauSeq, MauSeqManageDAO> {
	@Resource(name="mauSeqManageDAO")
	//@Autowired
	private MauSeqManageDAO dao;
	
	
	@Override
	public MauSeqManageDAO getEntityDaoInf() {
		return dao;
	}


	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @return         
	 */ 
	public List<MauSeq> getAllSeq() {
		// TODO Auto-generated method stub
		String hql = "from MauSeq ";
		List<MauSeq> listQuery = dao.listQuery(hql);
		return listQuery;
	}
	
	
	
}
