package com.css.business.web.subsyscraft.craManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsyscraft.bean.CraRouteSeq;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("craRouteSeqManageDAO")
public class CraRouteSeqManageDAO extends BaseEntityDaoImpl<CraRouteSeq>  {
	
	/**
	 * @TODO: 取工艺路线的工序列表。排序
	 * @author: zhaichunlei
	 & @DATE : 2017年7月12日
	 * @param routeCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<CraRouteSeq> getRouteSeqList(String routeCode){
		String hql = "from CraRouteSeq where routeCode=? order by sort ";
		return this.createQuery(hql, routeCode).list();
	}

	/**   
	 * @Description: 根据工艺路线 获得最后一道工序code 
	 * @param routeCode 工艺路线
	 * @return         
	 */ 
	public String getLastSeqCode(String routeCode) {
		// TODO Auto-generated method stub
		String hql ="SELECT seqCode FROM CraRouteSeq where  routeCode ='"+routeCode+"' ORDER BY sort DESC LIMIT 1";
		String seqCode = (String) this.createQuery(hql).list().get(0);
		return seqCode;
	}
}
