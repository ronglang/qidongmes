package com.css.business.web.subsysmanu.mauManage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.bean.CraSeq;
import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineManageDAO;
import com.css.business.web.subsysmanu.mauManage.dao.MauNewDislayManageDao;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauNewDislayManageService")
public class MauNewDislayManageService extends BaseEntityManageImpl<CraSeq, MauNewDislayManageDao>{

	@Resource(name = "mauNewDislayManageDao")
	private MauNewDislayManageDao dao;
	@Resource(name = "mauMachineManageDAO")
	private MauMachineManageDAO mauDao;
	@Override
	public MauNewDislayManageDao getEntityDaoInf() {
		return dao;
	}
	
	/**
	 * 获取所有工序列表
	 * @return
	 */
	/*
	public List<CraSeq> getAllCraSeqService(){
		String hql = "from CraSeq";
		List<CraSeq> list = dao.listQuery(hql);
		return list;
	}
	*/
	public Map<String,Object> getAllCraSeqService(){
		String hql = "from CraSeq order by id ASC";
		List<CraSeq> list = dao.listQuery(hql);
		Map<String,Object>resultMap = new HashMap<>(2);
		Map<Object,List<MauMachine>> map = new HashMap<>();
		for(int i=0;i<list.size();i++){
			String hql2 = "from MauMachine where seqCode='"+list.get(i).getSeqCode()+"'";
			List<MauMachine> list2 = mauDao.listQuery(hql2);
			if(list2 != null && list2.size()>0){
				map.put(list.get(i).getSeqCode(), list2);
			}
			
		}
		resultMap.put("seqs", list);
		resultMap.put("macMap", map);
		return resultMap;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
