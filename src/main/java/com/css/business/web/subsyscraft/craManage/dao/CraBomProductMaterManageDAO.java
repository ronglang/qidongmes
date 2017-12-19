package com.css.business.web.subsyscraft.craManage.dao;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsyscraft.bean.CraBomProductMater;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("craBomProductMaterManageDAO")
public class CraBomProductMaterManageDAO extends BaseEntityDaoImpl<CraBomProductMater>  {

	public void findByQueryPage(final Page page,final String hql ,final Object...values) {
		
		super.getHibernateTemplate().execute(new HibernateCallback<String>() {

			@SuppressWarnings("unchecked")
			@Override
			public String doInHibernate(Session session) throws HibernateException,
					SQLException {
				int pageNumber = page.getPageNo();
				int pagesize = page.getPagesize();
				
				Query q = session.createQuery(hql);
				for (int i = 0; i < values.length; i++) {
					q.setParameter(i, values[i]);
				}
				page.setTotalCount(q.list().size());
				q.setFirstResult((pageNumber-1)*pagesize);
				q.setMaxResults(pageNumber*pagesize);
				page.setData(q.list());
				return null;
			}
		});
	}

}
