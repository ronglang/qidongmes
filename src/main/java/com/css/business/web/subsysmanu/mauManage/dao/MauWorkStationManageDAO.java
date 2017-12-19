package com.css.business.web.subsysmanu.mauManage.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauWorkStation;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("mauWorkStationManageDAO")
public class MauWorkStationManageDAO extends BaseEntityDaoImpl<MauWorkStation>  {
	/** 
	 * @method: 获取某一张表中分页所需记录和所有的记录统计,
	 * @param:true返回所有的记录，false返回符合分页查询的记录
	 * @param:page 当前页号码
	 * @param:pagesize 每页显示多少条数据
	 * @values：需要传入到hql语句中的参数
	 * @author:曾斌
	 * @date:2017-06-27
	 */
	public List<?> getPagingQueryToolDao(final String hql,final boolean b,final int page,final int pagesize, final Object...values) {
		return getHibernateTemplate().execute(new HibernateCallback<List<?>>() {
			@SuppressWarnings("unused")
			@Override
			public List<?> doInHibernate(Session session)throws HibernateException, SQLException {
				Query q = session.createQuery(hql);
				for (int i = 0; i < values.length; i++) {
					q.setParameter(i, values[i]);
				}
				if(true==b){
					return q.list();
				}else{
					q.setMaxResults(pagesize);
					q.setFirstResult(page*pagesize-pagesize);
					List<?> list = q.list();
					return list;
				}
			}
		});
	}
	public Page queryMauWorkStationDao(String hql,Page p) throws Exception{
		Page page = this.pageQuery(hql, p.getPageNo(), p.getPagesize());
		//Page page = this.pageSQLQueryVONoneDesc(sql, p.getPageNo(), p.getPagesize(), new MauWorkStationVo());
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getChartDao(String hql){
		List<Object[]>  list=new ArrayList<>();
		try {
			list=this.createSQLQuery(hql).list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public List<?> getObjSortFromSysCommedicManageDao(String hql,String objSort) {
		List<?> list = this.createQuery(hql, objSort).list();
		return list;
	}
	public List<?> testDateManageDao(String hql, Integer id) {
		List<?> list = this.createQuery(hql, id).list();
		return list;
	}
	public List<?> getObjSortForExcel(String hql, Object...values){
		List<?> list = this.createQuery(hql, values).list();
		return list;
	}
	public List<Object[]> getChartList(String sql) {
		List<Object[]> list = new ArrayList<>();
		try {
			
			list=this.createSQLQuery(sql).list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 
	 *@data:2017年7月19日
	@param sql
	@return
	@autor:wl
	 */
	public List<Object[]> getMessageOne(String sql) {
		List<Object[]> list=null;
		try {
			
			 list =this.createSQLQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public List<Object[]> getMessageTwo(String sql) {
		List<Object[]> list=null;
		try {
			
			 list =this.createSQLQuery(sql).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
