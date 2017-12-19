package com.css.common.web.syscommon.dao.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.css.common.web.syscommon.dao.support.Page;
public class BaseStatisticsDAO extends HibernateDaoSupport  {
	@Autowired
	//@Resource(name = "sessionFactory")
	public void setSessionFactoryo(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 * 统计查询分页
	 * @param sql 接收统计查询sql
	 * @param clazz 接收实体（非必须条件）
	 * @param page 分页对象
	 * @param values 接收参数（非必须）
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page baseCount(String sql,Class clazz,Page page,Object... values){
		//String countQueryString = " select count(*) "+ removeSelect(removeOrders(sql));
		
		String countQueryString = "select count(*) from ("+sql+") as tm";
		List<Object> countlist = createSQLQuery(countQueryString, values).list();
		long totalCount = Long.valueOf(countlist.get(0).toString()).longValue();
		page.setTotalCount(totalCount);
		
		int startIndex = Page.getStartOfPage(page.getPageNo(), page.getPagesize());
		Query query = createSQLQuery(sql, clazz,values);
		
		List<Object> list = query.setFirstResult(startIndex).setMaxResults(page.getPagesize()).list();
		page.setData(list);
		
		return page;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Object>  baseCount(String sql,Class clazz,Object... values){
		Query query = createSQLQuery(sql, clazz,values);
		List<Object> list = query.list();
		return list;
	}
	
	public Query createSQLQueryTotal(String sql, Object... values) {
		Assert.hasText(sql);
		Query query = getSession().createSQLQuery(sql);
		
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		
		
		return query;
	}
	
	@SuppressWarnings("rawtypes")
	public Query createSQLQuery(String sql,Class clazz, Object... values) {
		Assert.hasText(sql);
		Query query = null;
		Session session = this.getSessionFactory().getCurrentSession();
		if(session == null){
			session = this.getSessionFactory().openSession();
		}
		
		query = session.createSQLQuery(sql).addEntity(clazz);
		
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		
		return query;
	}
	
	public Query createSQLQuery(String sql, Object... values) {
		Assert.hasText(sql);
		Query query = getSession().createSQLQuery(sql);
		
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		
		return query;
	}
	
	@SuppressWarnings("unused")
	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql
				+ " must has a keyword 'from'");
		return hql.substring(beginPos);
	}
	
	@SuppressWarnings("unused")
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
	
}

