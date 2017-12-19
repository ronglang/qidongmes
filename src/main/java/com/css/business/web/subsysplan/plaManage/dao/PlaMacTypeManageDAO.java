package com.css.business.web.subsysplan.plaManage.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysplan.bean.PlaMacType;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("plaMacTypeManageDAO")
public class PlaMacTypeManageDAO extends BaseEntityDaoImpl<PlaMacType>  {

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param condition 状态
	 * @param area 区域
	 * @return         
	 */ 
	public Long queryCount(String condition, String area) {
		String hql = "select count(*) from PlaMacType where 1= 1 ";
		StringBuilder sb = new StringBuilder(hql);
		if (condition!=null && condition!="") {
			hql = "select count(*) from PlaMacType where mac_state = '"+condition+"'";
			sb.append(" and mac_state = '"+condition+"'");
		}
		//机台现在没有区域划分???!!
		/*if (area!=null && area!="") {
			sb.append(" and ");
		}*/
		// TODO Auto-generated method stub
		Query createQuery = this.createQuery(hql);
		Long count =  (Long)createQuery.uniqueResult();
		return count;
	}

}
