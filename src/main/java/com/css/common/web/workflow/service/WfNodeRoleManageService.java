package com.css.common.web.workflow.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.css.common.web.workflow.bean.WfNodeRole;
import com.css.common.web.workflow.bean.WfNodeRoleVO;
import com.css.common.web.workflow.dao.WfNodeRoleManageDAO;

@Service("wfNodeRoleManageService")
public class WfNodeRoleManageService extends BaseEntityManageImpl<WfNodeRole, WfNodeRoleManageDAO> {
	@Resource(name="wfNodeRoleManageDAO")
	//@Autowired
	private WfNodeRoleManageDAO dao;
	
	
	@Override
	public WfNodeRoleManageDAO getEntityDaoInf() {
		return dao;
	}
	

	/*
	/**
	 * 获取当前节点已经绑定的角色的数量
	 * @param ps_dm
	 * @param node_id
	 * @return
	 */
	public int getNodeBindRoleShuLiang(String ps_dm,String node_id){
		
		Object o = dao.createSQLQuery("select count(*) count from wf_node_role where ps_dm=? and node_id=? ", ps_dm,Integer.parseInt(node_id)).uniqueResult();
		return Integer.parseInt(o.toString());
		/*int bind_role=0;
		if(!"".equals(node_id) && !"".equals(ps_dm)){
			BaseDao dao=new BaseDao();
			String ls_sql="select count(nr_id) as totalCount from wf_node_role where ps_dm='"+ps_dm+"' and node_id="+node_id;
			bind_role=dao.getRecordCountBySql(ls_sql);
		}
		return bind_role;*/
	}
	
	public Page getRoleDataByNodeIdPsDm(Page p,String node_id,String ps_dm) throws Exception{
		//WfNodeRoleVO
		WfNodeRoleVO vo = new WfNodeRoleVO();
		//String ls_sql="select distinct a.nr_id,a.ps_dm,a.role_id,a.node_id,b.role_name,c.dep_name,d.node_sx,d.node_url from wf_node_role a,bt_role b,bt_depart c,wf_node d where a.role_id=b.role_id and b.dep_id=c.dep_id and a.ps_dm=d.ps_dm and a.node_id=d.node_id and a.node_id="+node_id+" and a.ps_dm='"+ps_dm+"' order by nr_id desc";
		String ls_sql="select distinct a.nr_id,a.ps_dm,a.role_id,a.node_id,b.role_name,'' as dep_name,d.node_sx,d.node_url from wf_node_role a,bt_role b,wf_node d where a.role_id=b.role_id and a.ps_dm=d.ps_dm and a.node_id=d.node_id and a.node_id="+node_id+" and a.ps_dm='"+ps_dm+"' order by nr_id desc";
		Page pp = dao.pageSQLQueryVONoneDesc(ls_sql, p.getPageNo(),p.getPagesize(), vo);
		return pp;
	}
	
}
