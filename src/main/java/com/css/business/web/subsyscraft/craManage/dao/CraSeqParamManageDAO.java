package com.css.business.web.subsyscraft.craManage.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsyscraft.bean.CraSeqParam;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("craSeqParamManageDAO")
public class CraSeqParamManageDAO extends BaseEntityDaoImpl<CraSeqParam>  {

	public void findByQueryPage(final Page page,final String hql,final Map<String, Object> paramMap) {
		
		super.getHibernateTemplate().execute(new HibernateCallback<Page>() {
			@SuppressWarnings("unchecked")
			@Override
			public Page doInHibernate(Session session)
					throws HibernateException, SQLException {
				int pageNO = page.getPageNo();
				int pagesize = page.getPagesize();
				Query q = session.createQuery(hql);
				if(paramMap!=null&&!paramMap.isEmpty()){//有参数
					for(String key : paramMap.keySet()){
						q.setParameter(key, paramMap.get(key));
					}
				}
				int totalCount = q.list()==null?0:q.list().size();
				page.setTotalCount(totalCount);
				q.setFirstResult((pageNO-1)*pagesize);
				q.setMaxResults(pageNO*pagesize);
				page.setData(q.list());
				return page;
			}
		});
	}
	
	/**
	 * @TODO: 根据产品工艺编码、工序编码， 取本序的工艺参数
	 * @author: zhaichunlei
	 & @DATE : 2017年7月12日
	 * @param proCraCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CraSeqParam> getSeqParamListByProCraCode(String proCraCode,String seqCode){
		/**
		 * select * from cra_seq_param p where 1=1 
			and exists(select 1 from cra_seq_relation r where r.craft_seq_code=p.craft_seq_code 
					and r.craft_product_code='pro_0001')
			and p.seq_code='SEQ_001';
		 */
		StringBuffer sb = new StringBuffer();
		sb.append("select p from CraSeqParam p where 1=1 ");
		sb.append("and exists(select 1 from CraProSeqRelation r where r.pcscRelaCode=p.pcscRelaCode and r.cCode=p.craftCode and r.proCraftCode=? ) ");
		sb.append("and p.seqCode=? ");
		
		return this.createQuery(sb.toString(), proCraCode,seqCode).list();
		
	}

}
