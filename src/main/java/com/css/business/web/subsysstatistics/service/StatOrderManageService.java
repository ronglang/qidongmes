package com.css.business.web.subsysstatistics.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaProductOrder;
import com.css.business.web.subsysstatistics.bean.StatCourseDisplayVo;
import com.css.business.web.subsysstatistics.bean.StatMachineDisplayVo;
import com.css.business.web.subsysstatistics.bean.StatOrderDisplayVo;
import com.css.business.web.subsysstatistics.dao.StatOrderManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("statOrderManageService")
public class StatOrderManageService extends
		BaseEntityManageImpl<PlaProductOrder, StatOrderManageDAO> {

	@Resource(name="statOrderManageDAO")
	//@Autowired
	private StatOrderManageDAO dao;
	
	
	@Override
	public StatOrderManageDAO getEntityDaoInf() {
		return dao;
	}

	/**
	 * 
	 * @Description: 查询出生产令生产中的相关信息   
	 * @return
	 */
	public List<StatOrderDisplayVo>queryAllStatOrder(){
		String sql = "select from view_order_exception";
		List<Object[]> list = dao.createSQLQuery(sql).list();
		List<StatOrderDisplayVo>resultList = new ArrayList<>();
		for (Object[] objs : list) {
			try {
				StatOrderDisplayVo vo = new StatOrderDisplayVo();
				//合同编号不应该出现
			//	vo.setContractId(objs[0]==null?-1:Integer.parseInt(objs[0].toString()));
				vo.setProductOrderCode(objs[1]==null?"-1":objs[1].toString());
				vo.setBillDate(objs[2]==null?"-1":objs[2].toString());
				vo.setDemandDate(objs[3]==null?"-1":objs[3].toString());
				vo.setAmount(objs[4]==null?-1:Integer.parseInt(objs[4].toString()));
				vo.setCompleteAmount(objs[5]==null?-1:Integer.parseInt(objs[5].toString()));
				vo.setDemandPartLen(objs[6]==null?-1:Double.parseDouble(objs[6].toString()));
				vo.setProductPartLen(objs[7]==null?-1:Double.parseDouble(objs[7].toString()));
				vo.setProGgxh(objs[8]==null?"-1":objs[8].toString());
				vo.setProductOrderState(objs[9]==null?"-1":objs[9].toString());
				vo.setIs_flag(Integer.parseInt(objs[10].toString())==0?"未加入":"已加入");
				vo.setEx_count(objs[11]==null?-1:Integer.parseInt(objs[11].toString()));
				resultList.add(vo);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				continue;
			}
		}
		
		if (resultList != null && resultList.size()>0) {
			return resultList;
		}
		return null;
	}
	
	/**
	 * 
	 * @Description: 根据生产令编号获得该编号下所有的工单生产情况   
	 * @param orderCode 生产令编号
	 * @param percent 选择的百分比
	 * @return
	 */
	public List<StatCourseDisplayVo>queryStatCourseDisplayVoByOrderCode(String orderCode,String percent){
		String sql ="SELECT "
				+ "pms.course_code AS coursecode, "
				+ "pms.fact_start_time AS start_time, "
				+ "pms.fact_end_time AS end_time, "
				+ "mm.mac_type AS mactype, "
				+ "mm.mac_name AS macname "
				+ "FROM pla_product_order AS po, "
				+ "pla_week_plan AS pw, "
				+ "pla_machine_plan_schedule AS pms, "
				+ "mau_machine AS mm  "
				+ "WHERE "
				+ "pms.week_plan_id = pw.\"id\" "
				+ " AND pw.product_order_id = po.\"id\" "
				+ " AND pms.machine_id = mm.\"id\" "
				+ " AND po.product_order_code='"+orderCode +"'";
		//工单编号,实际开始时间,实际结束时间,工序,机台名称()
		List<Object[]> list = dao.createSQLQuery(sql).list();
		List<StatCourseDisplayVo>resultList = new ArrayList<>();
		for (Object[] objs : list) {
			StatCourseDisplayVo vo = new StatCourseDisplayVo();
			vo.setCourseCode(objs[0]==null?"-1":objs[0].toString());
			
			vo.setStartTime(objs[1]==null?"-1":objs[1].toString());
			vo.setEndTime(objs[2]==null?"-1":objs[2].toString());
			vo.setSeqName(objs[3]==null?"-1":objs[3].toString());
			//暂时不用机器名称
		//	vo.setMachineName(objs[4]==null?"-1":objs[4].toString());
		}
		return null;
		
	}
	
	/**
	 * 
	 * @Description: 通过工单编号,参数编码,查出这个参数的最大值,最小值,平均值,超过/低于平均值百分比的所占比   
	 * @param courseCode 工单编号 
	 * @param paramCode  参数编码
	 * @return
	 */
	public List<String>queryLineByCourseCode(String courseCode,String paramCode,String percent){
		String sql = "";
		Query createSQLQuery = dao.createSQLQuery(sql);
		
		return null;
	}
	
	/**
	 * 
	 * @Description: 查询所有的机台信息   
	 * @return
	 */
	public Page queryMachine(Page page){
		String sql="SELECT "
				+ "mm.mac_name, "
				+ "mm.mac_state, "
				+ "\"count\"(pms) pmscount,"
				+ "\"sum\" (pms.semi_product_len) "
				+ "FROM "
				+ "\"public\".mau_machine AS mm,"
				+ "\"public\".pla_machine_plan_schedule AS pms "
				+ "WHERE "
				+ "mm.\"id\" = pms.machine_id "
				+ "GROUP BY mm.mac_name , "
				+ "mm.mac_state";
		Page page2 = dao.pageSQLQuery(sql, page.getPageNo(), page.getPagesize());
		List<Object[]> list = page.getData();
		//机台名称,机台状态,机台完成工单量,完成的生产长度
		//List<Object[]> list = dao.createSQLQuery(sql).list();
		List resultList = new ArrayList<>();
		if (list!=null && list.size()>0) {
			for (Object[] obj : list) {
				try {
					StatMachineDisplayVo vo = new StatMachineDisplayVo();
					vo.setMachineName(obj[0]==null?"-1":obj[0].toString());
					vo.setState(obj[1]==null?"-1":obj[1].toString());
					vo.setCourseCount(obj[2]==null?"-1":obj[2].toString());
					vo.setProductLen(obj[3]==null?"-1":obj[3].toString());
					vo.setRepairCount("");
					resultList.add(vo);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					continue;
				}
				
			}
		}
		page2.setData(resultList);
		return page2;
	}

}
