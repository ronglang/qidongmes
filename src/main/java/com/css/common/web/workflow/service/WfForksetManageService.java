package com.css.common.web.workflow.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.css.common.web.workflow.bean.WfForkset;
import com.css.common.web.workflow.dao.WfForksetManageDAO;

@Service("wfForksetManageService")
public class WfForksetManageService extends BaseEntityManageImpl<WfForkset, WfForksetManageDAO> {
	@Resource(name="wfForksetManageDAO")
	//@Autowired
	private WfForksetManageDAO dao;
	
	
	@Override
	public WfForksetManageDAO getEntityDaoInf() {
		return dao;
	}
	

	/**
	 * 获取fork设置的所有数据
	 * @param ps_dm
	 * @param node_id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<WfForkset> getNextNodeByPs_dmNode_id(String ps_dm,Integer node_id) {
		//List dataList=null;
		String ls_sql ="select * from WfForkset where psDm=? and nodeId=? ";
		
		return dao.createQuery(ls_sql, ps_dm,node_id).list();
		/*BaseDao dao=new BaseDao();
		dataList=dao.executeQuery(ls_sql, WfForkset.class);
		return dataList;*/
	}

	
}
