package com.css.business.web.subsysstore.storeManage.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysstore.bean.StorePallet;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("storePalletManageDAO")
public class StorePalletManageDAO extends BaseEntityDaoImpl<StorePallet>{
		//CURD
		public StorePallet getEntity(Integer id)
		{
			return this.getHibernateTemplate().get(StorePallet.class, id);
		}
		//getCount
		public Integer getCount(){
			StringBuilder hql = new StringBuilder("select count(*) from StorePallet");
			Number count =  (Number) getHibernateTemplate().find(hql.toString()).iterator().next();
			return count.intValue();
		}
		
		//saveOrUpdate
		public void saveOrUpdate(StorePallet entity)
		{
			//update while entity.id is not null
			this.getHibernateTemplate().saveOrUpdate(entity);;
		}
		//delete by id
		public void removeRecordById(Integer id)
		{
			StorePallet entity = getEntity(id);
			this.getHibernateTemplate().delete(entity);
		}
		//delete collections by conditions
		public void removeCollections(ArrayList<Integer> col)
		{
			ArrayList<StorePallet> entities = new ArrayList<StorePallet>();
			for(Integer id :col)
			{
				entities.add(getEntity(id));
			}
			this.getHibernateTemplate().deleteAll(entities);
		}
		//query records from tables
		public List<?> queryRecords(DetachedCriteria criteria,Integer pageNo,Integer pageSize)
		{
			return this.getHibernateTemplate().findByCriteria(criteria, pageNo, pageSize);
		}
}
