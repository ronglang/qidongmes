package com.css.business.web.subsyssell.sellManage.dao;



import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsyssell.bean.SellContract;
import com.css.business.web.subsyssell.bean.SellContractPlanBatch;
import com.css.business.web.subsyssell.vo.SellContractVO;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("sellContractManageDAO")
public class SellContractManageDAO extends BaseEntityDaoImpl<SellContract>  {
	
	public void delContractService(final String sql){
		
		getHibernateTemplate().execute(new HibernateCallback<String>() {

			@Override
			public String doInHibernate(Session session)
					throws HibernateException, SQLException {
				session.delete(sql);
				return null;
			}
		});
	}

	public List<?> getPagingQueryToolDao(final String hql,final boolean b, final int page,final int pagesize,final Object...values){
		return getHibernateTemplate().execute(new HibernateCallback<List<?>>() {

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
	
	@Deprecated
	public void addSellContractManageDao(SellContract sellContract) {
		this.getSession().save(sellContract);
	}
	public void addSellContractPlanBatchDao(SellContractPlanBatch sellContractPlanBatch) {
		this.getSession().save(sellContractPlanBatch);
	}

	public void updateSellContractManageDao(SellContract sellContract) {
		this.getSession().update(sellContract);
	}

	public void updateSellContractPlanBatchDao(SellContractPlanBatch sellContractPlanBatch) {
		this.getSession().update(sellContractPlanBatch);
	}
	
	public Page loadSellContractManage(Page page, SellContract ent) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select c.id,c.create_by,c.create_date,c.sc_code,c.sc_content,c.remark,c.sc_date,c.sc_money,c.ws_type,");
		sb.append("c.first_party,c.second_party,c.agent_by,c.cus_code,c.delive_date,c.sc_state,c.cus_name,");
		sb.append("c.order_code,c.contract_code,c.contract_type,c.order_detail_id,");
		sb.append("(select coalesce(sum(coalesce(total_len,0)),0) || '' from sell_contract_detail s where s.main_id=c.id) total_len,"); //明细总长度
		sb.append("(select coalesce(sum(coalesce(complete_len,0)),0) || '' from sell_contract_detail s where s.main_id=c.id) complete_len "); //明细中已生成工单的长度
		sb.append("from sell_contract c where 1=1 ");
		String sc_state = ent.getScState();
		String sc_code  = ent.getScCode();
		String order_code = ent.getOrderCode();
		
		if(sc_state != null && sc_state.length() > 0){
			sb.append("and c.sc_state='"+sc_state+"' ");
		}
		if(sc_code != null && sc_code.length() > 0){
			sb.append("and c.sc_code='"+sc_code+"' ");
		}
		if(order_code != null && order_code.length() > 0){
			sb.append("and c.order_code='"+order_code+"' ");
		}
		sb.append(" order by c.create_date desc ");
		return this.pageSQLQueryVONoneDesc(sb.toString(),page.getPageNo(), page.getPagesize(),  new SellContractVO());
	}
}
