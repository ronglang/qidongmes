package com.css.business.web.subsysstore.storeManage.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysstore.bean.StoreSpec;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository
@RequestMapping("/storeSpecManageDAO")
public class StoreSpecManageDAO extends BaseEntityDaoImpl<StoreSpec>{
	//CURD
	public StoreSpec getEntity(Integer id)
	{
		return this.getHibernateTemplate().get(StoreSpec.class, id);
	}
	//getCount
	public Integer getCount(){
		StringBuilder hql = new StringBuilder("select count(*) from StoreSpec");
		Number count =  (Number) getHibernateTemplate().find(hql.toString()).iterator().next();
		return count.intValue();
	}
	//saveOrUpdate
	public void saveOrUpdate(StoreSpec entity) throws Exception
	{
		//update while entity.id is not null
		this.getHibernateTemplate().saveOrUpdate(entity);
	}
	//delete by id
	public void removeRecordById(Integer id)
	{
		StoreSpec entity = getEntity(id);
		this.getHibernateTemplate().delete(entity);
	}
	//delete collections by conditions
	public void removeCollections(ArrayList<Integer> col) throws Exception
	{
		ArrayList<StoreSpec> entities = new ArrayList<StoreSpec>();
		for(Integer id :col)
		{
			entities.add(getEntity(id));
		}
		this.getHibernateTemplate().deleteAll(entities);
	}
	//query records from tables
	public List<?> queryRecords(DetachedCriteria criteria,Integer offset,Integer pageSize) throws Exception
	{
		return this.getHibernateTemplate().findByCriteria(criteria, offset, pageSize);
	}
}
