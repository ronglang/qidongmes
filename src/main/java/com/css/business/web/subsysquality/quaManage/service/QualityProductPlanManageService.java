package com.css.business.web.subsysquality.quaManage.service;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.business.web.subsyscraft.craManage.service.CraProSeqRelationManageService;
import com.css.business.web.subsysplan.bean.PlaProductOrderSeqHourse;
import com.css.business.web.subsysplan.plaManage.service.PlaProductOrderManageService;
import com.css.business.web.subsysplan.plaManage.service.PlaProductOrderSeqHourseManageService;
import com.css.business.web.subsysquality.bean.QualityProductPlan;
import com.css.business.web.subsysquality.quaManage.dao.QualityProductPlanManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

/**
 * 
 * @Title: QualityProductPlanManageService.java
 * @Package com.css.business.web.subsysquality.quaManage.service
 * @Description: 成品半成品检测
 * @author rb
 * @date 2017年8月11日 上午10:00:37
 * @company SMTC
 */
@Service("qualityProductPlanManageService")
public class QualityProductPlanManageService extends
		BaseEntityManageImpl<QualityProductPlan, QualityProductPlanManageDAO> {
	@Resource(name = "qualityProductPlanManageDAO")
	// @Autowired
	private QualityProductPlanManageDAO dao;

	@Override
	public QualityProductPlanManageDAO getEntityDaoInf() {
		return dao;
	}

	// 生产令的service
	@Autowired
	private PlaProductOrderManageService poService;
	// 生产令的拆分表的service
	@Autowired
	private PlaProductOrderSeqHourseManageService phService;
	// 产品工艺，工序，工艺关系表 功能查芯数
	@Autowired
	private CraProSeqRelationManageService cpService;
	
	/**
	 * 导出文本格式化
	 * @return
	 */
	private WritableCellFormat getBodyFormat() {
		WritableCellFormat format = null;
		try {
			WritableFont font = new WritableFont(WritableFont.createFont("宋体"),10, WritableFont.NO_BOLD);
			format = new WritableCellFormat(font);
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(jxl.format.Border.TOP, jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.LEFT, jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.RIGHT, jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.BOTTOM, jxl.format.BorderLineStyle.THIN);

			format.setWrap(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return format;
	}
	/**
	 * 导出     标题格式化
	 * @return
	 */
	private WritableCellFormat getTitalFormat() {
		WritableCellFormat format = null;
		try {
			WritableFont font = new WritableFont(WritableFont.createFont("宋体"),12, WritableFont.BOLD);
			format = new WritableCellFormat(font);
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(jxl.format.Border.TOP,jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.LEFT,jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.RIGHT,jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.BOTTOM,jxl.format.BorderLineStyle.THIN);

			format.setWrap(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return format;
	}
	
	
	/**
	 * 
	 * @Description: 生产令拆分完,就调用次方法查询获得基本信息 pla_product_order_seq_hourse
	 * @param proOrderId
	 */
	public void initQualityProductPlan(Integer proOrderId) {
		// 1.查询pla_product_order_seq_hourse 需要检测的地方
		List<PlaProductOrderSeqHourse> phList = phService.getNeedTest(proOrderId);
		if (phList == null || phList.size() == 0) {
			log.error("生产令（" + proOrderId + "）分解,没有生成需要检测的数据.");
			return;
		}
		// 2.根据生产令获得 工序路线,获得该工艺路线下所有工序信息
		String routeCode = poService.get(proOrderId).getCraRouteCode();
		Map<String, String> seqMap = getMaxSortSeq(routeCode);
		if (seqMap == null || seqMap.size() ==0 ) {
			log.error("生产路线（" + routeCode + "）没有找到下属详细信息.");
			return;
		}
		//优先级最高的工序,这道工序完成后就是成品
		String maxSeq = seqMap.get("maxSort");
		// 3.循环需要检测的中间表,初始化抽检计划
		for (PlaProductOrderSeqHourse ph : phList) {
			try {
				QualityProductPlan plan = new QualityProductPlan();
				plan.setCreateDate(new Date());
				//判断成品,或半成品
				if (ph.getSeqCode() == maxSeq) {
					//成品
					plan.setType(QualityProductPlan.TEST_TYPE_END_PRODUCT);
				} else {
					//半成品
					plan.setType(QualityProductPlan.TEST_TYPE_SEMI_PRODUCT);
				}
				plan.setTestState(QualityProductPlan.TEST_STATE_NO_TEST);
				plan.setAxisName(ph.getAxisName());
				plan.setMacCode(ph.getMacCode());
				plan.setPlanTestDate(ph.getEndDate());
				plan.setPlaOrderId(proOrderId);
				plan.setProGgxh(ph.getProGgxh());
				plan.setSeqCode(ph.getSeqCode());
				plan.setSeqName(seqMap.get(ph.getSeqCode()));
				plan.setCreateDate(new Date());
				String survyCode = (String) getFun("fun_get_mau_code");
				plan.setSurvyCode(survyCode );
				//保存计划
				dao.save(plan);
			} catch (Exception e) {
				// TODO: handle exception
				log.info("工序编号为"+ph.getSeqCode()+"的 不行抽检");
				continue;
			}
			
		}
	}

	/**
	 * @Description: 获得该生产路线下,所有的详细信息
	 * @param routeCode
	 * @return
	 */
	private Map<String, String> getMaxSortSeq(String routeCode) {
		// TODO Auto-generated method stub
		/*String newSql = "SELECT max(crs.sort) sort FROM cra_route_seq crs WHERE crs.route_code = '"
				+ routeCode + "'";
		String sql = "SELECT crs.seq_code from cra_route_seq crs,(" + newSql
				+ ") c where crs.route_code = '" + routeCode
				+ "' and crs.sort = c.sort";
		List list = dao.createSQLQuery(sql).list();
		if (list != null && list.size() > 0) {
			return list.get(0).toString();
		}*/
		//已通过sort排序
		String  sql ="SELECT crs.seq_code,cs.seq_name,cs.test_wastage "
				+ "FROM cra_route_seq crs,cra_seq cs "
				+ "WHERE crs.route_code = '"+routeCode+"' and crs.seq_code = cs.seq_code "
				+ "ORDER BY  crs.sort";
		List<Object[]> list = dao.createSQLQuery(sql).list();
		if (list == null || list.size() == 0) return null;
		
		Map<String, String>map = new HashMap<String, String>();
		//单独把优先级最高的那个提取出来
		map.put("maxSort", list.get(list.size()-1)[0].toString());
		for (Object[] objs : list) {
			//code   name
			map.put(objs[0].toString(), objs[1].toString());
			//test+code  test_wastage 质检损耗
			map.put("test"+objs[0].toString(), objs[2].toString());
		}
		return map;
	}
	
	/**
	 * 
	 * @Description: 根据生产令id去查询本次质检的质检损耗
	 * @param orderId
	 * @return
	 */
	public Double getWastageByOrderId(Integer proOrderId){
		//质检损耗长度
		Double weaLength = 0.0;
		//1.根据生产令获得 工序路线,获得该工艺路线下所有工序信息
		String routeCode = poService.get(proOrderId).getCraRouteCode();
		Map<String, String> sortSeq = getMaxSortSeq(routeCode);
		//2.获取质检记录 通过生产令id
		String hql = " from QualityProductPlan where plaOrderId='"+proOrderId+"'";
		List<QualityProductPlan> planList = dao.createSQLQuery(hql).list();
		
		//3.获取产品信息,主要是几芯
		//芯线数
		Integer core = 0;
		//单个工序损耗长度
		Double each_weaLength = 0.0;
		for (QualityProductPlan plan : planList) {
			try {
				//查询芯数
				String sql = "select core "
						+ "from cra_pro_seq_relation "
						+ "where pro_ggxh='"+plan.getProGgxh()+"' and seq_code='"+plan.getSeqCode()+"'";
				
				core = (Integer) dao.createSQLQuery(sql).list().get(0);
				
				if (plan.getType()==QualityProductPlan.TEST_TYPE_END_PRODUCT) {
					//成品检测 质检默认0.3m
					each_weaLength = 0.3; 
				} else {
					//半成品质检
					each_weaLength = Double.parseDouble(sortSeq.get("test"+plan.getSeqCode()));
				}
				weaLength += each_weaLength*core;
			} catch (Exception e) {
				// TODO: handle exception
				log.error("计算质检损耗出现错误,规格型号（" + plan.getProGgxh() + ", 工序 "+plan.getSeqCode() +"）没有计算成功.此时芯线数"+core+".");
				continue;
			}
		}
		return weaLength;
	}
	
	/**
	 * 
	 * @Description: TODO 根据轴名称获得质检需要消耗的材料   
	 * @param axisName 轴名称
	 * @return
	 */
	public Double getWastage(String axisName){
		//质检损耗长度
		Double weaLength = 0.0;
		String hql = "from from QualityProductPlan where axisName ='"+axisName+"'";
		List<QualityProductPlan> planList = dao.createQuery(hql).list();
		//没数据直接返回
		if(planList == null || planList.size() == 0)return weaLength;
		QualityProductPlan plan = planList.get(0);
		//查询芯数
		String sql = "select core "
				+ "from cra_pro_seq_relation "
				+ "where pro_ggxh='"+plan.getProGgxh()+"' and seq_code='"+plan.getSeqCode()+"'";
		
		Integer core = (Integer) dao.createSQLQuery(sql).list().get(0);
		
		if (plan.getType()==QualityProductPlan.TEST_TYPE_END_PRODUCT) {
			//成品检测 质检默认0.3m
			weaLength = 0.3; 
		} else {
			//半成品质检
			String csSql ="SELECT cs.test_wastage from cra_seq cs where seq_code ='"+plan.getSeqCode()+"'";
			weaLength = (Double) dao.createSQLQuery(csSql).list().get(0);
		}
		
		return weaLength += weaLength*core;
	}

	/**   
	 * @Description: 通过轴名称来获得信息   
	 * @param axisName
	 * @return         
	 */ 
	public QualityProductPlan queryByAxisName(String axisName) {
		// TODO Auto-generated method stub
		String hql = " from QualityProductPlan where axisName='"+axisName.trim()+"'";
		List<QualityProductPlan> list = dao.createQuery(hql).list();
		if (list.size() > 0 && list != null) {
			return list.get(0);
		}
		return null;
	}

	/**   
	 * @Description: 分页查询,电子看板后台管理都可以   
	 * @param page
	 * @param map
	 * @return         
	 */ 
	public Page queryPageList(Page page, Map<String, String> map) {
		// TODO Auto-generated method stub
		String sql = getSql(map);
		Page pageQuery = page;
		try {
			pageQuery = dao.pageQuery(sql, page.getPageNo(), page.getPagesize());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return pageQuery;
	}
	
	/**
	 * 导出
	 * @param response
	 * @param page
	 * @param param
	 */
	public void exportExcelService(HttpServletResponse response,Page page,Map<String,String> map)throws Exception{
		
		// 获取表格样式
		WritableCellFormat bodyFormat = this.getBodyFormat();
		WritableCellFormat titlFormat = this.getTitalFormat();
		response.reset();
		OutputStream outputStream = response.getOutputStream();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sf.format(new Date());
		String titleName = format +"生产质检表";
		titleName = new String(titleName.getBytes(), "ISO8859-1");
		WritableWorkbook wwb = null;
//				Map<String, String> maps = new HashMap<>();
		try {
			// 创建表
			wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(format, 0);
			String[] arrTitle = new String[] { "检测类型", "轴名称", "机器编号", "工序名称","规格型号","计划检测时间","实际检测时间","检测状态",
					"检测人","检验报告单号","检验结果","处理意见"};
			// 写入标题
			for (int i = 0; i < arrTitle.length; i++) {
				ws.addCell(new Label(i, 0, arrTitle[i], titlFormat));
			}

			Page pageList = this.queryPageList(page,map);
			if (null != pageList) {
				  @SuppressWarnings("unchecked")
				List<QualityProductPlan> data = pageList.getData();
				int x = 1;
				for (int i = 0; i < data.size(); i++) {
					QualityProductPlan vo = data.get(i);
					ws.addCell(new Label(0, x, null == vo.getType() ? "" : vo.getType(), bodyFormat));
					ws.addCell(new Label(1, x, null == vo.getAxisName() ? "" : vo.getAxisName(), bodyFormat));
					ws.addCell(new Label(2, x, null == vo.getMacCode() ? "" : vo.getMacCode(), bodyFormat));
					ws.addCell(new Label(3, x, null == vo.getSeqName() ? "" : vo.getSeqName(), bodyFormat));
					ws.addCell(new Label(4, x, null == vo.getProGgxh() ? "" : vo.getProGgxh(), bodyFormat));
					ws.addCell(new Label(5, x, null == vo.getPlanTestDate() ? "" : vo.getPlanTestDate().toString(),bodyFormat));
					ws.addCell(new Label(6, x, null == vo.getFactTestDate() ? "" : vo.getFactTestDate().toString(), bodyFormat));
					ws.addCell(new Label(7, x, null == vo.getTestState() ? "" : vo.getTestState(), bodyFormat));
					ws.addCell(new Label(8, x, null == vo.getTestBy() ? "" : vo.getTestBy(), bodyFormat));
					ws.addCell(new Label(9, x, null == vo.getReportCode() ? "" : vo.getReportCode(), bodyFormat));
					ws.addCell(new Label(10, x, null == vo.getTestResult() ? "" : vo.getTestResult(), bodyFormat));
					ws.addCell(new Label(11, x, null == vo.getAdvice() ? "" : vo.getAdvice(), bodyFormat));
					x++;
				}
			}
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			response.addHeader("Content-Disposition", "attachment; filename=\"" + titleName + ".xls\"");
			wwb.write();
			//maps.put("success", "导出已执行，请查看结果！");
		} catch (Exception e) {
			//maps.put("error", "导出发生异常，导出失败！");
			e.printStackTrace();
			throw e;
		} finally {
			if (null != wwb) {
				wwb.close();
			}
			if (null != outputStream) {
				outputStream.close();
			}
		}
		
	}

	/**   
	 * @Description: 分页查询的sql   
	 * @param map
	 * @return         
	 */ 
	private String getSql(Map<String, String> map) {
		// TODO Auto-generated method stub
		String sql = " from QualityProductPlan where 1 = 1";
		StringBuilder sb = new StringBuilder(sql);
		if (map !=null  && map.size() > 0) {
			if (map.get("start") != null && map.get("start") != "") {
				sb.append(" and  factTestDate >= '"+map.get("start").trim()+"'");
			}
			if (map.get("end") != null && map.get("end") != "") {
				sb.append(" and  factTestDate <= '"+map.get("end").trim()+"'");
			}
			//轴名称
			if (map.get("axisName") != null && map.get("axisName") != "") {
				sb.append(" and  axisName= '"+map.get("axisName").trim()+"'");
			}
			//工序名称
			if (map.get("seqName") != null && map.get("seqName") != "") {
				sb.append(" and  seqName= '"+map.get("seqName").trim()+"'");
			}
			//测试人
			if (map.get("testBy") != null && map.get("testBy") != "") {
				sb.append(" and  testBy= '"+map.get("testBy").trim()+"'");
			}
			//结果
			if (map.get("testResult") != null && map.get("testResult") != "") {
				sb.append(" and  testResult= '"+map.get("testResult").trim()+"'");
			}
			//结果
			if (map.get("testState") != null && map.get("testState") != "") {
				sb.append(" and  testState= '"+map.get("testState").trim()+"'");
			}
		}
		sb.append("  order by planTestDate DESC");
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @Description: 调用数据库函数   
	 * @param funName
	 * @param val
	 * @return
	 * @throws Exception 
	 */
	public Object getFun(String funName ,Object ...val) throws Exception{
		Object object = dao.exeFunction(funName, val);
		return object;
	}
	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param rfid
	 * @return         
	 */ 
	public QualityProductPlan appGetPlan(String rfid) {
		// TODO Auto-generated method stub
		//在日进度表中 通过 半成品轴号 查询到轴名称
		String sql ="select axis_name from pla_machine_plan_schedule where seq_semi_product_axis ='"+rfid+"' and virtual_rfid is not null order by create_date DESC";
		List list = dao.createSQLQuery(sql).list();
		//轴名称
		String axisName = null;
		if (list != null && list.size() > 0) {
			axisName = (String) list.get(0);
			//通过轴名称去查计划
			String hql ="from QualityProductPlan where axisName = '"+axisName+"'  and type='半成品质检' order by createDate DESC";
			List<QualityProductPlan> planList = dao.createQuery(hql).list();
			if (planList != null  && planList.size() > 0) {
				QualityProductPlan plan = planList.get(0);
				return  plan;
			}
			
		}
			
		return null;
	}
}
