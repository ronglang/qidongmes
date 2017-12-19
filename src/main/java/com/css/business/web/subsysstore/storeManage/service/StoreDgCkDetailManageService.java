package com.css.business.web.subsysstore.storeManage.service;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Service;

import com.css.business.web.subsysstatistics.bean.StatStoreObjVo;
import com.css.business.web.subsysstore.bean.StoreDgCk;
import com.css.business.web.subsysstore.bean.StoreDgCkDetail;
import com.css.business.web.subsysstore.storeManage.bean.StoreDgCkVo;
import com.css.business.web.subsysstore.storeManage.dao.StoreDgCkDetailManageDAO;
import com.css.common.web.syscommon.dao.support.Page;
import com.css.common.web.syscommon.service.impl.BaseEntityManageImpl;

@Service("storeDgCkDetailManageService")
public class StoreDgCkDetailManageService extends BaseEntityManageImpl<StoreDgCkDetail, StoreDgCkDetailManageDAO> 
		 {
	@Resource(name="storeDgCkDetailManageDAO")
	//@Autowired
	private StoreDgCkDetailManageDAO dao;
	
	
	@Override
	public StoreDgCkDetailManageDAO getEntityDaoInf() {
		return dao;
	}
	
	/**
	 * 展示出库单明细详情
	 * @param param
	 * @param outCode
	 * @return
	 */
	public Page getStoreRecordPageList(Page param, String outCode) {
		// TODO Auto-generated method stub
		Page page=null;
		try {
			String hql="select a.id as id,a.outbound_order_code as ordercode,a.obj_ggxh as ggxh,a.amount as amount,"
					+ "a.fact_amount as factamount,a.unit as unit,c.mater_name as matername,b.mmr_code as mmrcode from "
					+ "store_dg_ck_detail a,mau_material_record b,store_mrecord c  "
					+ "where a.pick_dg_id=b.id  and a.material_id=c.id and a.outbound_order_code='"+outCode+"'";
			page=dao.pageSQLQueryVONoneDesc(hql, param.getPageNo(), param.getPagesize(), new StoreDgCkVo());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return page;
	}
	
	
	/**
	 * 得到表格内容格式
	 * 
	 * @return
	 */
	private WritableCellFormat getBodyFomat() {
		WritableCellFormat format = null;
		try {
			WritableFont font = new WritableFont(WritableFont.createFont("宋体"),
					10, WritableFont.NO_BOLD);
			format = new WritableCellFormat(font);
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(jxl.format.Border.TOP,
					jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.LEFT,
					jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.RIGHT,
					jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.BOTTOM,
					jxl.format.BorderLineStyle.THIN);

			format.setWrap(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return format;
	}
	
	private WritableCellFormat getTitleFomat() {

		WritableCellFormat format = null;
		try {
			WritableFont font = new WritableFont(WritableFont.createFont("宋体"),
					13, WritableFont.BOLD);
			format = new WritableCellFormat(font);
			format.setAlignment(jxl.format.Alignment.CENTRE);
			format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			format.setBorder(jxl.format.Border.TOP,
					jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.LEFT,
					jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.RIGHT,
					jxl.format.BorderLineStyle.THIN);
			format.setBorder(jxl.format.Border.BOTTOM,
					jxl.format.BorderLineStyle.THIN);

			format.setWrap(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return format;
	
	}
	/**
	 * 导出
	 * @param response
	 * @param param
	 * @param order
	 * @throws IOException 
	 */
	public void exprotDetailExcel(HttpServletResponse response, Page param,
			String order) throws Exception {
		// 获取表格样式
				WritableCellFormat bodyFomat = this.getBodyFomat();
				WritableCellFormat titleFormat = this.getTitleFomat();
				response.reset();
				OutputStream outputStream = response.getOutputStream();

				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String format = sf.format(new Date());
				String titleName = format + "出库单明细excel表";
				titleName = new String(titleName.getBytes(), "ISO8859-1");
				WritableWorkbook wwb = null;
				try {
					
					wwb = Workbook.createWorkbook(outputStream);
					WritableSheet ws = wwb.createSheet(format, 0);
					String[] arrTitle = new String[] {"出库单号", "领料单号","规格型号","物料名称","数量","实际出库数量",  "单位"};
					// 写入标题
					for (int i = 0; i < arrTitle.length; i++) {
						ws.addCell(new Label(i, 0, arrTitle[i], titleFormat));
					}
			
					Page pageList =this.getStoreRecordPageList(param, order);
					if (null != pageList) {
						List<StoreDgCkVo> data = pageList.getData();
						int x = 1;
						for (int i = 0; i < data.size(); i++) {
							StoreDgCkVo vo = data.get(i);
							ws.addCell(new Label(0, x, null==vo.getOrdercode()?"":vo.getOrdercode(), bodyFomat));
							ws.addCell(new Label(1, x, null==vo.getMmrcode()?"":vo.getMmrcode(), bodyFomat));
							ws.addCell(new Label(2, x, null==vo.getGgxh()?"":vo.getGgxh(), bodyFomat));
							ws.addCell(new Label(3, x, null==vo.getMatername()?"":vo.getMatername(), bodyFomat));
							ws.addCell(new Label(4, x, null==vo.getAmount()?"":vo.getAmount()+"", bodyFomat));
							ws.addCell(new Label(5, x, null==vo.getFactamount()?"":vo.getFactamount()+"", bodyFomat));
							ws.addCell(new Label(6, x, null==vo.getUnit()?"":vo.getUnit(), bodyFomat));
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
/**
 * 根据出库单号、物料规格、物料id去查询出库
 * @param outBoundCode
 * @param objGgxh
 * @param objName
 * @return
 */
	public StoreDgCkDetail queryDetailByCode(String outBoundCode,
			String objGgxh, Integer materialId) {
		// TODO Auto-generated method stub
		List<StoreDgCkDetail> list=new ArrayList<>();
		StoreDgCkDetail obj=null;
		try {
			String hql="from StoreDgCkDetail where outboundOrderCode=? and objGgxh=? and materialId=?";
			list=dao.queryDetailByCode(hql,outBoundCode,objGgxh,materialId);
			if(null!=list&&list.size()>0){
				obj=list.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return obj;
	}


	



	
	
}
