package com.css.business.web.subsysstatistics.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauMachine;
import com.css.business.web.subsysmanu.mauManage.service.MauExceptionManageService;
import com.css.business.web.subsysmanu.mauManage.service.MauMachineManageService;
import com.css.business.web.subsysstatistics.bean.StatCourse;
import com.css.business.web.subsysstatistics.dao.StatCourseManageDAO;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("statCourseManageService")
public class StatCourseManageService extends
		BaseEntityManageImpl<StatCourse, StatCourseManageDAO> {

	@Resource(name="statCourseManageDAO")
	//@Autowired
	private StatCourseManageDAO dao;
	
	
	@Override
	public StatCourseManageDAO getEntityDaoInf() {
		return dao;
	}
	@Resource(name="mauMachineManageService")
	private MauMachineManageService macService;
	@Resource(name="mauExceptionManageService")
	private MauExceptionManageService mauExceptionService;

	/**   
	 * @Description: 通过工单编号去保存记录   
	 * @param courseCode         
	 * @param machineId 
	 */ 
	public void saveStatCourse(String courseCode, String machineId) {
		
		//参数code ,具体看需要记录哪些信息
		String paramCode="";
		StatCourse statCourse = new StatCourse();
		/**
		 * 1.通过工单编号和参数code去实时数据表中查询数据
		 */
		List list=this.queryMau_process_dany(courseCode,machineId,paramCode);
		/**
		 * 2.解析获得的数据
		 */
		if (list==null || list.size()<1)return;
		
		Map<String,String>map = this.analy_list(list);
		if (map == null || map.size() <1)return; 
		statCourse.setMaxValue(map.get("maxF"));
		statCourse.setMinValue(map.get("minF"));
		statCourse.setAvgValue(map.get("avgF"));
		statCourse.setCourse_code(courseCode);
		MauMachine mauMachine = macService.get(machineId);
		statCourse.setMacName(mauMachine.getMacName());
		/**
		 * 3. 获得异常数
		 */
		Integer countE  = mauExceptionService.queryCount(courseCode,mauMachine.getMacCode());
		if (countE != null) {
			statCourse.setExCount(countE.toString());
		}
		/**
		 * 4.获得开始时间,结束时间,机台计划中去查
		 */
		String startTime = getCourseTime(courseCode,"start");
		String endTime = getCourseTime(courseCode,"end");
		statCourse.setStartTime(startTime);
		statCourse.setEndTime(endTime);
		statCourse.setCreateDate(new Date());

		dao.save(statCourse);
		
	} 


	/**   
	 * @Description: 求该工单的开始时间和结束时间,日计划表中查询   
	 * @param courseCode
	 * @param term   start 开始时间  end 结束时间
	 * @return         
	 */ 
	private String getCourseTime(String courseCode, String term) {
		// TODO Auto-generated method stub
		if ("start".equals(term)) {
			//查开始时间
			String sql = "select  fact_start_time from pla_machine_plan where course_code='"+courseCode+"' order By fact_start_time   ";
			List list = dao.createSQLQuery(sql).list();
			if (list != null && list.size()>0) {
				return list.get(0).toString();
			}
		} else {
			//查结束时间
			String sql = "select  fact_end_time from pla_machine_plan where course_code='"+courseCode+"' order By fact_end_time DESC  ";
			List list = dao.createSQLQuery(sql).list();
			if (list != null && list.size()>0) {
				return list.get(0).toString();
			}
		}
		
		return null;
	}


	/**   
	 * @Description: 解析参数   最大值,最小值,平均值
	 * @param list 是参数值
	 * @return         
	 */ 
	@SuppressWarnings("unchecked")
	private Map<String, String> analy_list(List list) {
		// TODO Auto-generated method stub
		Map<String, String>map = new HashMap<String, String>();
		List<Double>dList = new ArrayList<>();
		dList.addAll(list);
		//修改打double格式,保留两位小数
		DecimalFormat  df   = new DecimalFormat("######0.00");   
		Double max = Collections.max(dList);
		Double min = Collections.min(dList);
		String maxF = df.format(max);
		String minF = df.format(min);
		//Collections.
		Double total = 0.0;
		Double avg = 0.0;
		Double upAvg = null;
		Double lessAvg = null;
		Integer count = 0;
		Integer avgCount = 0;
		Integer lessCount = 0;
		//求总数
		for (Object obj : list) {
			try {
				Double value = Double.parseDouble(obj.toString());
				total += value;
				count += 1;
			} catch (Exception e) {
				// TODO: handle exception
				continue;
			}
		}
		//平均数
		if (count != 0) {
			avg = total/count;
		}
		String avgF = df.format(avg);
		map.put("max", maxF);
		map.put("min", minF);
		map.put("avg", avgF);
		
		if (map.size()>0 && map != null ) {
			return map;
		}
		return null;
	}


	/**   
	 * @Description: 通过工单编号和参数code去查询    对应的参数value
	 * @param courseCode 工单编号
	 * @param machineId 机器id
	 * @param paramCode 参数code
	 * @return         
	 */ 
	private List<Object> queryMau_process_dany(String courseCode,String machineId,String paramCode) {
		// TODO Auto-generated method stub
		String sql = "select "
				+ "md.param_value "
				+ "from mau_process_dany md "
				+ "where md.course_code='"+ courseCode +"' "
						+ "and param_code='"+paramCode +"' "
						+ "and machine_id = '"+machineId+"'";
		List<Object> list = dao.createSQLQuery(sql).list();
		if (list!=null && list.size()>0) {
			return list;
		}
		return null;
	}

	

}
