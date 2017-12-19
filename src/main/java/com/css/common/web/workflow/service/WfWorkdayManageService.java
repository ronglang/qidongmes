package com.css.common.web.workflow.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;
import com.css.common.web.workflow.bean.WfWorkday;
import com.css.common.web.workflow.dao.WfWorkdayManageDAO;

@Service("wfWorkdayManageService")
public class WfWorkdayManageService extends BaseEntityManageImpl<WfWorkday, WfWorkdayManageDAO> {
	@Resource(name="wfWorkdayManageDAO")
	//@Autowired
	private WfWorkdayManageDAO dao;
	
	
	@Override
	public WfWorkdayManageDAO getEntityDaoInf() {
		return dao;
	}
	

	/**
	 * 检查某年的工作日设置 数据是否存在
	 * @param year
	 * @return
	 */
	public boolean checkWorkdayDataIsExist(String year){
		//boolean existFlag=false;
		String ls_sql="select count(wd_id) as totalCount from wf_workday where wd_year=? ";
		Object o = dao.createSQLQuery(ls_sql, year).uniqueResult();
		if(Integer.parseInt(o.toString()) > 0)
			return true;
		else
			return false;
		
		/*BaseDao dao=new BaseDao();
		if(dao.getRecordCountBySql(ls_sql)>0){
			existFlag=true;
		}
		return existFlag;*/
	}
	
	/**
	 * 根据年份 删除工作日数据
	 * @param year
	 * @return
	 */
	public boolean deleteWorkdayDataByYear(String year){
		try {
			dao.deleteBySql("delete from wf_workday where wd_year=?",year);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		/*boolean deleteFlag=false;
		String ls_sql="delete wf_workday where wd_year='"+year+"'";
		BaseDao dao=new BaseDao();
		if(dao.executeUpdate(ls_sql)){
			deleteFlag=true;
		}
		return deleteFlag;*/
	}
	
	/**
	 * 根据当前年，获取所有此年的工作日，并以，分隔
	 * @param wd_year
	 * @return
	 */
	public String getWorkdayByYear(String wd_year){
		List<WfWorkday> lst = dao.findBy("wdYear", wd_year);
		
		StringBuffer sb=new StringBuffer("");
		if(null!= lst && lst.size()>0){
			for(WfWorkday wd:lst)
				sb.append(wd.getWdDay()+",");
		}
		String workday=sb.toString();
		if(workday.indexOf(",")>0)
			workday=workday.substring(0,workday.length()-1);
		return workday;
		
		/*StringBuffer sb=new StringBuffer("");
		String ls_sql="select wd_day from wf_workday where wd_year='"+wd_year+"'";
		BaseDao dao=new BaseDao();
		List<WfWorkday> dataList=dao.executeQuery(ls_sql,WfWorkday.class);
		WfWorkday wd_bean=null;
		if(null!=dataList && dataList.size()>0){
			for(WfWorkday wd:dataList)
				sb.append(wd.getWdDay()+",");
		}
		String workday=sb.toString();
		if(workday.indexOf(",")>0)
			workday=workday.substring(0,workday.length()-1);
		return workday;*/
	}
	
}
