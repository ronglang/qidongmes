package com.css.business.web.subsysquality.quaManage.service;

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
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysquality.bean.QualitySurveyReport;
import com.css.business.web.subsysquality.quaManage.dao.QualitySurveyReportManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("qualitySurveyReportManageService")
public class QualitySurveyReportManageService extends BaseEntityManageImpl<QualitySurveyReport,QualitySurveyReportManageDAO> {
	@Resource(name="qualitySurveyReportManageDAO")
	//@Autowired
	private QualitySurveyReportManageDAO dao;
	
	
	@Override
	public QualitySurveyReportManageDAO getEntityDaoInf() {
		return dao;
	}
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
	 * @Description: 分页查询  
	 * @param map
	 * @param page
	 * @return         
	 */ 
	public Page queryPage(Map<String, String> map, Page page) {
		// TODO Auto-generated method stub
		String hql ="from QualitySurveyReport where 1 = 1 ";
		StringBuilder sb = new StringBuilder(hql);
		String orderSql = " order by surveyDate DESC ";
		if (map != null  && map.size() > 0) {
			if (map.get("start") != null && map.get("start") != "") {
				sb.append(" and surveyDate >='"+map.get("start")+"' ");
			}
			if (map.get("end") != null && map.get("end") != "") {
				sb.append(" and surveyDate <='"+map.get("end")+"' ");
			}
			//物料类型
			if (map.get("objSort") != null && map.get("objSort") != "") {
				sb.append(" and objSort ='"+map.get("objSort")+"' ");
			}
			//质检单号
			if (map.get("surveyReportCode") != null && map.get("surveyReportCode") != "") {
				sb.append(" and surveyReportCode ='"+map.get("surveyReportCode")+"' ");
			}
			//供应商
			if (map.get("supplyAgent") != null && map.get("supplyAgent") != "") {
				sb.append(" and supplyAgent ='"+map.get("supplyAgent")+"' ");
			}
			//物料规格
			if (map.get("objGgxh") != null && map.get("objGgxh") != "") {
				sb.append(" and objGgxh ='"+map.get("objGgxh")+"' ");
			}
			//检验结果:合格,不合格
			if (map.get("reportResult") != null && map.get("reportResult") != "") {
				sb.append(" and reportResult ='"+map.get("reportResult")+"' ");
			}
			
			//是否入库 未入库 ,已入库
			if (map.get("objName") != null && map.get("objName") != "") {
				sb.append(" and objName like'%"+map.get("objName")+"%' ");
			}
			//是否入库 未入库 ,已入库
			if (map.get("isCome") != null && map.get("isCome") != "") {
				sb.append(" and isCome ='"+map.get("isCome")+"' ");
			}
		}
		sb.append(orderSql);
		Page queryPage = page;
		try {
			queryPage = dao.pageQuery(sb.toString(), page.getPageNo(), page.getPagesize());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryPage;
	}
	
	/**
	 * 
	 * @return
	 */
	public void exportMaterialService(Map<String, String> map,Page page,HttpServletResponse response)throws Exception{
		// 获取表格样式
		WritableCellFormat bodyFormat = this.getBodyFormat();
		WritableCellFormat titlFormat = this.getTitalFormat();
		response.reset();
		OutputStream outputStream = response.getOutputStream();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sf.format(new Date());
		String titleName = format +"原材料质检表";
		titleName = new String(titleName.getBytes(), "ISO8859-1");
		WritableWorkbook wwb = null;
//		Map<String, String> maps = new HashMap<>();
		try {
			// 创建表
			wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(format, 0);
			String[] arrTitle = new String[] { "物料类型", "名称", "物料规格型号", "物料颜色","供货商","批次号","数量","单位",
					"出厂检验报告","检验报告单号","检验结果","质检日期","是否入库"};
			// 写入标题
			for (int i = 0; i < arrTitle.length; i++) {
				ws.addCell(new Label(i, 0, arrTitle[i], titlFormat));
			}

			Page pageList = this.queryPage(map, page);
			if (null != pageList) {
				  @SuppressWarnings("unchecked")
				List<QualitySurveyReport> data = pageList.getData();
				int x = 1;
				for (int i = 0; i < data.size(); i++) {
					QualitySurveyReport vo = data.get(i);
					ws.addCell(new Label(0, x, null == vo.getObjSort() ? "" : vo.getObjSort(), bodyFormat));
					ws.addCell(new Label(1, x, null == vo.getObjName() ? "" : vo.getObjName(), bodyFormat));
					ws.addCell(new Label(2, x, null == vo.getObjGgxh() ? "" : vo.getObjGgxh(), bodyFormat));
					ws.addCell(new Label(3, x, null == vo.getObjColor() ? "" : vo.getObjColor(), bodyFormat));
					ws.addCell(new Label(4, x, null == vo.getSupplyAgent() ? "" :vo.getSupplyAgent(), bodyFormat));
					ws.addCell(new Label(5, x, null == vo.getBatchCode() ? "" : vo.getBatchCode(), bodyFormat));
					ws.addCell(new Label(6, x, null == vo.getAcount() ? "" : vo.getAcount().toString(), bodyFormat));
					ws.addCell(new Label(7, x, null == vo.getUnit() ? "" : vo.getUnit(), bodyFormat));
					ws.addCell(new Label(8, x, null == vo.getIsSupplySurey() ? "" : vo.getIsSupplySurey(), bodyFormat));
					ws.addCell(new Label(9, x, null == vo.getSurveyReportCode() ? "" : vo.getSurveyReportCode(), bodyFormat));
					ws.addCell(new Label(10, x, null == vo.getReportResult() ? "" :vo.getReportResult(), bodyFormat));
					ws.addCell(new Label(11, x, null == vo.getSurveyDate() ? "" : vo.getSurveyDate().toString(), bodyFormat));
					ws.addCell(new Label(12, x, null == vo.getIsCome() ? "" : vo.getIsCome(), bodyFormat));
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
		//return maps;
	}

	/**   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param id         
	 */ 
	public void clearReport(Integer id) {
		// TODO Auto-generated method stub
		try {
			dao.removeById(id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @Description: 调用函数   
	 * @param funName
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	public Object getFunction(String funName,Object ...obj) throws Exception{
		
		Object result = dao.exeFunction(funName, obj);
		
		return result;
	}
	
	
	
}
