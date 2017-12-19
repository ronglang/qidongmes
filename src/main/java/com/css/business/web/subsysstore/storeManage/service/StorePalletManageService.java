package com.css.business.web.subsysstore.storeManage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.mauManage.dao.MauRfidCardManageDAO;
import com.css.business.web.subsysstore.bean.StorePallet;
import com.css.business.web.subsysstore.storeManage.dao.StorePalletManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("storePalletManageService")
public class StorePalletManageService extends BaseEntityManageImpl<StorePallet,StorePalletManageDAO>{
	@Resource(name="storePalletManageDAO")
	private StorePalletManageDAO dao;
	@Resource(name="mauRfidCardManageDAO")
	private MauRfidCardManageDAO maudao;
	public MauRfidCardManageDAO getMaudao() {
		return maudao;
	}
	public void setMaudao(MauRfidCardManageDAO maudao) {
		this.maudao = maudao;
	}

	@Override
	public StorePalletManageDAO getEntityDaoInf() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	//getCount
	public Integer getCount(){
		return dao.getCount();
	}
	//delete by id
	public void removeRecordById(Integer id)
	{
		dao.removeById(id);
	}
	//delete collections
	public void removeCollections(ArrayList<Integer> col)
	{
		dao.removeCollections(col);
	}
	//saveOrUpdate
	public void saveOrUpdate(StorePallet entity)
	{
		dao.saveOrUpdate(entity);
	}
	//query records
	public List<?> queryRecords(DetachedCriteria criteria,Integer pageNo,Integer pageSize){
		return dao.queryRecords(criteria, pageNo, pageSize);
	}
	//get RFID
	public String getNewRfid()
	{
//		return maudao.getNewRfidCode();
		return null;
	}
}
