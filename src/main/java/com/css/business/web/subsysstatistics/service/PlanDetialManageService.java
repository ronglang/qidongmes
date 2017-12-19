package com.css.business.web.subsysstatistics.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysplan.bean.PlaCourse;
import com.css.business.web.subsysstatistics.bean.PlanDetialVo;
import com.css.business.web.subsysstatistics.dao.PlanDetialManageDao;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("planDetialManageService")
public class PlanDetialManageService extends BaseEntityManageImpl<PlaCourse, PlanDetialManageDao> {

	@Resource(name = "planDetialManageDao")
	private PlanDetialManageDao dao;
	
	@Override
	public PlanDetialManageDao getEntityDaoInf() {
		// TODO Auto-generated method stub
		return dao;
	}

	/**   
	 * @Description: 统计查询   
	 * @param page
	 * @param map
	 * @return         
	 */ 
	public Page queryPageList(Page page, Map<String, String> map) {
		// TODO Auto-generated method stub
		String sql = getSql(map);
		Page queryPage = page;
		try {
			queryPage = dao.pageSQLQueryVONoneDesc(sql, page.getPageNo(), page.getPagesize(), new PlanDetialVo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryPage;
	}

	
	/**   
	 * @Description: 获得sql  
	 * @param map
	 * @return         
	 */ 
	private String getSql(Map<String, String> map) {
		// TODO Auto-generated method stub
		String ordercode = null;
		String workDay = null;
		String ggxh = null ;
		if (map != null ) {
			if (map.get("workDay") !=null && map.get("workDay") !="" ) {
				workDay = map.get("workDay");
			} else {
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				workDay = sdf.format(date);
						
			}
			
			if (map.get("ordercode") !=null && map.get("ordercode") !="") {
				ordercode = map.get("ordercode");
			}
			if (map.get("ggxh") !=null && map.get("ggxh") !="") {
				ggxh = map.get("ggxh");
			}
			
			
		} else {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			workDay = sdf.format(date);
					
		}
		String sql = "SELECT "
				+ "po.id orderid,"
				+ "po.product_order_code ordercode,"
				+ "po.pro_ggxh proggxh, "
				+ "pw.axis_amount  axiscount,"
				+ "pw.work_day workday "
				+ "FROM "
				+ "pla_week_plan  pw,"
				+ "pla_product_order po "
				+ "WHERE "
			//	+ "po.id= pw.product_order_id AND pw.work_day ='20170812'";
		+ "po.id= pw.product_order_id AND pw.work_day ='"+workDay+"'";
		
		if (ordercode != null && ordercode != "") {
			sql += " and po.product_order_code = '" + ordercode + "' ";
		}
		if (ggxh != null && ggxh != "") {
			sql += " and po.pro_ggxh = '" + ggxh + "'";
		}
		
		return sql;
	}

	/**   
	 * @Description: 通过工单编号 和 生产日期获得详情   
	 * @param page
	 * @param map
	 * @return         
	 */ 
	public Page queryDetailPageList(Page page, Map<String, String> map) {
		// TODO Auto-generated method stub
		String sql = getDetailSql(map);
		Page queryPage = page;
		try {
			queryPage = dao.pageSQLQueryVONoneDesc(sql, page.getPageNo(), page.getPagesize(), new PlanDetialVo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryPage;
	}

	/**   
	 * @Description: 详情sql  
	 * @param map
	 * @return         
	 */ 
	private String getDetailSql(Map<String, String> map) {
		// TODO Auto-generated method stub
		
		String sql ="SELECT "
				+ "ph.pro_ggxh proggxh /** 规格型号 */,"
				+ "ph.mac_code  maccode /** 机台编号 */,"
				+ "pl.main_by  mainby/** 主操作手 */,"
				+ "pl.vice_by  viceby/** 副操作手 */,"
				+ "cr.seq_name  seqname/** 工序名称 */,"
				+ "pl.course_code  coursecode /** 工单编号 */,"
				+ "pl.product_state productstate/** 生产状态 */,"
				+ "pl.part_len partlen/** 生产段长 */,"
				+ "pl.id planid /** 机台计划id */,"
				+ "ps.axis_name axisname/**轴名称*/,"
				+ "ps.semi_product_len  semilen/** 半成品长度  */"
				+ " from "
				+ " pla_product_order_seq_hourse ph "
				+ " LEFT JOIN cra_seq cr ON cr.seq_code = ph.seq_code, "
				+ " pla_machine_plan pl "
				+ " LEFT JOIN pla_machine_plan_schedule ps ON pl.\"id\" = ps.machine_plan_id "
				+ " WHERE "
				+ " ph.mac_code = pl.mac_code "
				+ " AND ph.pla_order_id = '"+map.get("orderid")+"' "
				+ " AND ph.work_day = '"+map.get("workday")+"'";
		return sql;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param page
	 * @param map
	 * @return         
	 */ 
	public Page queryMaterDetailPageList(Page page, Map<String, String> map) {
		// TODO Auto-generated method stub
		String sql="SELECT "
				+ "pm.amount materamount/**原材料数量*/ ,"
				+ "pm.axis_name  axisname/**轴名称*/,"
				+ "pm.flag flag /**是否领料*/,"
				+ "pm.ggxh  materggxh/**原材料规格型号*/,"
				+ "pm.mater_name matername /**原材料名称*/,"
				+ "pm.unit unit/**原材料单位*/"
				+ "FROM"
				+ " pla_machine_plan_mater pm  "
		//		+ "where  pm.machine_plan_id ='23'";
		+ "where  pm.machine_plan_id ='"+map.get("planid")+"'";
		Page queryPage = page;
		try {
			queryPage = dao.pageSQLQueryVONoneDesc(sql, page.getPageNo(), page.getPagesize(),  new PlanDetialVo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryPage;
	}

}
