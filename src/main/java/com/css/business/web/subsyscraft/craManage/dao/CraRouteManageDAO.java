package com.css.business.web.subsyscraft.craManage.dao;

import java.sql.SQLException;
import java.util.List;

import oracle.net.aso.l;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsyscraft.bean.CraRoute;
import com.css.business.web.subsyscraft.bean.CraRouteSeq;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("craRouteManageDAO")
public class CraRouteManageDAO extends BaseEntityDaoImpl<CraRoute>  {

	/** 
	 * @method: 获取某一张表中分页所需记录和所有的记录统计,
	 * @param:true返回所有的记录，false返回符合分页查询的记录
	 * @param:page 当前页号码
	 * @param:pagesize 每页显示多少条数据
	 * @values：需要传入到hql语句中的参数
	 * @author:曾斌
	 * @date:2017-06-6
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
	/** 
	 * @method: 针对多表的根据id删除操作
	 * @param:hql执行删除的hql语句
	 * @param: ids 存放id的数组
	 * @author:曾斌
	 * @date:2017-06-06
	 */
	public void deleteByIdsDao(int[] ids, String hql) {
		Session session = this.getSession();
		for (int i = 0; i < ids.length; i++) {
			Query q = session.createQuery(hql);
			q.setParameter(0, ids[i]);
			q.executeUpdate();
		}
	}
	/** 
	 * @method: 针对多表的根据routeCode删除操作
	 * @param:hql执行删除的hql语句
	 * @param: routeCodes 存放routeCode的数组
	 * @author:曾斌
	 * @date:2017-06-06
	 */
	public void deleteByRouteCodesDao(String[] routeCodes, String hql) {
		Session session = this.getSession();
		for (int i = 0; i < routeCodes.length; i++) {
			Query q = session.createQuery(hql);
			q.setParameter(0, routeCodes[i]);
			q.executeUpdate();
		}
	}
	/** 
	 * @method: 跟新CraRoute表中的某一条记录
	 * @author:曾斌
	 * @date:2017-06-07
	 */
	public void updateCraRouteDao(CraRoute craRoute) {
		this.getSession().update(craRoute);
	}
	/** 
	 * @method: 跟新CraRouteSeq表中的某一条记录
	 * @author:曾斌
	 * @date:2017-06-07
	 */
	public void updateCraRouteSeqDao(CraRouteSeq craRouteSeq) {
		this.getSession().update(craRouteSeq);
	}
	/** 
	 * @method: 新增CraRoute表中的一条记录
	 * @author:曾斌
	 * @date:2017-06-08
	 */
	public void addCraRouteDao(CraRoute craRoute) {
		this.getSession().save(craRoute);	
	}
	/** 
	 * @method: 新增CraRouteSeq表中的一条记录
	 * @author:曾斌
	 * @date:2017-06-08
	 */
	public void addCraRouteSeqDao(CraRouteSeq craRouteSeq) {
		this.getSession().save(craRouteSeq);
	}
	
	public CraRoute getObjByCode(String routeCode){
		List<CraRoute> lst = this.findBy("routeCode", routeCode);
		if(lst == null || lst.size() == 0) return null;
		
		return lst.get(0);
	}
	
	public CraRoute getByCodeAndStep(String proGgxh, String seqName,
			Integer seqStep) {
		String hql = "from CraRoute where proGgxh = '"+proGgxh+"' and seqName ='"+seqName+"' and seqStep = "+seqStep+"";
		List<CraRoute> listQuery = this.listQuery(hql);
		if (listQuery != null && listQuery.size() > 0 ) {
			return listQuery.get(0);
		}
		return null;
	}
}
