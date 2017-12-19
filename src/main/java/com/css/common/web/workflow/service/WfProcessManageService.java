package com.css.common.web.workflow.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.css.common.web.workflow.bean.WfProcess;
import com.css.common.web.workflow.dao.WfProcessManageDAO;

@Service("wfProcessManageService")
public class WfProcessManageService extends BaseEntityManageImpl<WfProcess, WfProcessManageDAO> {
	@Resource(name="wfProcessManageDAO")
	//@Autowired
	private WfProcessManageDAO dao;
	
	
	@Override
	public WfProcessManageDAO getEntityDaoInf() {
		return dao;
	}
	

	/**
	 * 根据流程代码获取流程
	 * @param ps_id
	 * @return
	 */
	public WfProcess findProcessByPsId(String ps_id){
		//String ls_sql="select * from wf_process where ps_id="+ps_id;
		
		WfProcess p = dao.get(Integer.parseInt(ps_id));
		/*BaseDao dao=new BaseDao();
		Object obj=dao.findById(ls_sql, WfProcess.class);
		if(null!=obj){
			bean=(WfProcess)obj;
		}*/
		return p;
	}
	
	/**
	 * 根据流程代码获取流程
	 * @param ps_dm
	 * @return
	 * @throws Exception 
	 */
	public WfProcess findProcessByPsDm(String ps_dm) throws Exception{
		//String ls_sql="select * from wf_process where ps_dm='"+ps_dm+"'";
		List<WfProcess> lst = dao.findBy("psDm", ps_dm);
		if(lst != null && lst.size() > 1)
			throw new Exception("数据错误，业务流程代码不唯一");
		/*BaseDao dao=new BaseDao();
		Object obj=dao.findById(ls_sql, WfProcess.class);
		if(null!=obj){
			bean=(WfProcess)obj;
		}*/
		if(lst == null || lst.size() == 0)
			return null;
		
		return lst.get(0);
	}
	
	/**
	 * 根据流程名称获取流程
	 * @param ps_mc 流程名称，在添加时要保持名字不能重复
	 * @return
	 */
	public WfProcess findProcessByPsMc(String ps_mc){
		//WfProcess bean=null;
		//String ls_sql="select * from wf_process where ps_mc='"+ps_mc+"'";
		
		List<WfProcess> lst = dao.findBy("psMc", ps_mc) ;
		if(lst != null && lst.size() > 0)
			return lst.get(0);
		else
			return null;
		/*BaseDao dao=new BaseDao();
		Object obj=dao.findById(ls_sql, WfProcess.class);
		if(null!=obj){
			bean=(WfProcess)obj;
		}
		return bean;*/
	}
	
	/**
	 * 查看要保存的工作流名称是否存在
	 * @param ps_mc
	 * @return
	 */
	public boolean checkProcessNameIsExist(String ps_mc){
		//boolean existFlag=false;
		//String ls_sql="select count(ps_id) as totalCount from wf_process where ps_mc='"+ps_mc+"'";
		List<WfProcess> lst = dao.findBy("psMc", ps_mc) ;
		if(lst != null && lst.size() > 0)
			return true;
		else
			return false;
		/*BaseDao dao=new BaseDao();
		if(dao.getRecordCountBySql(ls_sql)>0){
			existFlag=true;
		}
		return existFlag;*/
	}
	
	

	public Page admin(Page p,WfProcess ent) throws Exception{
		return dao.pageQuery(p,ent);
	}
	
	/**
	 * 获取所有已经定义的工作流，方便前台对其节点进行授权
	 * @return
	 */
	 public List getAllProcessTreeData(){
    	List dataList=null;
		String ls_sql="select * from WfProcess where psSfzf='N' ";
		dataList= dao.createQuery(ls_sql).list();
		return dataList;
	 }	
}
