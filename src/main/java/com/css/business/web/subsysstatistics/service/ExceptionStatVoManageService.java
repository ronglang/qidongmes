package com.css.business.web.subsysstatistics.service;

import java.io.IOException;
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
import jxl.write.WriteException;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysstatistics.bean.ExceptionStatVo;
import com.css.business.web.subsysstatistics.bean.StatCourse;
import com.css.business.web.subsysstatistics.dao.ExceptionStatVoManageDAO;
import com.css.common.util.ExportExcel;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("exceptionStatVoManageService")
public class ExceptionStatVoManageService extends
		BaseEntityManageImpl<StatCourse, ExceptionStatVoManageDAO> {

	@Resource(name = "exceptionStatVoManageDAO")
	// @Autowired
	private ExceptionStatVoManageDAO dao;

	@Override
	public ExceptionStatVoManageDAO getEntityDaoInf() {
		return dao;
	}

	/**
	 * 
	 * @Description: 查询出所有的异常  
	 * @param map
	 * @param page
	 * @return
	 */
	public Page getExceptionVoPage(Map<String, String> map, Page page) {
		String sql ="SELECT "
				+ "me.course_code  course_code/** 工单编号 */,"
				+ "me.mac_code mac_code/** 机器编号 */,"
				+ "mm.mac_name  mac_name/** 机器名称 */,"
				+ "me.seq_code  seq_code/** 工序编码 */,"
				+ "cr.seq_name seq_name/** 工序名称 */,"
				+ "me.axis_name axis_name/** 异常轴名称 */,"
				+ "sp.para_value codename/** 异常名称 */,"
				+ "me.me_info me_info/**异常信息*/,"
				+ "me_time  me_time/** 异常时间 */,"
				+ "me.exception_param exception_param/**  异常参数 */,"
				+ "me.exception_value exception_value/** 异常值 */,"
				+ "me.agent_by  agent_by/** 操作人 */,"
				+ "me.state state/** 异常状态  */,"
				+ "me.remark remark/** 备注 */ "
				+ "FROM mau_exception me"
				+ " LEFT JOIN cra_seq cr ON me.seq_code = cr.seq_code "
				+ " LEFT JOIN mau_machine mm ON me.mac_code = mm.mac_code "
				+ " LEFT JOIN sys_parameter sp ON me.code = sp.para_name "
				+ " where 1 = 1 ";
		StringBuilder sb = new StringBuilder(sql);
		if (map.get("start") != null && map.get("start")  != "") {
			sb.append("  and me_time >='"+map.get("start")+"' ");
		}
		if (map.get("end") != null && map.get("end")  != "") {
			sb.append("  and me_time <='"+map.get("end")+"' ");
		}
		if (map.get("mac_name") != null && map.get("mac_name")  != "") {
			sb.append("  and mm.mac_name ='"+map.get("mac_name")+"' ");
		}
		if (map.get("codename") != null && map.get("codename")  != "") {
			sb.append("  and sp.para_value ='"+map.get("codename")+"' ");
		}
		if (map.get("seq_name") != null && map.get("seq_name")  != "") {
			sb.append("  and cr.seq_name ='"+map.get("seq_name")+"' ");
		}
		if (map.get("state") != null && map.get("state")  != "") {
			sb.append("  and me.state ='"+map.get("state")+"' ");
		}
		if (map.get("axis_name") != null && map.get("axis_name")  != "") {
			sb.append("  and me.axis_name ='"+map.get("axis_name")+"' ");
		}
		sb.append("order by me.state,me.create_date DESC");
		Page queryPage = page;
		try {
			 queryPage = dao.pageSQLQueryVONoneDesc(sb.toString(), page.getPageNo(), page.getPagesize(), new ExceptionStatVo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryPage;
	}
	
	
	//导出excel
	public void exportExceptionListService(HttpServletResponse response,Page page, Map<String, String> map) throws WriteException, IOException {
		
		// 获取表格样式
		WritableCellFormat bodyFomat = ExportExcel.getBodyFormat();
		WritableCellFormat titleFomat = ExportExcel.getTitleFormat();
		response.reset();
		OutputStream outputStream = response.getOutputStream();

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sf.format(new Date());
		String titleName = "异常统计报表excel表".concat(format);
		titleName = new String(titleName.getBytes(), "ISO8859-1");
		WritableWorkbook wwb = null;
		try {
			// 创建表
			wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(format, 0);
			String[] arrTitle = new String[] { "机器名称","异常名称", "异常信息", "异常时间", "工序名称",
					"工单编码", "异常轴名称","异常参数","异常值","操作人","状态","备注"};
			// 写入标题
			for (int i = 0; i < arrTitle.length; i++) {
				ws.addCell(new Label(i, 0, arrTitle[i], titleFomat));
			}

			Page pageList = this.getExceptionVoPage(map,page);
			if (null != pageList) {
				@SuppressWarnings("unchecked")
				List<ExceptionStatVo> data = pageList.getData();
				int x = 1;
				for (int i = 0; i < data.size(); i++) {
					ExceptionStatVo vo = data.get(i);
					ws.addCell(new Label(0, x, null==vo.getMac_name()?"":vo.getMac_name(), bodyFomat));
					ws.addCell(new Label(1, x, null==vo.getCodename()?"":vo.getCodename(), bodyFomat));
					ws.addCell(new Label(2, x, null==vo.getMe_info()?"":vo.getMe_info(), bodyFomat));
					ws.addCell(new Label(3, x, null==vo.getMe_time()?"":vo.getMe_time().toString(), bodyFomat));
					ws.addCell(new Label(4, x, null==vo.getSeq_name()?"":vo.getSeq_name(), bodyFomat));
					ws.addCell(new Label(5, x, null==vo.getCourse_code()?"":vo.getCourse_code(), bodyFomat));
					ws.addCell(new Label(6, x, null==vo.getAxis_name()?"":vo.getAxis_name(), bodyFomat));
					ws.addCell(new Label(7, x, null==vo.getException_param()?"":vo.getException_param(), bodyFomat));
					ws.addCell(new Label(8, x, null==vo.getException_value()?"":vo.getException_value(), bodyFomat));
					ws.addCell(new Label(9, x, null==vo.getAgent_by()?"":vo.getAgent_by(), bodyFomat));
					ws.addCell(new Label(10, x, null==vo.getState()?"":vo.getState(), bodyFomat));
					ws.addCell(new Label(11, x, null==vo.getRemark()?"":vo.getRemark(), bodyFomat));
					x++;
				}
			}

			response.setContentType("application/vnd.ms-excel;charset=GBK");
			response.addHeader("Content-Disposition", "attachment; filename=\""+ titleName + ".xls\"");
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
