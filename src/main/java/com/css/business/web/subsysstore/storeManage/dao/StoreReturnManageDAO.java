package com.css.business.web.subsysstore.storeManage.dao;

import java.util.List;

import oracle.net.aso.q;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysstore.bean.StoreReturn;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("storeReturnManageDAO")
public class StoreReturnManageDAO extends BaseEntityDaoImpl<StoreReturn>  {

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param string
	 * @param list
	 * @return         
	 */ 
	public List<StoreReturn> queryByCondition(String hql, Object... values) {
		// TODO Auto-generated method stub
		try {
			Query query = createQuery(hql, values);
			List<StoreReturn> resultList = query.list();
			return resultList;
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	

}
