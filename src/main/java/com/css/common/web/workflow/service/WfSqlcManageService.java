package com.css.common.web.workflow.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.common.util.EasyUIBean;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.css.common.web.workflow.bean.WfNode;
import com.css.common.web.workflow.bean.WfSqlc;
import com.css.common.web.workflow.dao.WfNodeManageDAO;
import com.css.common.web.workflow.dao.WfSqlcManageDAO;

@Service("wfSqlcManageService")
public class WfSqlcManageService extends BaseEntityManageImpl<WfSqlc, WfSqlcManageDAO> {
	@Resource(name="wfSqlcManageDAO")
	//@Autowired
	private WfSqlcManageDAO dao;
	
	@Resource(name="wfNodeManageDAO")
	private WfNodeManageDAO wfNodeManageDAO;
	
	
	@Override
	public WfSqlcManageDAO getEntityDaoInf() {
		return dao;
	}
	

	 /**
	  *  根据菜单编号，流程代码，判断此流程是否正在使用，使用时不能删除此流程
	  * @param ps_dm
	  * @return
	  */
	public boolean checkProcessIsUseByMenuIdPsDm(String menu_id,String ps_dm){
		boolean useFlag=false;
//		String ls_sql="select count(log_id) as totalCount from wf_shlog where menu_id="+menu_id+" and ps_dm='"+ps_dm+"'";
		String ls_sql="select count(b.log_id) as totalCount from wf_sqlc a,wf_shlog b where a.ps_dm=b.ps_dm and a.com_dm=b.com_dm and a.sq_sqbh=b.sq_sqbh and a.sqlc_jsbz='Y' and b.menu_id="+menu_id+" and b.ps_dm='"+ps_dm+"'";
		//System.out.println(ls_sql);
		//BaseDao dao=new BaseDao();
		Object o = dao.createSQLQuery(ls_sql).uniqueResult();
		if(o != null && Integer.parseInt(o.toString()) > 0)
			useFlag=true;
		/*if(dao.getRecordCountBySql(ls_sql)>0){
			useFlag=true;
		}*/
		return useFlag;
	}
	
	
	 /**
	  * 根据流程代码，判断此流程是否正在使用，使用时不能作废此流程
	  * @param ps_dm
	  * @return
	  */
	public boolean checkProcessIsUseByMenuIdPsDm(String ps_dm){
		boolean useFlag=false;
		String ls_sql="select count(b.log_id) as totalCount from wf_sqlc a,wf_shlog b where a.ps_dm=b.ps_dm and a.com_dm=b.com_dm and a.sq_sqbh=b.sq_sqbh and a.sqlc_jsbz='N' and b.ps_dm='"+ps_dm+"'";
		Object o = dao.createSQLQuery(ls_sql).uniqueResult();
		if(o != null && Integer.parseInt(o.toString()) > 0)
			useFlag=true;
		
		/*BaseDao dao=new BaseDao();
		if(dao.getRecordCountBySql(ls_sql)>0){
			useFlag=true;
		}*/
		return useFlag;
	}
	
	/**
	 * 根据申请编号 查询当前所处节点，及可回退的所有节点. 流程图产生循环无法用sql方式。 用笨方式，倒推
	 * @param URIEncodeSqbh
	 * @return
	 */
	public List<EasyUIBean> queryWorkFlow_Java(String sqSqbh){
		//BaseDao bd = new BaseDao();
		String sql = "select ps_dm from wf_sqlc where sq_sqbh='"+sqSqbh+"'";
		String psDm = dao.createSQLQuery(sql).uniqueResult().toString();
		//String psDm = bd.getUniqueValue(sql).toString();
		sql = "select  sl_curr_postion from wf_sqlc where sq_sqbh='"+sqSqbh+"'";
		String tt = dao.createSQLQuery(sql).uniqueResult().toString();
		//Long curNodeId = Long.parseLong(bd.getUniqueValue(sql).toString());
		Long curNodeId = Long.parseLong(tt);
		List<EasyUIBean> ret = new ArrayList<EasyUIBean>();
		EasyUIBean bean = null;
		
		
		//WfNode wt = queryWorkFlowFrom(curNodeId,psDm);
		WfNode wt = wfNodeManageDAO.queryWorkFlowFrom(curNodeId,psDm);
		while (wt != null){
			bean = new EasyUIBean();
			bean.setId(wt.getNodeId().toString());
			bean.setText(wt.getNodeName());
			ret.add(bean);
			curNodeId = Long.parseLong(wt.getNodeId().toString());
			wt = wfNodeManageDAO.queryWorkFlowFrom( curNodeId ,psDm);
		}
		
		return ret;
	}
}
