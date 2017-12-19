package com.css.business.web.subsyscraft.craManage.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsyscraft.bean.CraCraftProduct;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("craCraftProductManageDAO")
public class CraCraftProductManageDAO extends BaseEntityDaoImpl<CraCraftProduct>  {
	
	/**
	 * @TODO: 根据工艺路线编码取实体
	 * @author: zhaichunlei
	 & @DATE : 2017年7月12日
	 * @param proCraCode
	 * @return
	 */
	public CraCraftProduct getObjByProCraCode(String proCraCode){
		List<CraCraftProduct> lst = this.findBy("proCraftCode", proCraCode);
		if(lst != null && lst.size() > 0 ) return lst.get(0);
		
		return null;
	}
	

	/**
	 * @TODO:取上一工序的工序代码
	 * @author: zhaichunlei
	 & @DATE : 2017年8月4日
	 * @param seqCode
	 * @param proCraCode
	 * @return
	 */
	public String getLastSeq(String seqCode,String proCraCode){
		StringBuffer sb = new StringBuffer();
		sb.append("select seq_code from cra_pro_seq_relation r where ");
		sb.append(" exists(select 1 from cra_pro_seq_relation r2 where r2.pro_craft_code=r.pro_craft_code and r2.seq_sort=r.seq_sort+1 and ");
		sb.append("	 r2.seq_code=? ) ");
		sb.append(" and r.pro_craft_code=? ");
		Object o = this.createSQLQuery(sb.toString(),seqCode,proCraCode).uniqueResult();
		return o == null ? null : o.toString();
	}
	
	/**
	 * @TODO: 根据规格型号取产品工艺
	 * @author: zhaichunlei
	 & @DATE : 2017年8月21日
	 * @param proGgxh
	 * @param color
	 * @return
	 */
	public List<CraCraftProduct> getCraCraftProduct(String proGgxh,String color){
		String hql = " from CraCraftProduct where proGgxh = ? AND proColor = ? ";
		@SuppressWarnings("unchecked")
		List<CraCraftProduct> list = this.createQuery(hql, proGgxh,color).list();
		return list;
	}
}
