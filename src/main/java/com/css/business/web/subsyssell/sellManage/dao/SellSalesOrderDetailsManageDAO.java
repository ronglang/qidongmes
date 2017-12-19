package com.css.business.web.subsyssell.sellManage.dao;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsyssell.bean.SellSalesOrderDetails;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;



@Repository("sellSalesOrderDetailsManageDAO")
public class SellSalesOrderDetailsManageDAO extends BaseEntityDaoImpl<SellSalesOrderDetails>  {

	/**
	 * 
	 * @param page
	 * @param orderCode
	 */
	public void getByOrderCode(final Page page,final String orderCode,final String hql) {
		super.getHibernateTemplate().execute(new HibernateCallback<String>() {
			@SuppressWarnings("unchecked")
			@Override
			public String doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setParameter(0, orderCode  );
				page.setTotalCount(query.list().size());//设置总记录数量
				//获取结果集
				int pageNO = page.getPage();
				int pagesize = page.getPagesize();
				query.setFirstResult((pageNO-1)*pagesize);
				query.setMaxResults(pagesize*pageNO);
				page.setData(query.list());
				return null;
			}
		});
	}
	
	
}
