package com.css.business.web.subsysmanu.mauManage.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauHandlingChores;
import com.css.business.web.subsysmanu.mauManage.bean.HandChoresQueryParamVO;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("mauHandlingChoresManageDAO")
public class MauHandlingChoresManageDAO extends BaseEntityDaoImpl<MauHandlingChores>  {
  /*
   * @杂物管理条件分页查询
   * @mjq
   * @2017/4/27
   */
   public Page queryChoresDataDao(Page p,MauHandlingChores ent) throws Exception{
	   StringBuffer sql=new StringBuffer("select ");
	   sql.append("m.id id,m.chores_name choresName,m.agent_by agentBy,m.mater_code materCode,m.remark remark ");
	   sql.append("from mau_handling_chores m where 1=1 ");
	   String choresName=ent.getChoresName();
	   String agentBy=ent.getAgentBy();
	   String mateCode=ent.getMaterCode();
	   if(choresName!=null&&choresName.length()>0){
		   sql.append("and m.chores_name='"+choresName+"' ");
	   }
	   if(agentBy!=null&&agentBy.length()>0){
		   sql.append("and m.agent_by like '%"+agentBy+"%'");
	   }
	   if(mateCode!=null&&mateCode.length()>0){ 
		   sql.append("and m.mater_code='"+mateCode+"' ");
	   }  
	   sql.append("order by m.id asc ");
	   Page pp=this.pageSQLQueryVONoneDesc(sql.toString(), p.getPageNo(), p.getPagesize(),new HandChoresQueryParamVO());
	   return pp;
   }
	public void updateBean(MauHandlingChores bean) {
		getHibernateTemplate().saveOrUpdate(bean);
	}
	/*public  MauHandlingChores findModel(Integer id){
		MauHandlingChores mh=getHibernateTemplate().get(MauHandlingChores.class,id);
		return mh;
	}*/
	public void toDelete(Integer id) {
		getHibernateTemplate().delete(getHibernateTemplate().get(MauHandlingChores.class,id));
	}
	public void queryTest(final String hql){
		getHibernateTemplate().execute(new HibernateCallback<MauHandlingChores>(){
		@Override
		public MauHandlingChores doInHibernate(Session session)
				throws HibernateException, SQLException {
			session.createQuery(hql);
		
			return null;
		}
		});
	}
}
