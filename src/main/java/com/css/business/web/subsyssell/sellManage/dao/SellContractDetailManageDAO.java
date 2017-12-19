package com.css.business.web.subsyssell.sellManage.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsyssell.bean.SellContractDetail;
import com.css.business.web.subsyssell.sellManage.SellContractDetailVO;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("sellContractDetailManageDAO")
public class SellContractDetailManageDAO extends BaseEntityDaoImpl<SellContractDetail>  {

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
	
	public void deleteByIds(int[] ids, String hql){
		Session session = this.getSession();
		for (int i = 0; i < ids.length; i++) {
			Query q = session.createQuery(hql);
			q.setParameter(0, ids[i]);
			q.executeUpdate();
		}
	}
	
	public void updateSellContractDetailManageDao(SellContractDetail sellContractDetail) {
		this.getSession().update(sellContractDetail);
	}
	
	public void addSellContractDetailManageDao(SellContractDetail sellContractDetail) {
		this.getSession().save(sellContractDetail);
	}

	public void updateByIds(int[] ids, String hql) {
		Session session = this.getSession();
		for (int i = 0; i < ids.length; i++) {
			Query q = session.createQuery(hql);
			q.setParameter(0, ids[i]);
			q.executeUpdate();
		}
		
	}
	
	/**
	 * 
	 */
	public void updateBatch(final List<SellContractDetail> list){
		getHibernateTemplate().execute(new HibernateCallback<String>() {
			@Override
			public String doInHibernate(Session session)
					throws HibernateException, SQLException {
				int count = 0;
				for(SellContractDetail bean : list){
					session.save(bean);
					if(++count%50==0){
						session.flush();
					}
				}
				return null;
			}
			
		});
	}
	
	/**
	 * 查询已下发的数量
	 * @param planBatchId
	 * @return
	 * @throws Exception 
	 */
	public Page loadMayBeData(Page p,Integer planBatchId) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select d.id,");
		//sb.append("( select pro_period_length from sell_contract_plan_batch b where b.id=d.plan_batch_id ) pro_period_length_bat,"); //批次段长
		sb.append("( select fun_get_proname_by_procode( b.pro_code ) from sell_contract_plan_batch b where b.id=d.plan_batch_id ) pro_name,");
		sb.append("d.req_unit ,d.pro_ggxh ,d.pro_color ,");
		sb.append("d.pro_period_length,d.pbat_detail_state,d.delive_date, d.total_len,d.complete_len,");  //一个detail明细的段长
		//detail明细对应的已下发到工单的段长
		//sb.append("array_to_string(array((select concat(coalesce(c.product_part_len,'') ) from pla_product_order c where d.id=c.contract_detail_id )),'+') pro_period_length_gd ");
		//pla_product_order的段长由200*30+30*20调整为纯长度。 这里算法要改
		sb.append("array_to_string(array(select product_part_len || '*' ||coalesce(amount,0) from pla_product_order c where d.id=c.contract_detail_id),'+' )  pro_period_length_gd ");
		//sb.append("c.product_order_code course_code "); 
		sb.append(" from sell_contract_detail d ");//left join pla_product_order c on d.id=c.contract_detail_id ");
		sb.append(" where d.plan_batch_id=?");
		return this.pageSQLQueryVONoneDesc(sb.toString(), p.getPageNo(), p.getPagesize(), new SellContractDetailVO(), planBatchId);
	}
	
	/**
	 * @todo: 根据批次表ID，取本批次下，对应的明细表，已下发到工单的总长度
	 * @author : zhaichunlei
	 * @date: 2017年9月14日
	 */
	public Integer getAllGdPartLenByBatch(Integer plan_batch_id){
		StringBuffer sb = new StringBuffer();
		sb.append("select coalesce(sum(complete_len),0) from sell_contract_detail where plan_batch_id=? ");
		Integer num = Integer.parseInt(this.createSQLQuery(sb.toString(), plan_batch_id).uniqueResult().toString());
		return num;
	}
}
