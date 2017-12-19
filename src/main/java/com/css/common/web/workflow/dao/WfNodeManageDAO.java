package com.css.common.web.workflow.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.workflow.bean.WfNode;

@Repository("wfNodeManageDAO")
public class WfNodeManageDAO extends BaseEntityDaoImpl<WfNode>  {
	/**
	 * @TODO: 递归查询 上一级节点
	 * @author: zhaichunlei
	 & @DATE : 2015-9-15
	 * @param ts_to 是当前节点 
	 * @param psDm
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public WfNode queryWorkFlowFrom(Long ts_to,String psDm){
		//BaseDao bd = new BaseDao();
		StringBuffer sb = new StringBuffer();
		sb.append("select nodeId, nodeName  from WfNode a where 1=1 ");
		sb.append("and exists(select 1  from WfNodeTrans where psDm = '" + psDm + "' ");
		sb.append("and tsFrom=nodeId and tsTo=" + ts_to +")  and psDm = '" + psDm + "' ");
		//List<WfNode> lst = bd.executeQuery(sb.toString(), WfNode.class);
		List<WfNode> lst = this.createQuery(sb.toString()).list();
		if(lst != null && lst.size() > 0){
			WfNode wt = lst.get(0);
			return wt;
		}
		return null;
	}
}
