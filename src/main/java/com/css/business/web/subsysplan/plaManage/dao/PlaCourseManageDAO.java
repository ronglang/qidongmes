package com.css.business.web.subsysplan.plaManage.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.css.business.web.subsysplan.bean.PlaCourse;
import com.css.business.web.subsysplan.bean.PlaCourseAxis;
import com.css.business.web.subsysplan.bean.PlaMachinePlanSchedule;
import com.css.business.web.subsysplan.vo.NeedPartPlaCourseVo;
import com.css.business.web.subsysplan.vo.PlaCourseVO;
import com.css.common.web.syscommon.dao.impl.BaseEntityDaoImpl;
import com.css.common.web.syscommon.dao.support.Page;

@Repository("plaCourseManageDAO")
public class PlaCourseManageDAO extends BaseEntityDaoImpl<PlaCourse>  {
	/**
	 * @TODO: 查询当天及当天之前未完工单
	 * @author: zhaichunlei
	 & @DATE : 2017年7月12日
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PlaCourse> getCourseDaily(){
		String hql = "from PlaCourse p where p.billDate<=current_date and p.isFinish='否' and p.useFlag='是' and p.pastFlag='否' ";
		List<PlaCourse> lst = this.createQuery(hql).list();
		return lst;
	}
	
	
	/**
	 * 获取还没有生产计划的正常开单的工单列表
	 * @return
	 */
	public List<PlaCourse> getNoPlanNormalPlaCourseList(){
		String hql = "from PlaCourse c where c.wsType = '正常开单' and c.planFlag ='否' order by c.demandDate";
		@SuppressWarnings("unchecked")
		List<PlaCourse> lst = this.createQuery(hql).list();
		return lst;
	}
	
	/**
	 * 获取已生成计划但还没有排产的正常开单的工单列表
	 * @return
	 */
	public List<PlaCourse> getNormalPlanPlaCourseList(){
		String hql = "from PlaCourse c where c.wsType = '正常开单' and c.planFlag ='是' and c.useFlag ='否' order by c.demandDate";
		@SuppressWarnings("unchecked")
		List<PlaCourse> lst = this.createQuery(hql).list();
		return lst;
	}
	
	/**
	 * 获取还没有生产完的正常开单的工单列表
	 * @return
	 */
	public List<PlaCourse> getNormalPlaCourseUnfinishedList(){
		String hql = "from PlaCourse c where c.wsType = '正常开单'  and  c.wsSchedule<100 order by c.demandDate";
		@SuppressWarnings("unchecked")
		List<PlaCourse> lst = this.createQuery(hql).list();
		return lst;
	}
	
	/**
	 * 获取已经生产计划但还没有排产的正常开单的工单列表（需要删除对应的计划列表）
	 * @return
	 */
	public List<PlaCourse> getPlaCourseDeleteList(){
		String hql = "from PlaCourse c where c.wsType = '正常开单' and c.useFlag ='否'  and c.planFlag='是'";
		@SuppressWarnings("unchecked")
		List<PlaCourse> lst = this.createQuery(hql).list();
		return lst;
	}
	
	/**
	 * 获取插单的工单未排产的列表
	 * @return
	 */
	public List<PlaCourse> getPlaCourseInsertList(){
		String hql = "from PlaCourse c where c.wsType='插单'  and c.useFlag ='否' order by c.demandDate  ";
		@SuppressWarnings("unchecked")
		List<PlaCourse> lst = this.createQuery(hql).list();
		return lst;
	}
	
	/**
	 * 获取插单的工单未完成的列表
	 * @return
	 */
	public List<PlaCourse> getPlaCourseInsertUnfinishedList(){
		String hql = "from PlaCourse c where c.wsType='插单'  and c.wsSchedule<100 order by c.demandDate  ";
		@SuppressWarnings("unchecked")
		List<PlaCourse> lst = this.createQuery(hql).list();
		return lst;
	}
	
	/**
	 * 获取插单的工单已经生产计划但还没有排产的列表
	 * @return
	 */
	public List<PlaCourse> getInsertNoPlanPlaCourseList(){
		String hql = "from PlaCourse c where c.wsType='插单' and  c.planFlag ='否' and c.useFlag ='否' order by c.demandDate ";
		@SuppressWarnings("unchecked")
		List<PlaCourse> lst = this.createQuery(hql).list();
		return lst;
	}
	
	/**
	 * @TODO: 根据工单查询未完机台计划的数目
	 * @author: zhaichunlei
	 & @DATE : 2017年7月20日
	 * @param courseCode
	 * @return
	 */
	public int getMachinePlanByCoureCode_not_complete(String courseCode){
		String sql = "select count(*) from pla_machine_plan where course_code=? and product_state in('未开始','生产中')";
		int num = Integer.parseInt(this.createSQLQuery(sql,courseCode).uniqueResult().toString());
		return num;
	}
	
