package com.css.business.web.subsysplan.plaManage.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.craManage.dao.CraSeqManageDAO;
import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.mauManage.dao.MauMachineManageDAO;
import com.css.business.web.subsysplan.bean.PlaMacTask;
import com.css.business.web.subsysplan.bean.PlaMachinePlanSchedule;
import com.css.business.web.subsysplan.plaManage.bean.PlaMachineDisplayVo;
import com.css.business.web.subsysplan.plaManage.bean.PlaRequestPurchaseDetailedVo;
import com.css.business.web.subsysplan.plaManage.dao.PlaMachinePlanScheduleManageDAO;
import com.css.business.web.syswebsoket.bean.EchartsVo;
import com.css.common.util.DateUtil;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("plaMachinePlanScheduleManageService")
public class PlaMachinePlanScheduleManageService extends BaseEntityManageImpl<PlaMachinePlanSchedule, PlaMachinePlanScheduleManageDAO> {
	@Resource(name = "plaMachinePlanScheduleManageDAO")
	// @Autowired
	private PlaMachinePlanScheduleManageDAO dao;
	
	@Resource(name="craSeqManageDAO")
	private CraSeqManageDAO craSeqManageDAO;

	@Override
	public PlaMachinePlanScheduleManageDAO getEntityDaoInf() {
		return dao;
	}
	
	@Resource(name = "plaMacTypeManageService")
	private PlaMacTypeManageService macService;
	
	/**
	 * @return  service
	 */
	public PlaMacTypeManageService getMacService() {
		return macService;
	}
	
	@Resource(name="mauMachineManageDAO")
	private MauMachineManageDAO mauMachineDao;
	

	/**
	 * 质量回溯列表
	 * 
	 * @param p
	 * @param productcraft
	 * @return page
	 * @throws Exception
	 * @author JS
	 */
	public Page queryPlaMachinePlanScheduleService(Page p) throws Exception {
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM pla_machine_plan_schedule ");
		sql.append(" ORDER BY sort desc ");
		Page page = dao.queryPlaMachinePlanScheduleDao(p, sql.toString());// 查询机台进度表
		return page;
	}

