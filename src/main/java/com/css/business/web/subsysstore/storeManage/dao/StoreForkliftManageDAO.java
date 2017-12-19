package com.css.business.web.subsysstore.storeManage.dao;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.css.business.web.subsysmanu.bean.MauForklift;
import com.css.business.web.subsysmanu.bean.MauHandlingChores;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;
@Repository("storeForkliftManageDAO")
public class StoreForkliftManageDAO extends BaseEntityDaoImpl<MauForklift> {
	Page page = new Page();
	public Page queryForkliftPage(final Page pageParam, final String sql,
			final Object vo, final Object... values) {
		getHibernateTemplate().execute(new HibernateCallback<Page>() {
			@SuppressWarnings("unchecked")
			@Override
			public Page doInHibernate(Session session)
					throws HibernateException, SQLException {
				//Query query = session.createSQLQuery(sql); 
				String countQueryString = " select count(*) "
						+ removeSelect(removeOrders(sql));
				countQueryString = "";  
				countQueryString += "SELECT count(*) from ("
						+ removeOrders(sql) + " ) tt";
				List countlist = createSQLQuery(countQueryString)
						.list();
				long totalCount = Long.valueOf(countlist.get(0).toString())
						.longValue();
				int firstResult = Page.getStartOfPage(pageParam.getPage(),
						pageParam.getPagesize());
				Query query = session.createSQLQuery(sql);
				Query query2=query.setFirstResult(firstResult).setMaxResults(pageParam.getPagesize());
//				List list = query.setFirstResult(firstResult).setMaxResults(pageParam.getPagesize()).list();
				List list2=query.setFirstResult(firstResult).setMaxResults(pageParam.getPagesize()).list();
				List list=query2.setResultTransformer(Transformers.aliasToBean(vo.getClass())).list();
				page.setPagesize(pageParam.getPagesize()); 
				page.setData(list); 
				page.setPageNo(pageParam.getPageNo());
				page.setTotalCount(totalCount);
				return page;
			}
		});
		return page;
	}
	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql
				+ " must has a keyword 'from'");
		return hql.substring(beginPos);
	}
	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", 2);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	public MauForklift getEditData(Integer id) {
		// TODO Auto-generated method stub
		return (getHibernateTemplate().get(MauForklift.class,id));
	}
	public String saveOrUpdate(MauForklift manuForklift) {
		getHibernateTemplate().saveOrUpdate(manuForklift);
		return "success"; 
	}
	public void delete(final String[] str) {
	   getHibernateTemplate().execute(new HibernateCallback<MauForklift>(){
		@Override
		public MauForklift doInHibernate(Session session)
				throws HibernateException, SQLException {
			String hql="from MauForklift m where m.id in (:param)";
			@SuppressWarnings("unchecked")
			List<MauForklift> list=session.createQuery(hql).setParameter("param",str).list();
			getHibernateTemplate().delete(list);
			return null;
		}
	   }); 
	}
	public void toDelete(Integer id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(getHibernateTemplate().get(MauForklift.class,id));
	}
	
}
