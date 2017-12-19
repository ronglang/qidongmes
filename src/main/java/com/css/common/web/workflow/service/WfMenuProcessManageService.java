package com.css.common.web.workflow.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.css.common.web.workflow.bean.WfMenuProcess;
import com.css.common.web.workflow.dao.WfMenuProcessManageDAO;

@Service("wfMenuProcessManageService")
public class WfMenuProcessManageService extends BaseEntityManageImpl<WfMenuProcess, WfMenuProcessManageDAO> {
	@Resource(name="wfMenuProcessManageDAO")
	//@Autowired
	private WfMenuProcessManageDAO dao;
	
	
	@Override
	public WfMenuProcessManageDAO getEntityDaoInf() {
		return dao;
	}
	

	 /**
	  * 根据流程代码，判断此流程是否已经和菜单绑定，绑定后不能删除此流程
	  * @param ps_dm
	  * @return
	  */
	public boolean checkProcessIsBindMenuByPsDm(String ps_dm){
		boolean bindFlag=false;
		String ls_sql="select count(ml_id) as totalCount from wf_menu_process where ps_dm='"+ps_dm+"'";
		Object o = dao.createSQLQuery(ls_sql).uniqueResult();
		if(o != null && Integer.parseInt(o.toString()) > 0)
			bindFlag=true;
		return bindFlag;
	}
	
}
