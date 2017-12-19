package com.css.common.util;

import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;

public class ExportExcel {
	
	public static WritableCellFormat getBodyFormat() {
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

	public static WritableCellFormat getTitleFormat() {
		WritableCellFormat format = null;
		try {
			WritableFont font = new WritableFont(WritableFont.createFont("宋体"),
					10, WritableFont.BOLD);
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
	 * 导出excel文件
	 * @param datalist      行数据
	 * @param response      响应
	 * @param titleName     表名称
	 * @param arrTitle      列名称
	 * @throws Exception
	 */
	/*
	public static void exportExecl(HttpServletResponse response,List<String[]> datalist,String titleName,String[] arrTitle){
		
		// 获取表格样式
		WritableCellFormat bodyFormat = getBodyFormat();
		WritableCellFormat titlFormat = getTitleFormat();
		response.reset();
		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
		} catch (IOException e1) {
			System.out.println("导出输出文件流异常");
			e1.printStackTrace();
		}
	
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sf.format(new Date());
		try {
			titleName = new String(titleName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		WritableWorkbook wwb = null;
		try {
			// 创建表
			wwb = Workbook.createWorkbook(outputStream);
			WritableSheet ws = wwb.createSheet(format, 0);
			// 写入标题
			for (int i = 0; i < arrTitle.length; i++) {
				ws.addCell(new Label(i, 0, arrTitle[i], titlFormat));
			}
			//Page pageList = this.getAllStore(map, page);
			if (null != datalist) {
				//List<StatStoreObjVo> data = pageList.getData();
				
				for (int i = 0; i < datalist.size(); i++) {
					//StatStoreObjVo vo = (StatStoreObjVo)datalist.get(i);
					String[] arrTab = datalist.get(i);
					for(int j=0;j<arrTab.length;j++){
						ws.addCell(new Label(j, i+1, null==arrTab[j]?"":arrTab[j], bodyFormat));
					}
					
				}
			}
	
			response.setContentType("application/vnd.ms-excel;charset=GBK");
			response.addHeader("Content-Disposition", "attachment; filename=\""
					+ titleName + ".xls\"");
			wwb.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != wwb) {
				try {
					wwb.close();
				} catch (WriteException | IOException e) {
					System.out.println("关闭导出表异常");
					e.printStackTrace();
				}
			}
			if (null != outputStream) {
	
				try {
					outputStream.close();
				} catch (IOException e) {
					System.out.println("关闭导出表输出流异常");
					e.printStackTrace();
				}
			}
		}
	}
	*/
	
}