	/**
	 * 
	 * @param p
	 * @param axis_name
	 * @return
	 * @author JS
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Page queryMachineScheduleService(Page p, String axis_name) {
		boolean flag = true;
		long totalCount = 1L;
		int startIndex = Page.getStartOfPage(p.getPageNo(), p.getPagesize());
		List list = new ArrayList();
		while (flag) {
			List axis = dao.queryIncomingAxis(axis_name);
			if (axis != null && axis.get(0) != null && axis.size() > 0) {
				String[] as = axis.get(0).toString().split(",");
				for (int i = 0; i < as.length; i++) {
					StringBuffer sql = new StringBuffer(" SELECT * FROM "
							+ "pla_machine_plan_schedule WHERE axis_name = '"
							+ axis_name + "'");
					List li = dao.querySQLPageList(sql.toString(), startIndex,
							totalCount, p.getPageNo(), p.getPagesize(),
							new PlaRequestPurchaseDetailedVo());
					if (li != null)
						list.add(li.get(0));
					axis_name = as[i];
				}
			} else {
				StringBuffer sql = new StringBuffer(" SELECT * FROM "
						+ "pla_machine_plan_schedule WHERE axis_name = '"
						+ axis_name + "'");
				List li = dao.querySQLPageList(sql.toString(), startIndex,
						totalCount, p.getPageNo(), p.getPagesize(),
						new PlaRequestPurchaseDetailedVo());
				if (li != null)
					list.add(li.get(0));
				flag = false;
			}
		}
		return new Page(p.getPageNo(), totalCount, p.getPagesize(), list);
	}

	public List<PlaRequestPurchaseDetailedVo> queryPlaScheduleParamService(
			Integer week_plan_id, Integer machine_schedule_id) {
		StringBuffer sql = new StringBuffer(
				" SELECT param_name as param_name,param_value as param_value FROM "
						+ "pla_schedule_param " + "WHERE week_plan_id="
						+ week_plan_id + " " + " AND machine_schedule_id ="
						+ machine_schedule_id + "");
		List<PlaRequestPurchaseDetailedVo> list = dao.queryPlaScheduleParam(sql
				.toString());
		return list;
	}

	public void savePlaMachinePlanSchedule(PlaMachinePlanSchedule pps) {
		dao.save(pps);
	}

	/**
	 * @Description:通过机台id去查询当日的生产计划
	 * @param machineId
	 * @return
	 */
	public PlaMachineDisplayVo queryTaskCompByMachineId(String machineId,String machineCode) {
		// TODO Auto-generated method stub
		//PlaMacType plaMacType = macService.get(machineId);
		/*
		String macName = "";
		if(machineId != null && machineId.length() > 0){
			MauMachine mauMachine = mauMachineDao.get(Integer.parseInt(machineId));
			seqCode = mauMachine.getSeqCode();
			macName = mauMachine.getMacType();
		}
		else if(seqCode != null && seqCode.length() > 0){
			macName = craSeqManageDAO.getNameByCode(seqCode);
		}
		*/
		//
		String macName = "";
		String hql = "";
		MauMachine mauMachine = new MauMachine();
		if(machineCode != null && machineCode.length() > 0){
			hql = "from MauMachine where macCode = '"+machineCode+"'";
			mauMachine = mauMachineDao.listQuery(hql).get(0);
		}else if(machineId != null && machineId.length() > 0){
			mauMachine = mauMachineDao.get(Integer.parseInt(machineId));
		}
		
		//
		List<Object[]> list = getTodayList(mauMachine.getSeqCode());
		PlaMachineDisplayVo vo = new PlaMachineDisplayVo();
		List<String>X  = new ArrayList<>();
		List<String>Y = new ArrayList<>();
		if (list!=null && list.size()>0) {
			for (Object obj[] :  list) {
				String s = obj[0].toString();
				X.add(s);
				BigDecimal bd = new BigDecimal(obj[1].toString());
				Integer bl = bd.multiply(new BigDecimal(100)).intValue();
				Y.add(bl.toString());
			}
		}
		
		vo.setDataList(Y);
		vo.setAreas(X);
		vo.setMachinename(macName);  
		vo.setMauMachine(mauMachine);
		return vo;
		
	}
	
