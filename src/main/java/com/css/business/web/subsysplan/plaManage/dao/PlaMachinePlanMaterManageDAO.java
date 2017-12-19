package com.css.business.web.subsysplan.plaManage.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysplan.bean.PlaMachinePlanMater;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;

@Repository("plaMachinePlanMaterManageDAO")
public class PlaMachinePlanMaterManageDAO extends BaseEntityDaoImpl<PlaMachinePlanMater>  {

	/**
	 * @TODO: 查询当天及当天前未开始、生产中的任务对应的材料列表
	 * @author: zhaichunlei
	 & @DATE : 2017年7月12日
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PlaMachinePlanMater> queryMachinePlanMaterDaily(){
		//List<PlaMachinePlan> lst = findBy("workDate", "current_date");
		//return lst;
		StringBuffer sb = new StringBuffer();
		sb.append("from PlaMachinePlanMater p where 1=1 ");
		sb.append("and exists(select 1 from PlaMachinePlan p2 where p2.id=p.machinePlanId ");
		sb.append("and p2.workDate<=current_date and p2.productState in('未开始','生产中')) ");
		sb.append("and p.flag = '否' and p.sendState='否' ");
		//工序详细配料计划
		sb.append("and exists(select 1 from PlaSeqMaterDetail p3 where p3.machinePlanId=p.machinePlanId and extract(minute from p3.sendDate - now()) <= 30 ) ");
		List<PlaMachinePlanMater> lst = createQuery(sb.toString()).list();
		return lst;
	} 
	
	/**
	 * @TODO: 根据机台计划ID，查询材料
	 * @author: zhaichunlei
	 & @DATE : 2017年7月12日
	 * @param planId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PlaMachinePlanMater> queryMachinePlanMaterByPid(Integer planId){
		String sql = "from PlaMachinePlanMater p where p.machinePlanId=? ";
		return this.createQuery(sql, planId).list();
	}
	

	/**
	 * @TODO: 根据工单，查询材料
	 * @author: zhaichunlei
	 & @DATE : 2017年7月12日
	 * @param planId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PlaMachinePlanMater> queryMachinePlanMaterByCourseCode(String wsCode){
		String sql = "from PlaMachinePlanMater p where  p.courseCode=? ";
		return this.createQuery(sql, wsCode).list();
	}
/**
 * 
 *@data:2017年7月21日
@param sql
@return
@autor:wl
 */
	@SuppressWarnings("unchecked")
	public List<PlaMachinePlanMater> getPlaMachinePlanMater(String sql,String workCode) {
		List<PlaMachinePlanMater> list =null;
		try {
			
			 list = this.createSQLQuery(sql, workCode).list();
			// list=this.createQuery(sql, workCode).list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
}
