package com.css.common.web.workflow.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.css.common.web.workflow.bean.WfNodeTrans;
import com.css.common.web.workflow.dao.WfNodeTransManageDAO;

@Service("wfNodeTransManageService")
public class WfNodeTransManageService extends BaseEntityManageImpl<WfNodeTrans, WfNodeTransManageDAO> {
	@Resource(name="wfNodeTransManageDAO")
	//@Autowired
	private WfNodeTransManageDAO dao;
	
	
	@Override
	public WfNodeTransManageDAO getEntityDaoInf() {
		return dao;
	}
	

	/**
	 * @TODO:生成节点下一流向的下拉列表
	 * @author: zhaichunlei
	 & @DATE : 2017年4月19日
	 * @param ps_dm
	 * @param node_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getNextNodeSelect(String ps_dm,Integer node_id) {
		List<WfNodeTrans> lst = dao.createQuery("select * from WfNodeTrans where psDm=? and tsFrom=? order by id desc ", ps_dm,node_id).list();
		StringBuffer selectType = new StringBuffer();
		if(null != lst && lst.size()>0){
			selectType.append("<select name=\"fork_to_node_id\" class=\"easyui-validatebox\" data-options=\"required:true\">");
			selectType.append("<option selected></option>");
			for(WfNodeTrans wnt:lst){
				selectType.append("<option value=\"" +wnt.getTsTo()+ "\">" + wnt.getTsName()+"</option>");
			}
			selectType.append("</select>");
		}
		return selectType.toString();
		
		/*StringBuffer selectType = new StringBuffer();
		String ls_sql ="select ts_name,ts_to from wf_node_trans where ps_dm='"+ps_dm+"' and ts_from="+node_id+" order by wnt_id desc";
		BaseDao dao=new BaseDao();
		List<WfNodeTrans> dataList=dao.executeQuery(ls_sql,WfNodeTrans.class);
		if(null!=dataList && dataList.size()>0){
			selectType.append("<select name=\"fork_to_node_id\" class=\"easyui-validatebox\" data-options=\"required:true\">");
			selectType.append("<option selected></option>");
			for(WfNodeTrans wnt:dataList){
				selectType.append("<option value=\"" +wnt.getTsTo()+ "\">" + wnt.getTsName()+"</option>");
			}
			selectType.append("</select>");
		}
		return selectType.toString();*/
	}
	
	

	/**
	 * 获取fork设置的所有数据
	 * @param ps_dm
	 * @param node_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<WfNodeTrans> geAllNodeTransByPsdmNodeId(String ps_dm,Integer node_id) {
		List<WfNodeTrans> lst = dao.createQuery("select * from WfNodeTrans where psDm=? and tsFrom=? order by id desc", ps_dm,node_id).list();
		return lst;
		/*
		List dataList=null;
		String ls_sql ="select * from wf_node_trans where ps_dm='"+ps_dm+"' and ts_from="+node_id+" order by wnt_id desc";
		BaseDao dao=new BaseDao();
		dataList=dao.executeQuery(ls_sql, WfNodeTrans.class);
		return dataList;*/
	}
}
