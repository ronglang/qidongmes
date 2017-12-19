package com.css.business.web.subsysmanu.mauManage.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.css.business.web.subsysmanu.bean.MauTaskLoad;
import com.css.business.web.subsysmanu.mauManage.bean.MauMachineVO;
import com.css.business.web.sysManage.dao.SysConfigManageDAO;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("mauTaskLoadDAO")
public class MauTaskLoadDAO extends BaseEntityDaoImpl<MauTaskLoad>{
	@Resource(name="sysConfigManageDAO")
	private SysConfigManageDAO sysConfigManageDAO;
	
	/**
	 * @TODO: 找到当天的空闲机台
	 * @author: zhaichunlei
	 & @DATE : 2017年8月4日
	 * @param seqCode
	 * @param thisSeqTime_begin
	 * @return
	 * @throws Exception 
	 */
	public List<MauMachineVO>  fetchSeqIdleMachine(String seqCode,Integer workDay) throws Exception{
	    /*Timestamp d = new Timestamp(System.currentTimeMillis());
	    if(d.getTime() > thisSeqTime_begin.getTime()){
	    	thisSeqTime_begin = d;
	    }*/
		
		//怎么定义一个机台是空闲的？ 假如工时是24小时，目前已有20工时，？
		//--》》》》》》》》》》 当24-合计工时> 系统参数设置的值时，就视为空闲  
		String gs = sysConfigManageDAO.getValueByItem("一天工时数");
		if(gs == null || gs.trim().length() == 0)
			throw new Exception("请在SYSCONFIG配置【一天工时数】参数");
		
		String kxgs = sysConfigManageDAO.getValueByItem("排产机台允许空闲工时");
		if(kxgs == null || kxgs.trim().length() == 0)
			throw new Exception("请在SYSCONFIG配置【排产机台允许空闲工时】参数");
		
		//Integer yxgs = Integer.parseInt(gs) - Integer.parseInt(kxgs); //有效工时
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date dday = sdf.parse(workDay.toString());
		sdf.applyPattern("yyyy-MM-dd");
		String dstr = sdf.format(dday);
		String dstart = dstr + " 08:00:00";
		
		sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
		//查询当天的开始与结束时间
		Date date_start = sdf.parse(dstart);
		Date date_end   = new Date(date_start.getTime()+24 * 60 * 60 * 1000 );*/
		
		/**
		 * select * from mau_machine m  where 1=1 
 and not exists (
		select 1 from mau_task_load p where 1=1 and p.obj_id=m.id 
		and p.status='否'
		  and p.work_day= 2 )  
and seq_code='SEQ_002'
		 */
		//当天，所有工单合计，已安排工时少于指定工时的机台
		StringBuffer sb = new StringBuffer();
		/*sb.append("select * from mau_machine m  where 1=1 and m.id in (");
		sb.append("select p.obj_id from mau_task_load p where 1=1 ");
		sb.append("and p.mac_code=m.mac_code ");
		sb.append("  and p.work_day= ? ");
		sb.append(" group by p.mac_code ");
		sb.append("having  sum(seq_hours) > "+yxgs+" "); // --工时合计不到指定工时
		sb.append(" and m.seq_code=? ");*/
		//新思路
		
		sb.append(" select * from mau_machine m  where 1=1  ");
		sb.append("and not exists (select 1 from mau_task_load p where 1=1 and p.obj_id=m.id and p.status='否' and p.work_day= ? ) ");
		sb.append("and seq_code=? ");
		@SuppressWarnings("unchecked")
		List<MauMachineVO> lst = this.createSQLQuery(sb.toString(), workDay,seqCode).setResultTransformer(Transformers.aliasToBean(MauMachineVO.class)).list();
		return lst;
	}
	
	/**
	 * @TODO: 根据机台ID与工作日，取机台任务负荷 
	 * @author: zhaichunlei
	 & @DATE : 2017年8月8日
	 * @param macId
	 * @param workDay
	 * @return
	 */
	public MauTaskLoad getTaskByMachineAndWorkDay(Integer macId,Integer workDay){
		String hql = "from MauTaskLoad where objId=? and workDay=? ";
		List<MauTaskLoad> lst = this.createQuery(hql, macId,workDay).list();
		if(lst == null || lst.size() == 0)
			return null;
		else
			return lst.get(0);
	}
}
