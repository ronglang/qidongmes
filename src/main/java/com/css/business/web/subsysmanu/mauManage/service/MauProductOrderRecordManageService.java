package com.css.business.web.subsysmanu.mauManage.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.business.web.subsysmanu.bean.MauProductOrderRecord;
import com.css.business.web.subsysmanu.mauManage.dao.MauProductOrderRecordManageDAO;
import com.css.business.web.subsysplan.plaManage.service.PlaMachinePlanManageService;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("mauProductOrderRecordManageService")
public class MauProductOrderRecordManageService
		extends
		BaseEntityManageImpl<MauProductOrderRecord, MauProductOrderRecordManageDAO> {
	@Resource(name = "mauProductOrderRecordManageDAO")
	// @Autowired
	private MauProductOrderRecordManageDAO dao;

	@Override
	public MauProductOrderRecordManageDAO getEntityDaoInf() {
		return dao;
	}

	/**
	 * 查询生产令延期情况
	 *@data:2017年7月14日
	@param time1
	@param time2
	@param productOrderCode
	@param materType
	@param contractId
	@param p
	@return
	@throws Exception
	@autor:wl
	 */
	public Page queryMauProductOrderRecord(String time1, String time2, String productOrderCode,
			String materType, String contractId ,Page p)
			throws Exception {
		List<Object[]> list = getObjectList();
		List<MauProductOrderRecord> listPage=new ArrayList<>();
		if(list.size()>0){
			for (Object[] obj : list) {
				MauProductOrderRecord rd=new MauProductOrderRecord();
				rd.setCourseCode((String)obj[0]);
				rd.setProGgxh((String)obj[1]);
				rd.setAxisAmount(obj[2]+"");
				rd.setPartLen(obj[3]+"");
				rd.setWorkStartDate((Timestamp)obj[4]);
				rd.setWorkEndDate((Timestamp)obj[5]);
				rd.setFactStartTime((Timestamp)obj[6]);
				rd.setFactEndTime((Timestamp)obj[7]);
				rd.setSeqHours(obj[8]+"");
				listPage.add(rd);
			}
		}
		Page page = new Page(p.getPageNo(),list.size(),p.getPagesize(),listPage);
		return page;
	}
	/**
	 * 通过查询数据库获取生产周计划延期的情况
	 *@data:2017年7月14日
	@return
	@autor:wl
	 */
	private List<Object[]> getObjectList() {
	/*	String sql="select a.product_order_code,d.porder_code,a.demand_part_len,a.pro_ggxh,a.amount,b.work_start_date,"
				+ "b.work_end_date,c.fact_start_time,c.fact_end_time from pla_product_order a,pla_week_plan b ,pla_machine_plan_schedule c"
				+ ",sell_contract_detail d where a.id=b.product_order_id  and b.id=c.week_plan_id and d.id=a.contract_id  "
				+ "and c.fact_start_time>b.work_start_date and a.product_order_state!='完成'";*/
		String sql ="select a.course_code,c.pro_ggxh,b.axis_amount,b.part_len,b.work_start_date,b.work_end_date,"
				+ "a.fact_start_time,a.fact_end_time,c.seq_hours   from pla_machine_plan a,"
				+ "pla_week_plan b,pla_week_seq_hours c where a.week_plan_id=b.id and b.product_order_id=c.pla_order_id "
				+ " and a.fact_start_time>b.work_start_date  and a.product_state='已结束'";
		List<Object[]> list=dao.getChartList(sql);
		return list;
	}

	public List<MauProductOrderRecord> queryStoreRfid(String rfid) {
		String hql = " from MauProductOrderRecord s where s.inoutType='入库' and s.rfidCode = ? ";
		@SuppressWarnings("unchecked")
		List<MauProductOrderRecord> list = dao.createQuery(hql, rfid).list();
		return list;
	}

	/**
	 * 查询柱状图数据
	 * 
	 * @param objSort
	 * @return
	 * @author JS
	 */
/*	public Map<String, List<String>> getChart(String objSort) {

		String sql = "";
		List<Object[]> listObject = dao.getChartList(sql);
		String hql = " from MauProductOrderRecord s  ";
		List<MauProductOrderRecord> list = dao.getChartDao(hql);
		Map<String, List<String>> chartsMap = new HashMap<String, List<String>>();
		if (list.size() > 0 && list != null) {
			// 柱状图的X轴
			List<String> xAxis = new ArrayList<>();
			// 柱状图的Y轴
			List<String> series = new ArrayList<>();
			for (MauProductOrderRecord storeReturn : list) {
				// xAxis.add(storeReturn.getObjGgxh());
				// series.add(storeReturn.getAcount()+"");
			}
			chartsMap.put("xAxis", xAxis);
			chartsMap.put("series", series);
		}
		return chartsMap;
	}*/




	
	/**
	 * 查询饼状图数据
	 * 
	 * @data:2017年7月11日
	 * @return
	 * @autor:wl
	 */
	public Map<String, List<String>> getPineChartDate() {
		String hql = "select a.axis_amount as plan,count(b.axis_name)"
				+ "as mau  from pla_week_plan a,pla_machine_plan b where a.id=b.week_plan_id and b.product_state='已结束' "
				+ "GROUP BY a.axis_amount";
		List<Object[]> list = dao.getChartDao(hql);
		List<String> listPlan=new ArrayList<>();
		List<String> listMau=new ArrayList<>();
		if(null!=list&&list.size()>0){
			for (Object[] obj : list) {
			Object a=obj[0];
			Object b=obj[1];
			listPlan.add(a==null?"100":a+"");
			listMau.add(b==null?"0":b+"");
			}
		}else{
			listPlan.add("100");
			listMau.add("0");
		}
		Map<String, List<String>> chartsMap = new HashMap<String, List<String>>();
		if(listPlan.size()>0||listMau.size()>0){
			chartsMap.put("listPlan", listPlan);
			chartsMap.put("listMau", listMau);
			}
		
		
		return chartsMap;
	}

}
