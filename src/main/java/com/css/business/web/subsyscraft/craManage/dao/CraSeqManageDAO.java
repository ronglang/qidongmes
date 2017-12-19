package com.css.business.web.subsyscraft.craManage.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsyscraft.bean.CraSeq;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("craSeqManageDAO")
public class CraSeqManageDAO extends BaseEntityDaoImpl<CraSeq>  {

	/** 
	 * @method: 获取某一张表中分页所需记录和所有的记录统计,
	 * @param:true返回所有的记录，false返回符合分页查询的记录
	 * @param:page 当前页号码
	 * @param:pagesize 每页显示多少条数据
	 * @values：需要传入到hql语句中的参数
	 * author:曾斌
	 * time:2017-06-6
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
	 * author:曾斌
	 * time:2017-06-06
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
	 * @method: 跟新一个表中的某一条记录
	 * author:曾斌
	 * time:2017-06-06
	 */
	public void updateCraSeqDao(CraSeq craSeq) {
		this.getSession().update(craSeq);
	}
	/** 
	 * @method: 新增CraSeq表中的一条记录
	 * author:曾斌
	 * time:2017-06-06
	 */
	public void addCraSeqDao(CraSeq craSeq) {
		this.getSession().save(craSeq);
	}
	
	/**
	 * @TODO:根据工序编码取工序名称
	 * @author: zhaichunlei
	 & @DATE : 2017年7月19日
	 * @param seqCode
	 * @return
	 */
	public String getNameByCode(String seqCode){
		String sql = "select seq_name from cra_seq where seq_code=?";
		Object o = this.createSQLQuery(sql, seqCode).uniqueResult();
		if(o == null) return null;
		
		return o.toString();
	}
	
	/**
	 * @TODO:根据工序代码取实体
	 * @author: zhaichunlei
	 & @DATE : 2017年8月15日
	 * @param seqCode
	 * @return
	 */
	public CraSeq getEntityByCode(String seqCode){
		List<CraSeq> lst = this.findBy("seqCode", seqCode);
		if(lst == null || lst.size() == 0 )
			return null;
		else
			return lst.get(0);
	}
}
