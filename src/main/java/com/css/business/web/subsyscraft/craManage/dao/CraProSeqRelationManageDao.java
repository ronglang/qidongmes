package com.css.business.web.subsyscraft.craManage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsyscraft.bean.CraProSeqRelation;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("craProSeqRelationManageDao")
public class CraProSeqRelationManageDao extends BaseEntityDaoImpl<CraProSeqRelation>{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String getCode(){
		String sql = " select nextval(?)  ";//select nextval('seq_cra_seq_relation_code');
		
		Map<String,Object> map = jdbcTemplate.queryForMap(sql,"seq_cra_seq_relation_code");
		String code = map.get("nextval").toString();
		return code ;
	}
	
	/**
	 * @TODO: 根据产品工艺取它的各工序关联
	 * @author: zhaichunlei
	 & @DATE : 2017年8月2日
	 * @param proCraftCode
	 * @return
	 */
	public List<CraProSeqRelation> getRouteSeqByRoute_code(String proCraftCode){
		String hql = "select r from CraProSeqRelation r,CraRouteSeq s where r.routeCode=s.routeCode and r.seqCode=s.seqCode and r.proCraftCode=? order by s.sort ";
		@SuppressWarnings("unchecked")
		List<CraProSeqRelation> lst = this.createQuery(hql, proCraftCode).list();
		return lst;
	}
	
	/**
	 * @TODO: 根据产品工序编码、工序，取工艺关系。
	 * @author: zhaichunlei
	 & @DATE : 2017年8月15日
	 * @param proCraftCode
	 * @param seqCode
	 * @return
	 */
	public CraProSeqRelation getRelaByCCP_code_and_seqCode(String proCraftCode, String seqCode){
		String hql = "from CraProSeqRelation where proCraftCode=? and seqCode=?";
		List<CraProSeqRelation> lst = this.createQuery(hql,proCraftCode, seqCode).list();
		if(lst == null || lst.isEmpty()) 
			return null;
		else
			return lst.get(0);
	}
}
