package com.css.business.web.subsysstatistics.service;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysstatistics.bean.ProductStatReportVo;
import com.css.business.web.subsysstatistics.bean.StatCourse;
import com.css.business.web.subsysstatistics.dao.ProductStatReportVoManageDAO;
import com.css.common.util.ExportExcel;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("productStatReportVoManageService")
public class ProductStatReportVoManageService extends
		BaseEntityManageImpl<StatCourse, ProductStatReportVoManageDAO> {

	@Resource(name = "productStatReportVoManageDAO")
	// @Autowired
	private ProductStatReportVoManageDAO dao;

	@Override
	public ProductStatReportVoManageDAO getEntityDaoInf() {
		return dao;
	}

	/**
	 * @Description:   
	 * @param map
	 * @param page
	 * @return
	 */
	public Page getReportPage(Map<String,String> map,Page page){
		String sql ="SELECT "
				+ " mp.course_code course_code/** 工单编号 */,"
				+ " mp.mac_code mac_code/** 机台code */,"
				+ " mm.mac_name mac_name/** 机台名称 */,"
				+ " cs.seq_name seq_name/** 工序名称 */,"
				+ " mp.axis_name axis_name/** 轴名称 */,"
				+ " pm.ggxh mater_type/** 物料规格名称 */,"
				+ " pc.head_ggxh head_ggxh/** 产品规格 */,"
				+ " pc.color color/** 工单颜色 */,"
				+ " mp.part_len part_len /** 计划长度 */,"
				+ " ps.semi_product_len semi_product_len/** 半成品长度 */,"
				+ " mp.plan_start_time plan_start_time/** 计划开始时间 */,"
				+ " mp.fact_start_time fact_start_time/** 实际开始时间 */,"
				+ " mp.plan_end_time plan_end_time/** 计划结束时间 */,"
				+ " mp.fact_end_time fact_end_time/** 实际结束时间 */,"
				+ " ps.plan_incoming_time plan_incoming_time/** 计划来料时间 */,"
				+ " ps.fact_incoming_time fact_incoming_time/** 实际来料时间 */,"
				+ " mp.product_state product_state/** 生产状态 */ "
				+ "FROM "
				+ " pla_machine_plan mp "
				+ " LEFT JOIN "
				+ " pla_machine_plan_schedule ps ON mp.\"id\" = ps.machine_plan_id "
				+ " LEFT JOIN pla_machine_plan_mater pm ON pm.machine_plan_id = mp.\"id\" "
				+ " LEFT JOIN cra_seq cs ON cs.seq_code = mp.seq_code "
				+ " LEFT JOIN mau_machine mm ON mm.id = mp.machine_id,"
				+ " pla_course pc"
				+ " WHERE "
				+ " pc.ws_code = mp.course_code ";
		StringBuilder sb = new StringBuilder(sql);
		if (map.get("start") != null && map.get("start")  != "") {
			sb.append("  and mp.fact_start_time  >='"+map.get("start").trim()+"' ");
		}
		if (map.get("end") != null && map.get("end")  != "") {
			sb.append("  and mp.fact_start_time  <='"+map.get("end").trim()+"' ");
		}
		if (map.get("course_code") != null && map.get("course_code")  != "") {
			sb.append("  and mp.course_code lisk '%"+map.get("course_code").trim()+"%' ");
		}
		if (map.get("mac_name") != null && map.get("mac_name")  != "") {
			sb.append("  and mm.mac_name like '%"+map.get("mac_name").trim()+"%' ");
		}
		if (map.get("head_ggxh") != null && map.get("head_ggxh")  != "") {
			sb.append("  and pc.head_ggxh like '%"+map.get("head_ggxh").trim()+"%' ");
		}
		if (map.get("product_state") != null && map.get("product_state")  != "") {
			sb.append("  and mp.product_state ='"+map.get("product_state").trim()+"' ");
		}
		sb.append("group by "
				+ "mp.course_code,mp.mac_code,	"
				+ "mm.mac_name,cs.seq_name,ps.axis_name,"
				+ "pm.ggxh,pc.color,mp.part_len,ps.semi_product_len,"
				+ "mp.plan_start_time,mp.fact_start_time,"
				+ "mp.plan_end_time,mp.fact_end_time,"
				+ "ps.plan_incoming_time,ps.fact_incoming_time,"
				+ "mp.product_state,pc.head_ggxh,pc.create_date,mp.axis_name "
				+ " ORDER BY "
//				+ "mp.course_code DESC,pc.create_date DESC,mp.axis_name DESC,mp.plan_start_time DESC");
		+ " mp.plan_start_time DESC");
		Page queryPage = page;
		try {
			queryPage = dao.pageSQLQueryVONoneDesc(sb.toString(), page.getPageNo(), page.getPagesize(), new ProductStatReportVo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryPage;
		
	}

	//导出
	public void exportProductStatService(HttpServletResponse response,Page page, Map<String, String> map) throws Exception {

		// 获取表格样式
		WritableCellFormat bodyFomat = ExportExcel.getBodyFormat();
		WritableCellFormat titleFomat = ExportExcel.getTitleFormat();
		response.reset();
		OutputStream outputStream = response.getOutputStream();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sf.format(new Date());
		String titleName = "生产统计报表excel表".concat(format);
		titleName = new String(titleName.getBytes(), "ISO8859-1");
		WritableWorkbook wwb = null;
		try {
			// 创建表
			wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(format, 0);
			String[] arrTitle = new String[] { "工单编号","机台名称", "工序名称", "轴名称", "产品规格",
					"生产状态", "完成率","原料类型","原料规格型号","计划长度","半成品长度","计划开始时间","实际开始时间","计划结束时间",
					"实际结束时间","计划来料时间","实际来料时间","工作时间","颜色"};
			// 写入标题
			for (int i = 0; i < arrTitle.length; i++) {
				ws.addCell(new Label(i, 0, arrTitle[i], titleFomat));
			}

			Page pageList = this.getReportPage(map,page);
			if (null != pageList) {
				@SuppressWarnings("unchecked")
				List<ProductStatReportVo> data = pageList.getData();
				int x = 1;
				for (int i = 0; i < data.size(); i++) {
					ProductStatReportVo vo = data.get(i);
					ws.addCell(new Label(0, x, null==vo.getCourse_code()?"":vo.getCourse_code(), bodyFomat));
					ws.addCell(new Label(1, x, null==vo.getMac_name()?"":vo.getMac_name(), bodyFomat));
					ws.addCell(new Label(2, x, null==vo.getSeq_name()?"":vo.getSeq_name(), bodyFomat));
					ws.addCell(new Label(3, x, null==vo.getAxis_name()?"":vo.getAxis_name(), bodyFomat));
					ws.addCell(new Label(4, x, null==vo.getHead_ggxh()?"":vo.getHead_ggxh(), bodyFomat));
					ws.addCell(new Label(5, x, null==vo.getProduct_state()?"":vo.getProduct_state(), bodyFomat));
					ws.addCell(new Label(6, x, null==vo.getComp_rate()?"":vo.getComp_rate().toString(), bodyFomat));
					ws.addCell(new Label(7, x, null==vo.getMater_type()?"":vo.getMater_type(), bodyFomat));
					ws.addCell(new Label(8, x, null==vo.getMater_ggxh()?"":vo.getMater_ggxh(), bodyFomat));
					ws.addCell(new Label(9, x, null==vo.getPart_len()?"":vo.getPart_len(), bodyFomat));
					ws.addCell(new Label(10, x, null==vo.getSemi_product_len()?"":vo.getSemi_product_len().toString(), bodyFomat));
					ws.addCell(new Label(11, x, null==vo.getPlan_start_time()?"":vo.getPlan_start_time().toString(), bodyFomat));
					ws.addCell(new Label(12, x, null==vo.getFact_start_time()?"":vo.getFact_start_time().toString(), bodyFomat));
					ws.addCell(new Label(13, x, null==vo.getPlan_end_time()?"":vo.getPlan_end_time().toString(), bodyFomat));
					ws.addCell(new Label(14, x, null==vo.getFact_end_time()?"":vo.getFact_end_time().toString(), bodyFomat));
					ws.addCell(new Label(15, x, null==vo.getPlan_incoming_time()?"":vo.getPlan_incoming_time().toString(), bodyFomat));
					ws.addCell(new Label(16, x, null==vo.getFact_incoming_time()?"":vo.getFact_incoming_time().toString(), bodyFomat));
					ws.addCell(new Label(17, x, null==vo.getWork_date()?"":vo.getWork_date(), bodyFomat));
					ws.addCell(new Label(18, x, null==vo.getColor()?"":vo.getColor(), bodyFomat));
					x++;
				}
			}

			response.setContentType("application/vnd.ms-excel;charset=GBK");
			response.addHeader("Content-Disposition", "attachment; filename=\""
					+ titleName + ".xls\"");
			wwb.write();
		} catch (Exception e) {
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
	
	
	
}
