package com.css.business.web.subsysmanu.mauManage.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauProcessSampling;
import com.css.business.web.subsysmanu.mauManage.bean.MauProcessMonitoringVo;
import com.css.commcon.util.YorkUtil;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("mauProcessSamplingManageDAO")
public class MauProcessSamplingManageDAO extends BaseEntityDaoImpl<MauProcessSampling>  {
	
	public Page queryBackDateDao(Page p,final StringBuffer sql) throws Exception{
		Page pp = this.pageSQLQueryVONoneDesc(sql.toString(), p.getPageNo(), p.getPagesize(), new MauProcessMonitoringVo());
		return pp;
	}
	
	public List<?> queryAxisDate(String endaxisnumber){
		final String hql = "SELECT m.sourceAxisNumber FROM MauProcessMonitoring m WHERE m.ownAxisNumber = '"+endaxisnumber+"' ";
		List<?> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	public Page queryBackAxisDateDao(Page p,final StringBuffer sql) throws Exception{
		Page pp = this.pageSQLQueryVONoneDesc(sql.toString(), p.getPageNo(), p.getPagesize(), new MauProcessMonitoringVo());
		return pp;
	}
	
	@SuppressWarnings("rawtypes")
	public List querySQLPageList(String sql,int startIndex,long totalCount,int pageNo, int pageSize,MauProcessMonitoringVo vo,Object... values) {
		Query query = createSQLQuery(sql,values);
		List li = query.setFirstResult(startIndex).setMaxResults(pageSize).setResultTransformer(Transformers.aliasToBean(vo.getClass())).list();
		return li;
	}
	
	public List<MauProcessMonitoringVo> queryBackDateCraParamDao(final String sql){
		List<MauProcessMonitoringVo> list = getHibernateTemplate().execute(new HibernateCallback<List<MauProcessMonitoringVo>>() {

			@Override
			public List<MauProcessMonitoringVo> doInHibernate(Session session) throws HibernateException,
					SQLException {
				@SuppressWarnings("unchecked")
				List<MauProcessMonitoringVo> li = session.createSQLQuery(sql).list();
				li = YorkUtil.parse(li, MauProcessMonitoringVo.class, sql);
				return li;
			}
		});
		return list;
	}
	
	
}
