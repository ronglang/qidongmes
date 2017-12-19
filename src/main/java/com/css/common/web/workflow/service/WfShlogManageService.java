package com.css.common.web.workflow.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.css.common.web.workflow.bean.WfShlog;
import com.css.common.web.workflow.dao.WfShlogManageDAO;

@Service("wfShlogManageService")
public class WfShlogManageService extends BaseEntityManageImpl<WfShlog, WfShlogManageDAO> {
	@Resource(name="wfShlogManageDAO")
	//@Autowired
	private WfShlogManageDAO dao;
	
	
	@Override
	public WfShlogManageDAO getEntityDaoInf() {
		return dao;
	}
	

	 /**
	  * 根据流程代码，判断此流程是否正在使用，使用时不能删除此流程
	  * @param ps_dm
	  * @return
	  */
	public boolean checkProcessIsUseByPsDm(String ps_dm){
		boolean useFlag=false;
		String ls_sql="select count(log_id) as totalCount from wf_shlog where ps_dm='"+ps_dm+"'";
		Object o = dao.createSQLQuery(ls_sql).uniqueResult();
		if(o != null && Integer.parseInt(o.toString()) > 0)
			useFlag=true;
		return useFlag;
	}
	
}
