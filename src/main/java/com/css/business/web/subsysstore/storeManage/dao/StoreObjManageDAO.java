package com.css.business.web.subsysstore.storeManage.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysstore.bean.StoreObj;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("storeObjManageDAO")
public class StoreObjManageDAO extends BaseEntityDaoImpl<StoreObj>  {
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
	public Page queryStoreObjDao(String hql,Page p) throws Exception{
		Page page = this.pageQuery(hql, p.getPageNo(), p.getPagesize());
		//Page page = this.pageSQLQueryVONoneDesc(sql, p.getPageNo(), p.getPagesize(), new StoreObjVo());
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public List<StoreObj> getChartDao(String hql,String objSort){
		List<StoreObj> list = this.createQuery(hql,objSort).list();
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
	public List<StoreObj> getStoreObjByRfid(String rfid){
		String hql = " from StoreObj where rfidCode = ? ";
		@SuppressWarnings("unchecked")
		List<StoreObj> list = this.createQuery(hql, rfid).list();
		if(list.size()<1|| list==null){
			return null;
		}
		return list;
	}
}