	//旧方法
	public PlaMachineDisplayVo queryTaskCompByMachineId(String machineId) {
		List <Object[]>list = new ArrayList<>();
		MauMachine mauMachine = mauMachineDao.get(Integer.parseInt(machineId));
		List<Object[]> list1 = getTodayList(machineId,"生产中");
		if (list1 != null) {
			list.addAll(list1);
		}
		List<Object[]> list2 = getTodayList(machineId,"未开始");
		if (list2 != null) {
			list.addAll(list2);
		}
		List<Object[]> list3 = getTodayList(machineId,"已生产");
		if (list3 != null) {
			list.addAll(list3);
		}
		PlaMachineDisplayVo vo = new PlaMachineDisplayVo();
		List<String>X  = new ArrayList<>();
		List<String>Y = new ArrayList<>();
		if (list!=null && list.size()>0) {
			for (Object[] obj : list) {
				X.add(obj[0].toString());
				BigDecimal partLen = new BigDecimal(obj[1].toString());
				if (obj[2] == null) {
					obj[2] = 0;
				}
				BigDecimal proLen =  new BigDecimal(obj[2].toString());
				BigDecimal divide = proLen.divide(partLen);
				BigDecimal result = new BigDecimal(Double.toString(100))
					.multiply(divide);
				Y.add(result.toString().substring(0,result.toString().indexOf(".") + 2));
			}
			 
			vo.setDataList(Y);
			vo.setAreas(X);
			vo.setMachinename(mauMachine.getMacName());
			return vo;
		}
		return vo;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTodayList(String seqCode){
		String sql = "select p.mac_code, ";
	    sql += "sum(COALESCE(case when p.product_state='已生产' then a.part_len else 0 end,0) ) "
				+ "/COALESCE(sum ((COALESCE(a.part_len,0))+0.0),1) bl "
				+ "from pla_product_order_axis a ,pla_machine_plan p where a.axis_name=p.axis_name and p.sort=1 "
				+ "and exists(select 1 from pla_product_order o where o.product_order_code=p.course_code and o.is_flag='3')  "
				+ "and p.seq_code='"+seqCode+"' "
				+ "group by p.mac_code" ;
	    List<Object[]> list = dao.createSQLQuery(sql).list();
		return list;
	}
	
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param machineId 机台id
	 * @param state 生产状态
	 * @return
	 */
	public List<Object[]>getTodayList(String macCode,String state){
		//Integer macId = Integer.parseInt(machineId);
		String sql = "SELECT "
				+ "pm.course_code, "
				+ "pm.part_len, "
				//+ "pms.semi_product_len "
				+ "case  when pm.product_state='生产中' or pm.product_state='未开始' then '0' else  pm.part_len end  as leng "
				+ "FROM "
				+ "pla_machine_plan AS pm, "
				+ "pla_machine_plan_schedule AS pms "
				+ " WHERE "
				+ "pm.id = pms.machine_plan_id "
				//+ "AND pms.work_date = '"+DateUtil.format(new Date(), "yyyy-MM-dd")+"' "
				//+ " and pm.work_day="+DateUtil.getCurrentDateInYyyyMMdd_intVal()
				+ " AND pm.product_state = '"+state+"' "
				//+ "AND pm.machine_id = '"+machineId+"' ";
				+ "AND pm.mac_code = '"+macCode+"' ";
			@SuppressWarnings("unchecked")

			List<Object[]> list = dao.createSQLQuery(sql).list();
				return list;
	}
	
	/**
	 * 
	 * @Description: 根据工单编号,生产状态,找到生产令编号,生产令计划长度 ,产品规格
	 * @param courseCode
	 * @param machineState
	 * @return
	 */
	public List<Object[]>getOrderByCourse(String courseCode,String machineState){
		String sql="SELECT po.product_order_code, "
				+ "po.product_part_len , "
				+ "po.pro_ggxh"
				+ "FROM "
				+ "pla_machine_plan AS pm,"
				+ " pla_week_plan AS pw,"
				+ " pla_product_order AS po "
				+ "WHERE "
				+ "pm.week_plan_id = pw.\"id\" "
				+ "AND pw.product_order_id = po.\"id\" "
				+ "AND pm.week_plan_id = pw.\"id\" "
				+ "AND pm.product_state = '"+machineState +"' "
				+ "AND pm.course_code = '"+ courseCode +"'";
		List<Object[]> list = dao.createQuery(sql).list();
		if (list!=null && list.size()>0) {
			return list;
		}
		return null;
	}


	/**   
	 * @Description: 通过rfid查询最新的数据   
	 * @param semiAxis
	 * @return         
	 */ 
	public PlaMachinePlanSchedule getLastBySemiAxis(String semiAxis) {
		// TODO Auto-generated method stub
		String hql = "from PlaMachinePlanSchedule where seqSemiProductAxis ='"+semiAxis+"' order by factEndTime DESC";
		List<PlaMachinePlanSchedule> list = dao.createQuery(hql).list();
		if (list != null && list.size() > 0 ) {
			return list.get(0);
		}
		return null;
	}


	/**   
	 * @Description: 通过轴名称去查询此计划进度  
	 * @param axisName
	 * @return         
	 */ 
	public PlaMachinePlanSchedule getByAxisName(String axisName) {
		// TODO Auto-generated method stub
		String hql = "from PlaMachinePlanSchedule where axisName = '"+axisName+"'";
		List<PlaMachinePlanSchedule> listQuery = dao.listQuery(hql);
		if (listQuery != null && listQuery.size() > 0) {
			return listQuery.get(0);
		}
		return null;
	}

	/**   
	 * @Description: 机器计划进度   
	 * @param machineId
	 * @param start  开始时间
 	 * @param end  结束时间
	 * @return         
	 */ 
	public List<PlaMachinePlanSchedule> getListByTime(Integer machineId,
			String start, String end) {
		// TODO Auto-generated method stub
		String hql = " from PlaMachinePlanSchedule "
				+ "where machineId = '"+machineId+"' "
				+ "and fact_start_time >= '"+start+"' "
				+ "and fact_end_time <='"+end+"'";
		List<PlaMachinePlanSchedule> listQuery = dao.listQuery(hql);
		if (listQuery != null && listQuery.size() > 0) {
			return listQuery;
		}
		return null;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param seqCode
	 * @return         
	 */ 
	public PlaMachineDisplayVo initDisplayTask(String seqCode) {
		// TODO Auto-generated method stub
		//String hql ="select machineId || '' from PlaMachinePlanSchedule where seqCode='"+seqCode+"' order by createDate DESC ";
		String hql ="select macCode || '' from MauMachine where seqCode='"+seqCode+"' order by createDate DESC ";
		@SuppressWarnings("unchecked")
		List<String> list =  dao.createQuery(hql).list();
		//PlaMachineDisplayVo displayVo = queryTaskCompByMachineId(list.get(0)+"");
		//return displayVo;
		//横坐标. 机台id
		PlaMachineDisplayVo p = new PlaMachineDisplayVo();
		p.setAreas(list);
		String name =  craSeqManageDAO.getNameByCode(seqCode);
		p.setMachinename(name);  //页的工序名称
		return p;
	}

	/**
	 * 
	 * @Description:获得当日的计划及生产情况
	 * @param seqCode
	 * @return
	 */
	public PlaMachineDisplayVo getTodayProductionBySeq(String seqCode,Integer workDay) {
		List<String>legendList = new ArrayList<>();
		Map<String,List<String>>sDataList = new HashMap<>(16);
		List<String>macFiniList = new ArrayList<String>();
		List<String>macNoFiniList = new ArrayList<String>();
		//完成量
		Float macFini = 0.0F;
		//未完成量
		Float macNoFini = 0.0F;
		Float macPartLen = 0.0F;
		String macCode = "";
		Integer count = 1;
		int detailCount = 0;
		//1. 计算当天需要生产的任务
		String format = DateUtil.format(new Date(), "yyyy-MM-dd ");
		String start = format + "00:00:00";
		String end = format + "23:59:59";
		
		String hql = "from PlaMacTask where pstime >= '"+start+"' and pdtime <= '"+end+"'  and productstate = '已排产' order by maccode";
		List<PlaMacTask> list2 = dao.createQuery(hql).list();
		if (list2 == null   || list2.size() == 0) {
			return null;
		}
		macCode = list2.get(0).getMaccode();
		
		for (PlaMacTask task : list2) {
			count++;
			if (macCode.equals(task.getMaccode())) {
				//同个机台
				macFini +=task.getSchedule();
				detailCount++;
			}else{
				//不同机台
				//1.保存上一个
				legendList.add(macCode);
				macFiniList.add(String.valueOf(macFini/detailCount));
				macNoFiniList.add(String.valueOf(100 - macFini/detailCount));
				//2.初始数据
				macCode = task.getMaccode();
				detailCount = 0;
				macFini =0.0F;
				macFini +=task.getSchedule();
				detailCount++;
			}
			if (count == list2.size()) {
				legendList.add(macCode);
				macFiniList.add(String.valueOf(macFini/detailCount));
				macNoFiniList.add(String.valueOf(100 - macFini/detailCount));
			}
			
		}
		
		PlaMachineDisplayVo vo = new PlaMachineDisplayVo();
		vo.setLegends(legendList);
		sDataList.put("完成量", macFiniList);
		sDataList.put("未完成量", macNoFiniList);
		vo.setmDataList(sDataList);
		
		
		
		
		
		return vo;
	}

	/**
	 * 
	 * @Description:查询机台任务进度
	 * @param planid
	 * @param courseCode
	 * @return
	 */
	public List<PlaMachinePlanSchedule> getBypPlanIdAndCourseCode(Integer planid, String courseCode) {
		String hql = "from PlaMachinePlanSchedule where machinePlanId = "+planid+" and  courseCode = '"+courseCode+"'"; 
		List<PlaMachinePlanSchedule> listQuery = dao.listQuery(hql);
		return listQuery;
	}



	
}
