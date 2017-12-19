package com.css.business.web.subsysstore.storeManage.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.css.business.web.subsysstore.bean.StoreMaterialBasicInfo;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository
@RequestMapping("/storeMaterialBasicInfoManageDAO")
public class StoreMaterialBasicInfoManageDAO extends BaseEntityDaoImpl<StoreMaterialBasicInfo> {

	public Integer getPageCount()
	{
		//Hibernate3以后，返回的是LONG类型，数据不能直接转Integer，需要父类Number转换
		String hql = "select count(*) from StoreMaterialBasicInfo";
		Number count =  (Number) getHibernateTemplate().find(hql).iterator().next();
		return count.intValue();
	}
	//返回list
	public List<?> getlist(String params) throws DataAccessException
	{
		String hql = "from StoreMaterialBasicInfo where 1=1 ";
		if(params!=null)
		{
			hql+=params;
		}
		List<?> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	public void getObjectById(Object obj ,String value){
		
	}
	
	public StoreMaterialBasicInfo getEntityById(Integer id)
	{
		return getHibernateTemplate().get(StoreMaterialBasicInfo.class, id);
	}
	@SuppressWarnings("unchecked")
	public List<String> getRfid(String params)
	{		
		String hql = "select rfid from StoreMaterialBasicInfo where id in (%s)";
		hql = String.format(hql, params);
		Query query = getSession().createQuery(hql);
		return query.list();
	}
	public void deleteByCollection(String params) throws Exception
	{
		String hql = "delete from StoreMaterialBasicInfo where id in (%s)";
		hql = String.format(hql, params);
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
	}
	public void saveOrUpdate(StoreMaterialBasicInfo entity) throws Exception
	{
		getHibernateTemplate().saveOrUpdate(entity);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<String> getSupplier(String hql) {
		// TODO Auto-generated method stub
		List<String> list = this.createQuery(hql).list();
		
		return list;
	}
	
	/**
	 * 查询所有类别
	 * @return
	 */
	public List<StoreMaterialBasicInfo> getAllDataTypeDao(){
		String sql = "from StoreMaterialBasicInfo where material_type is not null and mater_ggxh is null or mater_ggxh = '' ";
		List<StoreMaterialBasicInfo> list = this.listQuery(sql);
		return list;
	}
	
	
	/**
	 * 查询是否存在此规格型号
	 * @param ggxh
	 * @return
	 */
	@SuppressWarnings("null")
	public boolean getGgxh(String ggxh){
		String sql = "from StoreMaterialBasicInfo where mater_ggxh = '"+ggxh+"'";
		List<StoreMaterialBasicInfo> list = this.listQuery(sql);
		if(list.size() > 0){
			return false;
		}else{
			return true;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
