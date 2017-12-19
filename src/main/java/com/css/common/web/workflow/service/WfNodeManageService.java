package com.css.common.web.workflow.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.css.common.web.workflow.bean.WfNode;
import com.css.common.web.workflow.dao.WfForksetManageDAO;
import com.css.common.web.workflow.dao.WfNodeManageDAO;

@Service("wfNodeManageService")
public class WfNodeManageService extends
		BaseEntityManageImpl<WfNode, WfNodeManageDAO> {
	@Resource(name = "wfNodeManageDAO")
	// @Autowired
	private WfNodeManageDAO dao;
	@Resource(name = "wfForksetManageDAO")
	private WfForksetManageDAO forkDao;

	@Override
	public WfNodeManageDAO getEntityDaoInf() {
		return dao;
	}

	/**
	 * 根据流程代码，查看此流程中是否有FORK节点，如果有的话，是否进行过流向设置
	 * 
	 * @param ps_dm
	 * @return
	 */
	public boolean checkProcessForkNodeIsSetted(String ps_dm) {
		boolean settedFlag = true;
		String ls_sql = "select * from wf_node where node_type='FORK_NODE' and ps_dm='"
				+ ps_dm + "'";
		@SuppressWarnings("unchecked")
		List<WfNode> dataList = dao.createQuery(ls_sql, WfNode.class).list();
		if (null != dataList && dataList.size() > 0) {// 有fork节点
			// System.out.println("have fork");
			String tmp_node_id = "";
			String tmp_sql = "";
			for (WfNode wn : dataList) {
				tmp_node_id = wn.getNodeId() + "";
				tmp_sql = "select count(fk_id) as totalCount from wf_forkset where ps_dm='"
						+ ps_dm + "' and node_id=" + tmp_node_id;

				Object o = forkDao.createSQLQuery(tmp_sql).uniqueResult();
				if (o != null && !(Integer.parseInt(o.toString()) > 0)) {
					settedFlag = false;
				}
				/*
				 * if(!(dao.getRecordCountBySql(tmp_sql)>0)) settedFlag=false;
				 */
				break;
			}
		}

		return settedFlag;
	}

	/**
	 * 根据ps_dm取此流程所有的节点
	 * 
	 * @param ps_dm
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<WfNode> getNodeTreeData(String ps_dm) {
		List<WfNode> lst = dao.createQuery(
				"select * from WfNode where psDm=? order by id", ps_dm).list();
		return lst;
		/*
		 * List dataList=null; try { String ls_sql = null;
		 * ls_sql="select * from wf_node where ps_dm='"
		 * +ps_dm+"' order by nd_id"; dataList=new
		 * BaseDao().executeQuery(ls_sql, WfNode.class); } catch (Exception e) {
		 * e.printStackTrace(); } return dataList;
		 */
	}
	
	/*
	 * 根据NODE_ID查找记录
	 * @param node_id
	 * @return
	 */
	public WfNode findWfNodeByNodeId(String node_id){
		WfNode node = dao.get(Integer.parseInt(node_id));
		return node;
		/*WfNode bean=null;
		String ls_sql="select nd_id,ps_dm,node_id,node_type,node_name,node_x,node_y,node_width,node_height,node_sx from wf_node where node_id="+node_id;
		BaseDao dao=new BaseDao();
		Object obj=dao.findById(ls_sql, WfNode.class);
		if(null!=obj){
			bean=(WfNode)obj;
		}
		return bean;*/
	}
	
	/**
	 * 根据NODE_ID,ps_dm查找记录
	 * @param node_id
	 * @param ps_dm
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public WfNode findWfNodeByNodeIdPsDm(String node_id,String ps_dm){
		List<WfNode> lst = dao.createQuery("select * from WfNode where psDm=? and id=? ", ps_dm,Integer.parseInt(node_id)).list();
		if(lst == null || lst.size() == 0)
			return null;
		else
			return lst.get(0);
		
		/*WfNode bean=null;
		System.out.println(node_id);
		System.out.println(ps_dm);
		String ls_sql="select nd_id,ps_dm,node_id,node_type,node_name,node_x,node_y,node_width,node_height,node_sx,node_url from wf_node where node_id="+node_id +" and ps_dm='"+ps_dm+"'";
		System.out.println(ls_sql);
		BaseDao dao=new BaseDao();
		Object obj=dao.findById(ls_sql, WfNode.class);
		if(null!=obj){
			bean=(WfNode)obj;
		}
		return bean;*/
	}
	
	public Page all_node_by_ps_dm(Page p,String psDm){
		Page pp = dao.pageQuery("select * from WfNode where psDm=?", p.getPageNo(), p.getPagesize(), psDm);
		return pp;
	}
	

	 /**
	  * 根据菜单编号，流程代码，判断此流程是否正在使用，使用时不能删除此流程
	  * @param ps_dm
	  * @return
	  */
	public boolean checkProcessIsUseByMenuIdPsDm(String menu_id,String ps_dm){
		boolean useFlag=false;
//		String ls_sql="select count(log_id) as totalCount from wf_shlog where menu_id="+menu_id+" and ps_dm='"+ps_dm+"'";
		String ls_sql="select count(b.log_id) as totalCount from wf_sqlc a,wf_shlog b where a.ps_dm=b.ps_dm and a.com_dm=b.com_dm and a.sq_sqbh=b.sq_sqbh and a.sqlc_jsbz='Y' and b.menu_id="+menu_id+" and b.ps_dm='"+ps_dm+"'";
		System.out.println(ls_sql);
		/*BaseDao dao=new BaseDao();
		if(dao.getRecordCountBySql(ls_sql)>0){
			useFlag=true;
		}*/
		Object o = dao.createSQLQuery(ls_sql).uniqueResult();
		if(o != null && Integer.parseInt(o.toString()) > 0)
			useFlag=true;
		
		return useFlag;
	}
}
