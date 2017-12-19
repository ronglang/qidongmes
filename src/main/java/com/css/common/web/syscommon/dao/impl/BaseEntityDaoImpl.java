package com.css.common.web.syscommon.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.css.common.util.GenericsUtils;
import com.css.common.util.ReflectHelper;
import com.css.common.util.StringUtil;
import com.css.common.web.syscommon.bean.BaseEntity;
import com.css.common.web.syscommon.bean.EntityField;
import com.css.common.web.syscommon.dao.IBaseEntityDaoInf;
import com.css.common.web.syscommon.dao.support.Page;

public class BaseEntityDaoImpl<T extends BaseEntity> extends
		HibernateDaoSupport implements IBaseEntityDaoInf<T> {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	protected Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public BaseEntityDaoImpl() {
		this.entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	@Autowired
	public void setSessionFactoryo(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	public T get(Serializable id) {
		return getHibernateTemplate().get(this.entityClass, id);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object get(Class objClass, Serializable id) {
		return getHibernateTemplate().get(objClass, id);
	}

	public List<T> getAll() {
		return getHibernateTemplate().loadAll(this.entityClass);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll(String orderBy, boolean isAsc) {
		Assert.hasText(orderBy);
		if (isAsc) {
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(this.entityClass).addOrder(
							Order.asc(orderBy)));
		}
		return getHibernateTemplate().findByCriteria(
				DetachedCriteria.forClass(this.entityClass).addOrder(
						Order.desc(orderBy)));
	}

	public Criteria createCriteria(Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(this.entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	public Criteria createCriteria(String orderBy, boolean isAsc,
			Criterion... criterions) {
		Assert.hasText(orderBy);
		Criteria criteria = createCriteria(criterions);
		if (isAsc) {
			criteria.addOrder(Order.asc(orderBy));
		} else {
			criteria.addOrder(Order.desc(orderBy));
		}
		return criteria;
	}

	@SuppressWarnings("unchecked")
	public List<T> findBy(String propertyName, Object value) {
		Assert.hasText(propertyName);
		return createCriteria(
				new Criterion[] { Restrictions.eq(propertyName, value) })
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<T> findBy(String propertyName, Object value, String orderBy,
			boolean isAsc) {
		Assert.hasText(propertyName);
		Assert.hasText(orderBy);
		return createCriteria(orderBy.trim(), isAsc,
				new Criterion[] { Restrictions.eq(propertyName.trim(), value) })
				.list();
	}

	@SuppressWarnings("unchecked")
	public T findUniqueBy(String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (T) createCriteria(
				new Criterion[] { Restrictions.eq(propertyName.trim(), value) })
				.uniqueResult();
	}

	public void remove(BaseEntity object) {
		getHibernateTemplate().delete(object);
	}

	public void removeById(Serializable id) {
		Object ids = null;
		if (id instanceof String) {
			ids = Integer.parseInt(id.toString());
		} else {
			ids = id;
		}
		String className = this.entityClass.getName();
		createQuery("delete " + className + " where id=?", new Object[] { ids }).executeUpdate();
	}

	@SuppressWarnings("rawtypes")
	public void removeById(Class objClass, Serializable id) {
		getHibernateTemplate().delete(get(objClass, id));
	}

	public void save(BaseEntity object) {
		// if (StringUtil.isEmpty(object.getId())) {
		// object.setId(null);
		// }
		getHibernateTemplate().saveOrUpdate(object);
	}

	/**
	 * 根据条件删除
	 * void
	 * 2015年12月16日 上午10:28:32
	 * @param object
	 * @throws Exception
	 */
	public void removeByCon(BaseEntity object) throws Exception {
		Map<String, Object> map = ReflectHelper.getAllFileds(object,true);
		Iterator<Entry<String, Object>> ites = map.entrySet().iterator();
		String classname = object.getClass().getName();
		List<Object> vals = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder("delete " + classname);
		if(map.size() > 0){
			hql.append(" where 1=1 ");
			while(ites.hasNext()){
				Entry<String, Object> ent = ites.next();
				String key = ent.getKey();
				Object val = ent.getValue();
				if(val == null || StringUtil.isEmpty(val.toString())){
					continue;
				}
				hql.append(" and ").append(key);
				hql.append(" = ?");
				vals.add(val);
			}
		}
		createQuery(hql.toString(), vals.toArray()).executeUpdate();
	}
	
	/**
	 * 保存更新对象，有ID时更新对象，无ID添加操作
	 * void
	 * 2016年1月25日 上午11:53:02
	 * @param object
	 * @param nullIsWrite 空值是否更新
	 * @throws Exception
	 */
	public void updateByCon(BaseEntity object,boolean nullIsUpdate) throws Exception {
		if(object.getId() == null){
			 this.save(object);
			 return;
		}
		Map<String, Object> map = ReflectHelper.getAllFileds(object,!nullIsUpdate);
		Iterator<Entry<String, Object>> ites = map.entrySet().iterator();
		String classname = object.getClass().getName();
		List<Object> vals = new ArrayList<Object>();
		StringBuilder hql = new StringBuilder("update " + classname);
		hql.append(" set ");
		if(map.size() > 0){
			while(ites.hasNext()){
				Entry<String, Object> ent = ites.next();
				String key = ent.getKey();
				Object val = ent.getValue();
				if("id".equals(key)){
					continue;
				}
				hql.append(key);
				hql.append(" = ? , ");
				vals.add(val);
			}
		}
		hql.append(" where  id="+object.getId());
		hql = hql.replace(hql.lastIndexOf(","), hql.lastIndexOf(",")+1, "  ");
		 createQuery(hql.toString(), vals.toArray()).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listQuery(String hql, Object... values) {
		System.out.println(hql);
		Query query = createQuery(hql, values);
		List<T> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public Page pageQuery(String hql, int pageNo, int pageSize,
			Object... values) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");

		String countQueryString = " select count (*) "
				+ removeSelect(removeOrders(hql));
		List<Object> countlist = getHibernateTemplate().find(countQueryString, values);
		long totalCount = Long.valueOf(countlist.get(0).toString()).longValue();
		if (totalCount < 1L) {
			Page page = new Page();
			page.setPagesize(pageSize);
			return page;
		}
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		Query query = createQuery(hql, values);

		List<Object> list = query.setFirstResult(startIndex).setMaxResults(pageSize)
				.list();
		return new Page(pageNo, totalCount, pageSize, list);
	}

	@SuppressWarnings("rawtypes")
	public Page pageSQLQuery(String sql, int pageNo, int pageSize,
			final Object... values) {
		Assert.hasText(sql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");

		final String countQueryString = " select count(*)"	+ removeSelect(removeOrders(sql));
		List countlist = createSQLQuery(countQueryString, values).list();
		
		long totalCount = Long.valueOf(countlist.get(0).toString()).longValue();
		if (totalCount < 1L) {
			Page page = new Page();
			page.setPagesize(pageSize);
			return page;
		}
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		Query query = createSQLQuery(sql, values);

		List list = query.setFirstResult(startIndex).setMaxResults(pageSize)
				.list();
		return new Page(pageNo, totalCount, pageSize, list);
	}

	public void deleteBySql(final String sql, final Object... values) {
		//Query query = createSQLQuery(sql, values);
		//query.executeUpdate();
		//Session session = sessionFactory.getCurrentSession(); 
	/*	Session session = this.getSession();
		try {
			Query query = session.createSQLQuery(sql);
			
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
			query.executeUpdate();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			session.close();
		}*/
		
		getHibernateTemplate().execute(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createSQLQuery(sql);
				for (int i = 0; i < values.length; i++) {
					query.setParameter(i, values[i]);
				}
				query.executeUpdate();
				return null;
			}
		});
	}

	/**
	 * sql 查询返回实体Page对象
	 * 
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @param clazz
	 *            实体
	 * @param values
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page pageSQLQuery(String sql, int pageNo, int pageSize, Class clazz,
			Object... values) {
		Assert.hasText(sql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");

		String countQueryString = " select count(*) "
				+ removeSelect(removeOrders(sql));
		List countlist = createSQLQuery(countQueryString, values).list();
		long totalCount = Long.valueOf(countlist.get(0).toString()).longValue();
		if (totalCount < 1L) {
			Page page = new Page();
			page.setPagesize(pageSize);
			return page;
		}
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		Query query = createSQLQuery(sql, clazz, values);

		List list = query.setFirstResult(startIndex).setMaxResults(pageSize)
				.list();
		return new Page(pageNo, totalCount, pageSize, list);
	}

	@SuppressWarnings("rawtypes")
	public Page pageSQLQuery(String sql, String countQuery, int pageNo,
			int pageSize, Class clazz, Object... values) {
		Assert.hasText(sql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");

		String countQueryString = " select count(*) "
				+ removeSelect(removeOrders(sql));
		if (!StringUtil.isEmpty(countQuery)) {
			countQueryString = countQuery;
		}
		List countlist = createSQLQuery(countQueryString, values).list();
		long totalCount = Long.valueOf(countlist.get(0).toString()).longValue();
		if (totalCount < 1L) {
			Page page = new Page();
			page.setPagesize(pageSize);
			return page;
		}
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		Query query = createSQLQuery(sql, clazz, values);

		List list = query.setFirstResult(startIndex).setMaxResults(pageSize)
				.list();
		return new Page(pageNo, totalCount, pageSize, list);
	}

	
	/**
	 * 
	 * Page
	 * 2015年11月18日 上午10:05:43
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @param vo
	 * @param values 条件查询参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page pageSQLQueryVO(String sql, int pageNo, int pageSize,Object vo,Object... values) throws Exception{
		Assert.hasText(sql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");

		String countQueryString = " select count(*) "+ removeSelect(removeOrders(sql));
		
		countQueryString  = "";
		countQueryString +="SELECT count(*) from ("+removeOrders(sql)+" ) tt";
		
		List countlist = createSQLQuery(countQueryString, values).list();
		long totalCount = Long.valueOf(countlist.get(0).toString()).longValue();
		if (totalCount < 1L) {
			Page page = new Page();
			page.setPagesize(pageSize);
			return page;
		}
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		try {
			if(ReflectHelper.isContainAttr(vo, "createDate")){
				sql += " order by create_Date desc";
			}
		} catch (Exception e) {
			String msg = "该对象没有createDate属性";
			this.logger.error(msg, e);
			throw new RuntimeException(msg, e);
		} 
		Query query = createSQLQuery(sql,values);
		
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).setResultTransformer(Transformers.aliasToBean(vo.getClass())).list();
		return new Page(pageNo, totalCount, pageSize, list);
	}
	
	/**
	 * 
	 * 没有order by 排序
	 * 2015年11月18日 上午10:05:43
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @param vo
	 * @param values 条件查询参数
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page pageSQLQueryVONoneDesc(String sql, int pageNo, int pageSize,Object vo,Object... values) throws Exception{
		Assert.hasText(sql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		
		String countQueryString = " select count(*) "+ removeSelect(removeOrders(sql));
		
		countQueryString  = "";
		countQueryString +="SELECT count(*) from ("+removeOrders(sql)+" ) tt";
		
		List countlist = createSQLQuery(countQueryString, values).list();
		long totalCount = Long.valueOf(countlist.get(0).toString()).longValue();
		if (totalCount < 1L) {
			Page page = new Page();
			page.setPagesize(pageSize);
			return page;
		}
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		/*try {
			if(ReflectHelper.isContainAttr(vo, "createDate")){
				sql += " order by create_Date desc";
			}
		} catch (Exception e) {
			String msg = "该对象没有createDate属性";
			this.logger.error(msg, e);
			throw new RuntimeException(msg, e);
		} */
		Query query = createSQLQuery(sql,values);
		
		List list = query.setFirstResult(startIndex).setMaxResults(pageSize).setResultTransformer(Transformers.aliasToBean(vo.getClass())).list();
		return new Page(pageNo, totalCount, pageSize, list);
	}
	
	/**
	 *  sql 分页查询
	 * Page
	 * 2015年10月27日 下午6:01:45
	 * @param sql 语句不代条件，条件放在 entity中
	 * @param paramPage
	 * @param vo 条件查询值对象
	 * @return
	 * @throws Exception
	 */
	public Page pageSqlQueryByVO(String sql ,Page paramPage, Object vo) throws Exception {
		Map<String, Object> map = ReflectHelper.getAllFileds(vo,true);
		Iterator<Entry<String, Object>> ites = map.entrySet().iterator();
		//String classname = vo.getClass().getName();
		List<Object> vals = new ArrayList<Object>();
		
		StringBuilder sqls = new StringBuilder(sql);
		if(map.size() > 0){
			sqls.append(" where 1=1 ");
			while(ites.hasNext()){
				Entry<String, Object> ent = ites.next();
				String key = ent.getKey();
				Object val = ent.getValue();
			
				if(val == null || StringUtil.isEmpty(val.toString())){
					continue;
				}
				Field f = ReflectHelper.getFieldByFieldName(vo, key);
				Column col = f.getAnnotation(Column.class);
				if(col != null && col.name() != null){
					key = col.name();
				}
				sqls.append(" and ").append(key);
				if(val instanceof String){
					sqls.append(" like ?");
					vals.add("%"+val+"%");
				}else {
					sqls.append(" = ?");
					vals.add(val);
				}
			}
		}
		paramPage = this.pageSQLQueryVO(sqls.toString(), paramPage.getPageNo(), paramPage.getPagesize(),vo, vals.toArray());
		return paramPage;
	}
	
	/**
	 * 
	 * List
	 * 2015年11月18日 上午10:13:18
	 * @param sql
	 * @param vo 条件查询值对象
	 * @return list
	 * @throws Exception
	 */
	public List<Object> listSQLQueryByVO(String sql , Object vo) throws Exception {
		Map<String, Object> map = ReflectHelper.getAllFileds(vo,true);
		Iterator<Entry<String, Object>> ites = map.entrySet().iterator();
		StringBuilder sqls = new StringBuilder(sql).append(" where 1=1 ");
		List<Object> vals = new ArrayList<Object>();
		while(ites.hasNext()){
			Entry<String, Object> ent = ites.next();
			String key = ent.getKey();
			Object val = ent.getValue();
		
			if(val == null || StringUtil.isEmpty(val.toString())){
				continue;
			}
			Field f = ReflectHelper.getFieldByFieldName(vo, key);
			Column col = f.getAnnotation(Column.class);
			if(col != null && col.name() != null){
				key = col.name();
			}
			sqls.append(" and ").append(key);
			if(val instanceof String){
				sqls.append(" like ?");
				vals.add("%"+val+"%");
			}else {
				sqls.append(" = ?");
				vals.add(val);
			}
		}
		return this.listSQLQueryVO(sqls.toString(),vo, vals.toArray());
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object> listSQLQueryVO(String sql,Object vo, Object... values) throws Exception{
		try {
			if(ReflectHelper.isContainAttr(vo, "createDate")){
				sql += " order by create_Date desc";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		Query query = createSQLQuery(sql, values).setResultTransformer(Transformers.aliasToBean(vo.getClass()));
		List<Object> list = query.list();
		return list;
	}
	
	/**
	 * SQL查询 List 2015年10月12日 下午10:08:54
	 * 
	 * @param sql
	 * @param pageNo
	 * @param pageSize
	 * @param clazz
	 * @param values
	 * @return List
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List listSQLQuery(String sql, Class clazz, Object... values) {
		Query query = createSQLQuery(sql, clazz, values);
		List<Object> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Object> listSQLQuery(String sql, Object... values) {
		Query query = createSQLQuery(sql, values);
		List<Object> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Object> listSQLQueryByEntity(String sql, Object entity)
			throws Exception {
		Map<String, Object> map = ReflectHelper.getAllFileds(entity, true);
		Iterator<Entry<String, Object>> ites = map.entrySet().iterator();
		StringBuilder sqls = new StringBuilder(sql).append(" where 1=1 ");
		List<Object> vals = new ArrayList<Object>();
		while (ites.hasNext()) {
			Entry<String, Object> ent = ites.next();
			String key = ent.getKey();
			Object val = ent.getValue();

			if (val == null || StringUtil.isEmpty(val.toString())) {
				continue;
			}
			Field f = ReflectHelper.getFieldByFieldName(entity, key);
			Column col = f.getAnnotation(Column.class);
			if (col != null && col.name() != null) {
				key = col.name();
			}
			sqls.append(" and ").append(key);
			if (val instanceof String) {
				sqls.append(" like ?");
				vals.add("%"+val + "%");
			} else {
				sqls.append(" = ?");
				vals.add(val);
			}
		}
		return this.listSQLQuery(sqls.toString(), entity.getClass(),
				vals.toArray());
	}

	/**
	 * sql 分页查询 Page 2015年10月27日 下午6:01:45
	 * 
	 * @param sql
	 *            语句不代条件，条件放在 entity中
	 * @param paramPage
	 * @param entity
	 *            条件查询实体
	 * @return
	 * @throws Exception
	 */
	public Page pageSqlQuery(String sql, Page paramPage, Object entity)
			throws Exception {
		Map<String, Object> map = ReflectHelper.getAllFileds(entity, true);
		Iterator<Entry<String, Object>> ites = map.entrySet().iterator();
		//String classname = entity.getClass().getName();
		StringBuilder sqls = new StringBuilder(sql).append(" where 1=1 ");
		List<Object> vals = new ArrayList<Object>();
		while (ites.hasNext()) {
			Entry<String, Object> ent = ites.next();
			String key = ent.getKey();
			Object val = ent.getValue();

			if (val == null || StringUtil.isEmpty(val.toString())) {
				continue;
			}
			Field f = ReflectHelper.getFieldByFieldName(entity, key);
			Column col = f.getAnnotation(Column.class);
			if (col != null && col.name() != null) {
				key = col.name();
			}
			sqls.append(" and ").append(key);
			if (val instanceof String) {
				sqls.append(" like ?");
				vals.add("%"+val + "%");
			} else {
				sqls.append(" = ?");
				vals.add(val);
			}
		}
		paramPage = this.pageSQLQuery(sqls.toString(), paramPage.getPageNo(),
				paramPage.getPagesize(), vals.toArray());
		return paramPage;
	}

	@SuppressWarnings("rawtypes")
	public String getIdName(Class clazz) {
		Assert.notNull(clazz);
		ClassMetadata meta = getSessionFactory().getClassMetadata(clazz);
		Assert.notNull(meta, "Class " + clazz
				+ " not define in hibernate session factory.");
		String idName = meta.getIdentifierPropertyName();
		Assert.hasText(idName, clazz.getSimpleName()
				+ " has no identifier property define.");
		return idName;
	}
	
	/**
	 * hibernate实体查询，不能使用VO
	 * @param sql
	 * @param clazz
	 * @param values
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Query createSQLQuery(String sql, Class clazz, Object... values) {
		Assert.hasText(sql);
		//Query query = getSession().createSQLQuery(sql).addEntity(clazz);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql).addEntity(clazz);

		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		
		return query;
	}

	/**
	 * VO查询，需要setResultTransformer(Transformers.aliasToBean(vo.getClass()
	 * @param sql
	 * @param values
	 * @return
	 */
	public Query createSQLQuery(final String sql,final  Object... values) {
		Assert.hasText(sql);
		//Query query = getSession().createSQLQuery(sql);
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query;
	}
	public Query createQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}

		return query;
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
	@Override
	public Page pageQuery(HttpServletRequest request,Page paramPage, T entity) throws Exception {
		Map<String, Object> map = ReflectHelper.getAllFileds(entity, true);
		Iterator<Entry<String, Object>> ites = map.entrySet().iterator();
		String classname = this.entityClass.getName();
		StringBuilder hql = new StringBuilder("from " + classname
				+ " where 1=1 ");
		List<Object> vals = new ArrayList<Object>();
		while (ites.hasNext()) {
			Entry<String, Object> ent = ites.next();
			String key = ent.getKey();
			Object val = ent.getValue();
			if (val == null || StringUtil.isEmpty(val.toString())) {
				continue;
			}
			hql.append(" and ").append(key);
			if (val instanceof String) {
				hql.append(" like ?");
				vals.add("%"+val + "%");
			} else {
				hql.append(" = ?");
				vals.add(val);
			}
		}
		// leitao 判断实体中是否存在hql字段，并且不为空，有说明是高级查询，忽略其他查询条件，只用hql条件查询
		//使用高级查询的时候，请在实体中添加虚拟字段 String hql;
		if (ReflectHelper.isContainAttrIncludeTransient(entity, "hql")) {
			Object obj = ReflectHelper.getValueByFieldName(entity, "hql");
			if (obj != null && obj.toString().length() != 0) {
				String hqlStr="from " + classname
						+ " where "+(String) ReflectHelper.getValueByFieldName(entity, "hql");
				//decodeURIComponent()中不识别“%”，因此用MD5将%加密后的密文(5bd8a48eda1b26eb)作为传入参数，然后在替换回来查询
				//如果有好的办法解决此问题，请告诉我。leitao
				hqlStr=hqlStr.replace("5bd8a48eda1b26eb", "%");
				return this.pageQuery(hqlStr, paramPage.getPageNo(),
						paramPage.getPagesize());
			}
		}
		
		paramPage = this.pageQuery(hql.toString(), paramPage.getPageNo(),
				paramPage.getPagesize(), vals.toArray());
		return paramPage;
	}

	/**
	 * 实体查询
	 * @param paramPage
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public Page pageQuery(Page paramPage, Object entity) throws Exception {
		Map<String, Object> map = ReflectHelper.getAllFileds(entity, true);
		Iterator<Entry<String, Object>> ites = map.entrySet().iterator();
		String classname = entity.getClass().getName();
		StringBuilder hql = new StringBuilder("from " + classname
				+ " where 1=1 ");
		List<Object> vals = new ArrayList<Object>();
		while (ites.hasNext()) {
			Entry<String, Object> ent = ites.next();
			String key = ent.getKey();
			Object val = ent.getValue();
			if (val == null || StringUtil.isEmpty(val.toString())) {
				continue;
			}
			hql.append(" and ").append(key);
			if (val instanceof String) {
				hql.append(" like ?");
				vals.add("%" + val + "%");
			} else {
				hql.append(" = ?");
				vals.add(val);
			}
		}
		paramPage = this.pageQuery(hql.toString(), paramPage.getPageNo(),
				paramPage.getPagesize(), vals.toArray());
		return paramPage;
	}
	/**
	 * 有数据权限的条件查询
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> listQuery(HttpServletRequest request,T entity,String token) throws Exception {
		String classname = this.entityClass.getName();
		StringBuilder hql = new StringBuilder("from " + classname
				+ " where 1=1 ");
		List<Object> vals = new ArrayList<Object>();
		if (entity != null) {
			Map<String, Object> map = ReflectHelper.getAllFileds(entity, true);
			Iterator<Entry<String, Object>> ites = map.entrySet().iterator();
			while (ites.hasNext()) {
				Entry<String, Object> ent = ites.next();
				String key = ent.getKey();
				Object val = ent.getValue();
				if (val == null || StringUtil.isEmpty(val.toString())) {
					continue;
				}
				hql.append(" and ").append(key);
				if (val instanceof String) {
					hql.append(" like ?");
					vals.add("%"+val + "%");
				} else {
					hql.append(" = ?");
					vals.add(val);
				}
			}
		}
		Query query = createQuery(hql.toString(), vals.toArray());
		return query.list();
	}
	
	/**
	 * 无数据权限的条件查询
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<T> listQuery(T entity) throws Exception {
		String classname = this.entityClass.getName();
		StringBuilder hql = new StringBuilder("from " + classname
				+ " where 1=1 ");
		List<Object> vals = new ArrayList<Object>();
		if (entity != null) {
			Map<String, Object> map = ReflectHelper.getAllFileds(entity, true);
			Iterator<Entry<String, Object>> ites = map.entrySet().iterator();
			while (ites.hasNext()) {
				Entry<String, Object> ent = ites.next();
				String key = ent.getKey();
				Object val = ent.getValue();
				if (val == null || StringUtil.isEmpty(val.toString())) {
					continue;
				}
				hql.append(" and ").append(key);
				if (val instanceof String) {
					hql.append(" like ?");
					vals.add("%"+val + "%");
				} else {
					hql.append(" = ?");
					vals.add(val);
				}
			}
		}
		Query query = createQuery(hql.toString(), vals.toArray());
		return query.list();
	}

	// 注意未处理名称相同的情况！
	@Override
	public List<EntityField> getMetaField(final String tableName)
			throws Exception {
		StringBuffer sqlStr = new StringBuffer();
		sqlStr.append(
				"select m.CN_NAME colName,m.SH_NAME col,m.FIELD_TYPE typeCode,d.VALUE typeName,m.FIELD_LEN colLen,m.PRECISION colPrecision\n")
				.append(" from SYS_DBMETADATA m LEFT JOIN SYS_DICTIONARY d on m.FIELD_TYPE = d.CODE\n")
				.append(" where m.PARENT_ID = (\n")
				.append(" SELECT d.ID FROM SYS_DBMETADATA d where d.SH_NAME = ?\n")
				.append(" )\n");

		final String sql = sqlStr.toString();
		List<EntityField> list = getHibernateTemplate().execute(
				new HibernateCallback<List<EntityField>>() {
					@SuppressWarnings("unchecked")
					@Override
					public List<EntityField> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createSQLQuery(sql);
						query.setParameter(0, tableName);
						query.setResultTransformer(Transformers
								.aliasToBean(EntityField.class));
						return query.list();
					}
				});
		return list;
	}


	@SuppressWarnings("unchecked")
	public List<Object[]> queryBySql(final String sql) {
		/*List<Object[]> list = sessionFactory.getCurrentSession()
				.createSQLQuery(sql).list();
		return list;*/
		List<Object[]> list = (List<Object[]>) this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
							List<Object> ls = null;
							ls = session.createSQLQuery(sql).list();
							return ls;
					}
				}
			);
		return list;
	}

	/**
	 * 执行原生sql语句
	 * @param sqlCount
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object> queryBySQL(final String sqlCount) {
		List<Object> list = (List<Object>) this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						List<Object> ls = null;
						ls = session.createSQLQuery(sqlCount).list();
						return ls;
					}
				});
		return list;
	}
	
	/**
	 * @TODO: 为sql非hql的count查询增加外包装select count(*) from (原sql) XX.
	 * @author: zhaichunlei
	 & @DATE : 2016年3月8日
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("unused")
	private  String addCount(String sql) {
		Assert.hasText(sql);
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*)  from (");
		sb.append( removeOrders(sql));
		sb.append(") sqlxxcount ");
		return sb.toString();
	}
	
	/**
	 * 根据实体名查询(可按条件查询)
	 * List<Object>
	 * 2016年1月12日 下午5:10:41
	 * @param entityName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List <Object> queryAll(Object entity)throws Exception{
		Map<String, Object> map = ReflectHelper.getAllFileds(entity,true);
		Iterator<Entry<String, Object>> ites = map.entrySet().iterator();
		String classname =entity.getClass().getName();
		StringBuilder hql = new StringBuilder("from "+classname+" where 1=1 ");
		List<Object> vals = new ArrayList<Object>();
		while(ites.hasNext()){
			Entry<String, Object> ent = ites.next();
			String key = ent.getKey();
			Object val = ent.getValue();
			if(val == null || StringUtil.isEmpty(val.toString())){
				continue;
			}
			hql.append(" and ").append(key);
			if(val instanceof String){
				hql.append(" like ?");
				vals.add("%"+val+"%");
			}else {
				hql.append(" = ?");
				vals.add(val);
			}
		}
		if(ReflectHelper.isContainAttr(entityClass.newInstance(), "createDate")){
			hql.append(" order by createDate desc") ;
		}
		Query query = createQuery(hql.toString(), vals.toArray());
		return query.list();
	}
	
	/**
	 * @TODO:取序列的值
	 * @author: zhaichunlei
	 & @DATE : 2017年7月20日
	 * @param seqName  序列名称。 不要写sql
	 * @return
	 */
	public String seqNextVal(String seqName){
		final String sql = "select nextVal('"+seqName+"')";
		/*String str = this.createSQLQuery(sql).uniqueResult().toString();
		return str;*/
		
		String str = (String) this.getHibernateTemplate().execute(
				new HibernateCallback<String>() {
					@Override
					public String doInHibernate(Session session)
							throws HibernateException, SQLException {
							return session.createSQLQuery(sql).uniqueResult().toString();
					}
				}
			);
		return str;
	}
	
	private boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * @todo  执行函数
	 * @param funName
	 * @param o
	 * @return
	 */
	public Object exeFunction(String funName,Object ...o){
		final StringBuffer sql = new StringBuffer("select "+funName+"(");
		StringBuffer sql2 = new StringBuffer();
		for(Object oo : o){
			if(oo instanceof String)
				sql2.append("'"+oo.toString()+"',");
			else if(isNumeric(oo.toString())){
				sql2.append(oo.toString()+",");
			}
			else{
				sql2.append(oo.toString()+",");
			}
		}
		if(sql2.length() > 0 ){
			sql.append(sql2.substring(0, sql2.length()-1));
		}
		sql.append(")");
		/*Object str = this.createSQLQuery(sql.toString()).uniqueResult();
		return str;*/
		Object str = (Object) this.getHibernateTemplate().execute(
				new HibernateCallback<Object>() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
							return session.createSQLQuery(sql.toString()).uniqueResult();
					}
				}
			);
		return str;
	}
	
	public String exeCode(String funcName){
		StringBuffer sql = new StringBuffer(" select  "+funcName+"()");
		Map<String,Object> map = jdbcTemplate.queryForMap(sql.toString());
		String rs = map.get(funcName).toString();
		return rs;
	}
	
	public void saveOrUpdateBatch(final List<T> list){
		getHibernateTemplate().execute(new HibernateCallback<T>() {
			@Override
			public T doInHibernate(Session session) throws HibernateException,
					SQLException {
				int count  = 0;
				for(T t : list){
					session.saveOrUpdate(t);
					if(++count%30==0){
						session.flush();
					}
				}
				return null;
			}
		});
	}
}
