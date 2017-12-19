package com.css.business.web.subsysplan.plaManage.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysplan.bean.PlaMember;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("plaMemberManageDAO")
public class PlaMemberManageDAO extends BaseEntityDaoImpl<PlaMember>  {

	/** 
	 * @method: 获取某一张表中分页所需记录和所有的记录统计,
	 * @param:true返回所有的记录，false返回符合分页查询的记录
	 * @param:page 当前页号码
	 * @param:pagesize 每页显示多少条数据
	 * @values：需要传入到hql语句中的参数
	 * author:曾斌
	 * time:2017-05-27
	 */
	public List<?> getPagingQueryToolDao(final String hql,final boolean b, final int page,final int pagesize,final Object...values){
		return getHibernateTemplate().execute(new HibernateCallback<List<?>>() {

			@SuppressWarnings("unused")
			@Override
			public List<?> doInHibernate(Session session)
					throws HibernateException, SQLException {
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
	 * author:曾斌
	 * time:2017-05-27
	 */
	public void deleteByIdsDAO(int[] ids, String hql){
		Session session = this.getSession();
		for (int i = 0; i < ids.length; i++) {
			Query q = session.createQuery(hql);
			q.setParameter(0, ids[i]);
			q.executeUpdate();
		}
	}
	
	/** 
	 * @method: 跟新一个表中的某一条记录
	 * author:曾斌
	 * time:2017-05-27
	 */
	public void updatePlaMemberDao(PlaMember plaMember) {
		this.getSession().update(plaMember);
	}
	
	/** 
	 * @method: 新增PlaMember表中的一条记录
	 * author:曾斌
	 * time:2017-05-27
	 */
	public void addPlaMemberDao(PlaMember plaMember) {
		this.getSession().save(plaMember);
	}
}
