package com.css.business.web.subsysmanu.mauManage.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauProcessMonitoring;
import com.css.business.web.subsysmanu.mauManage.bean.MauProcessMonitoringVo;
import com.css.commcon.util.YorkUtil;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("mauProcessMonitoringManageDAO")
public class MauProcessMonitoringManageDAO extends BaseEntityDaoImpl<MauProcessMonitoring>  {
	/**
	 * 制程记录
	 * @param p
	 * @param ent
	 * @param sql
	 * @return
	 * @throws Exception
	 * @author JiangShuai
	 */
	public Page queryMauProcessMonitoring(Page p,MauProcessMonitoring ent,final StringBuffer sql) throws Exception{
		Page pp = this.pageSQLQueryVONoneDesc(sql.toString(),p.getPageNo(),p.getPagesize(),new MauProcessMonitoringVo());
		return pp;
	}
	/**
	 * 制程详情
	 * @param p
	 * @param ent
	 * @param sql
	 * @param productid
	 * @return
	 * @throws Exception
	 * @author JiangShuai
	 */
	public Page doSearchDao(Page p,MauProcessMonitoring ent,final StringBuffer sql) throws Exception{
		Page pp = this.pageSQLQueryVONoneDesc(sql.toString(),p.getPageNo(),p.getPagesize(),new MauProcessMonitoringVo());
		return pp;
	}
	
	public List<MauProcessMonitoringVo> queryListDao(Integer productid,String seqname,final String sql){
		List<MauProcessMonitoringVo> list = getHibernateTemplate().execute(new HibernateCallback<List<MauProcessMonitoringVo>>() {
			@Override
			public List<MauProcessMonitoringVo> doInHibernate(Session session) throws HibernateException,
					SQLException {
				@SuppressWarnings("unchecked")
				List<MauProcessMonitoringVo> li = session.createSQLQuery(sql).list();
				li = YorkUtil.parse(li,MauProcessMonitoringVo.class, sql);
				return li;
			}
		});
		return list;
	}
}
