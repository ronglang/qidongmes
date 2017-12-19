package com.css.business.web.subsysstore.storeManage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysstore.bean.StoreSpec;
import com.css.business.web.subsysstore.storeManage.dao.StoreSpecManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("storeSpecManageService")
public class StoreSpecManageService extends BaseEntityManageImpl<StoreSpec,StoreSpecManageDAO>{

	@Resource(name="storeSpecManageDAO")
	private StoreSpecManageDAO dao;
	@Override
	public StoreSpecManageDAO getEntityDaoInf() {
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
	public void removeCollections(ArrayList<Integer> col) throws Exception
	{
		dao.removeCollections(col);
	}
	//saveOrUpdate
	public void saveOrUpdate(StoreSpec entity) throws Exception
	{
		dao.saveOrUpdate(entity);
	}
	//query records
	public List<?> queryRecords(DetachedCriteria criteria,Integer offset,Integer pageSize) throws Exception{
		return dao.queryRecords(criteria, offset, pageSize);
	}
}