	/**
	 * @TODO: 根据工单编码取得第一个工单
	 * @author: zhaichunlei
	 & @DATE : 2017年7月20日
	 * @param courseCode
	 * @return
	 */
	public PlaCourse getByCourseCode(String courseCode){
		List<PlaCourse> lst = this.findBy("wsCode", courseCode);
		if(lst == null || lst.size() == 0)
			return null;
		
		return lst.get(0);
	}
	/**
	 * 根据机台编码获取当前机台正在执行的机台计划
	 *@data:2017年8月10日
	@param sql
	@param macCode
	@return
	@autor:wl
	 */
	public List<PlaMachinePlanSchedule> getMauMachineObjectByMacCode(
			String sql, String macCode) {
		// TODO Auto-generated method stub
		List<PlaMachinePlanSchedule> list=null;
		try {
			//list=this.createSQLQuery(sql, macCode).list();
			list=this.createQuery(sql, macCode).list();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * @todo: 单管理，查询工单相信信息
	 * @author : zhaichunlei
	 * @throws Exception 
	 * @date: 2017年9月21日
	 */
	public Page getCourseInfoList( Page p, PlaCourse ent) throws Exception {
		//Date bdate = ent.getBillDate();
		//decomposeFlag','planFlag','wsType','priorityWay
		String decomposeFlag = ent.getDecomposeFlag();
		String planFlag      = ent.getPlanFlag();
		String wsType        = ent.getWsType();
		String priorityWay   = ent.getPriorityWay();
		List<Object> pl = new ArrayList<Object>();
		
		//PlaCourseVO
		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		sb.append("c.id,c.create_by,c.create_date,c.ws_type,c.ws_code,c.sc_code,c.bat_code,");
		sb.append("c.pro_ggxh,c.pro_color,c.head_zzdc,c.head_zzds,c.total_amount,");
		sb.append("c.manu_notice_id,c.bill_date,c.is_finish,c.use_flag,c.past_flag,");
		sb.append("c.plan_enable_date,c.route_code,c.pro_craft_code,c.product_order_code,");
		sb.append("c.contract_detail_id,c.demand_date,c.p_code,c.plan_flag,c.priority_way,c.decompose_flag,c.priority_way,");
		sb.append("o.amount,o.complete_amount,o.demand_part_len,o.product_order_state,");
		sb.append("o.product_part_len,o.priority,o.is_flag,o.pro_color,o.pro_code,");
		sb.append("fun_get_proname_by_procode(o.pro_code) pro_name ");
		
		sb.append("from pla_course c,pla_product_order o where c.ws_code=o.product_order_code ");
		//sb.append("and c.plan_flag='否' ");//and c.decompose_flag='否'
		if(decomposeFlag != null && decomposeFlag.length() > 0){
			sb.append(" and c.decompose_flag=? ");
			pl.add(decomposeFlag);
		}
		if(planFlag != null && planFlag.length() > 0){
			sb.append(" and c.plan_flag=? ");
			pl.add(planFlag);
		}
		if(wsType != null && wsType.length() > 0){
			sb.append(" and c.ws_type=? ");
			pl.add(wsType);
		}
		if(priorityWay != null && priorityWay.length() > 0){
			sb.append(" and c.priority_way=? ");
			pl.add(priorityWay);
		}
		
		sb.append(" order by create_date desc");
		
		
		Page page = this.pageSQLQueryVONoneDesc(sb.toString(),p.getPageNo(), p.getPagesize(), new PlaCourseVO(),pl.toArray());
		return page;
	}

	/**
	 * 
	 * @Description:获取最大的
	 * @param workCode
	 * @return
	 */
	public Integer getMaxStepByWorkCode(String workCode) {
		String hql  = "select max(step) from PlaMacTask where workcode='"+workCode+"' ";
		List<Integer> list = this.createQuery(hql).list();
		if (list != null && !list.isEmpty()) {
			return (int)list.get(0);
		}
		return null;
	}

	public String getProGGXH(String workCode) {
		String hql = "select proGgxh from PlaCourse where wsCode = '"+workCode+"'";
		return (String) this.createQuery(hql).uniqueResult();
	}

	/**
	 * 
	 * @Description:该订单已排产的工单
	 * @param orderCode
	 * @param useFlag
	 * @return
	 */
	public List<PlaCourse> findByOrderCodeAndUse(String orderCode, String useFlag) {
		String hql = "from PlaCourse where orderCode='"+orderCode+"' and useFlag = '"+useFlag+"'";
		List<PlaCourse> listQuery = this.listQuery(hql);
		return listQuery;
	}

	/**
	 * 
	 * @Description:删除 工单和工单详情,
	 * @param orderCode
	 * @param useFlag
	 */
	public void clearBeanByOrderCodeAndUse(String orderCode, String useFlag) {
		List<PlaCourse> list = findByOrderCodeAndUse(orderCode,useFlag);
		if (list == null || list.size() == 0) {
			return;
		}
		
		//删除任务 相关信息
		String taskSql = "delete from pla_mac_task  where workcode in (select ws_code from Pla_Course where order_Code='"+orderCode+"' and use_Flag = '"+useFlag+"')";
		String taskSqlColor = "delete from pla_mac_task_color  where workcode in (select ws_code from Pla_Course where order_Code='"+orderCode+"' and use_Flag = '"+useFlag+"')";
		String taskSqlMaterial  = "delete from pla_mac_task_materil where workcode in (select ws_code from Pla_Course where order_Code='"+orderCode+"' and use_Flag = '"+useFlag+"')";
		this.deleteBySql(taskSql);
		this.deleteBySql(taskSqlColor);
		this.deleteBySql(taskSqlMaterial);
		//删除工单
		String orderSql = "delete from Pla_Course where order_Code='"+orderCode+"' and use_Flag = '"+useFlag+"'";
		this.deleteBySql(orderSql);
		//删除工单详情
		String odetaiSql = "delete from pla_course_axis where course_code in ( select ws_code from Pla_Course where order_Code='"+orderCode+"' and use_Flag = '"+useFlag+"')";
		this.deleteBySql(odetaiSql);
	}
	

	/**
	 * @Description: 根据通知单 编号查询工单
	 * @param contractCode
	 * @return
	 */
	public List<PlaCourse> getPlaCourseByContractId(String contractId) {
		// TODO Auto-generated method stub
		String hql = "  from PlaCourse where manuNoticeId='" + contractId + "'";
		@SuppressWarnings("unchecked")
		List<PlaCourse> list = this.createQuery(hql).list();
		return list;
	}
	
	public PlaCourse getPlaCourseByWsCode(String wsCode){
		String hql = " from PlaCourse where wsCode = ?  ";
		@SuppressWarnings("unchecked")
		List<PlaCourse> list = this.createQuery(hql, wsCode).list();
		if(list.size()<1 || list ==null){
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * 获取没有排产的可合并的工单列表
	 * @return
	 * @throws Exception 
	 */
	public Page getUnProductPlaCourseList(Page p) throws Exception{
		
		String sql = "select pro_ggxh from Pla_Course c where  c.use_Flag ='否' group by pro_ggxh HAVING count(*) >1";
		List<String> ggxhs = new ArrayList<String>();
		List<String> lst = this.createSQLQuery(sql).list();
		for(int i = 0; i<lst.size();i++){
			if(lst.get(i)!=null){
				sql = "select area from mau_product where pro_ggxh='"+lst.get(i)+"'";
				List<String> areas = this.createSQLQuery(sql).list();
				if(areas.size()>0&&areas.get(0)!=null){
					String[] area = areas.get(0).split(";");
					if(Integer.valueOf(area[0])<=500){
						ggxhs.add(lst.get(i));
					}
				}
			}
		}
		
	   String wheresql="";
	   for(int i = 0; i<ggxhs.size(); i++){
		   wheresql=wheresql+"'"+ggxhs.get(i)+"',";
	   }
	   List<NeedPartPlaCourseVo> npcv= new ArrayList<NeedPartPlaCourseVo>();
	   wheresql = wheresql.substring(0, wheresql.length()-1);
	   
	   sql  = " select a.ws_code workcode ,a.pro_ggxh ggxh, "
	   		+ " CAST(sum(COALESCE( b.axis_length * b.axis_num,0) ) as int4) count, a.demand_date demanddate "
	   		+ " from pla_course a,pla_course_axis b " 
			+ " where b.course_code=a.ws_code and a.pro_ggxh IN  ("+wheresql+") " 
			+ " group by  a.ws_code ,a.pro_ggxh,a.demand_date order by a.demand_date ";
	   Page page=this.pageSQLQueryVO(sql, p.getPageNo(), p.getPagesize(),new NeedPartPlaCourseVo());
//	   List<Object[]> list = this.createQuery(sql).list();
//		for (Object[] objects : list) {
//			NeedPartPlaCourseVo npc = new NeedPartPlaCourseVo();
//			if(objects[0]!=null){
//				npc.setWorkcode((String)objects[0]);
//			}
//			else{
//				break;
//			}
//			npc.setGgxh((String)objects[1]);
//			npc.setCount((int)objects[2]);
//			npc.setDemandDate((Date)objects[3]);
//			npcv.add(npc);
//	   }
//	 return npcv;
	   return page;
	}
	
	/**
	 * 根据工单号，查询工单具体轴
	 * @param wsCode
	 * @return
	 */
	public  List<PlaCourseAxis> getPlaCourseAxisByWsCode(String wsCode){
		String hql = " from PlaCourseAxis where courseCode = ? ";
		@SuppressWarnings("unchecked")
		List<PlaCourseAxis> list = this.createQuery(hql, wsCode).list();
		return list;
	}
	

}
