package com.css.business.web.sysManage.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.css.common.web.syscommon.bean.Sys_attachment;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
/**
 * 
 *TODO 系 统附件DAO
 * @author huangaho
 *2015-4-17下午2:40:30
 */
@Repository("sysAttachmentManageDAO")
public class SysAttachmentManageDAO extends BaseEntityDaoImpl<Sys_attachment>  {
	
	/**
	 * 根据业务ID删除附件
	 * @param bid (业务ID会相同，需要加上业务类名)
	 */
	public void deleteByBid(Integer bid,String className){
		String hql = "delete from Sys_attachment where bid=?";
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setInteger(0, bid);
		query.executeUpdate();
	}
	/**
	 * 根据业务ID和CLassName删除附件
	 * @param bid
	 */
	public void deleteByBidAndCLassName(Integer bid,String className){
		String hql = "delete from Sys_attachment where bid=? and classname=?";
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setInteger(0, bid);
		query.setString(1,className);
		query.executeUpdate();
	}
}
