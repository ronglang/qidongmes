package com.css.business.web.subsysstore.storeManage.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysstore.bean.StoreInventory;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;
@Repository("storeInventoryManageDAO")
public class StoreInventoryManageDAO extends BaseEntityDaoImpl<StoreInventory>{

	public void findByQueryPage(final String hql,final Page page, final List<Object> list) {
//		page = this.pageQuery(hql,page.getPageNo(),page.getPagesize(), list.toArray(new Object[list.size()]));
		getHibernateTemplate().execute(new HibernateCallback<Page>() {
			@SuppressWarnings("unchecked")
			@Override
			public Page doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query q = session.createQuery(hql);
				for (int i = 0; i < list.size(); i++) {
					Object obj = list.get(i);
					q.setParameter(i,obj);
				}
				int totalCount = q.list().size();
				page.setTotalCount(totalCount);
				int pageNO = page.getPageNo();
				int pagesize = page.getPagesize();
				q.setFirstResult((pageNO-1)*pagesize);
				q.setMaxResults(pageNO*pagesize);
				List<Object> lsit = q.list();
				page.setData(lsit);
				return null;
			}
		});
	}

}
