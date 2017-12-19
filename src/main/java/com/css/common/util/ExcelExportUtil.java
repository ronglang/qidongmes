package com.css.common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelExportUtil {
	
	//生成excel文件
	public static void export(Map<String, String> head, List<Map<String, Object>> dataList, String filePath) throws Exception  {
		// 创建工作簿对象
		HSSFWorkbook hssfWorkBook = new HSSFWorkbook();
		// 创建 sheet 页
		HSSFSheet sheet = hssfWorkBook.createSheet();

		HSSFRow row = null;
		HSSFCell cell = null;
		
		int rownum = 0;
		int j = 0;

		Set<String> keySet = null;
		Iterator<String> keyIter = null;
		Map<String, Object> dataMap = null;
		List<String> headList = null;
		//列名
		if(head != null && head.size() > 0){
			row = sheet.createRow(rownum);
			rownum++;
			keySet = head.keySet();
			keyIter = keySet.iterator();
			headList = new ArrayList<String>();
			
			String key = "";
			while(keyIter.hasNext()){
				key = keyIter.next();
				cell = row.createCell(j);
				cell.setCellValue(String.valueOf(head.get(key)));
				j++;
				headList.add(key);
			}
			
			//填充数据
			for(int i = 0, len = dataList.size(); i < len; i++){
				j = 0;
				row = sheet.createRow(rownum);
				dataMap = dataList.get(i);

				for(String col : headList){
					cell = row.createCell(j);
					if(dataMap.get(col) != null){
						cell.setCellValue(String.valueOf(dataMap.get(col)));
					}
					j++;
				}
				rownum++;
			}
		}
		else{
			Object value = null;
			for(int i = 0, len = dataList.size(); i < len; i++){
				j = 0;
				row = sheet.createRow(rownum);
				dataMap = dataList.get(i);
				keyIter = dataMap.keySet().iterator();
				while(keyIter.hasNext()){
					value = dataMap.get(keyIter.next());
					cell = row.createCell(j);
					if(value != null){
						cell.setCellValue(String.valueOf(value));
					}
					j++;
				}
				rownum++;
			}
		}
		// 输出工作簿
		FileOutputStream fos =  new FileOutputStream(filePath);
		// 将工作簿进行输出
		hssfWorkBook.write(fos);
		// 关闭输出流
		fos.close();
	}
	
	//生成并下载excel文件
/*	public static void export(Map<String, String> head, List<Map<String, Object>> dataList, String filePath, HttpServletResponse response) throws Exception {
		ExcelExportUtil.export(head, dataList, filePath);
		//文件下载
		response.setContentType("application/msexcel;CHARSET=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=exportExcel.xls");
		File file = new File(filePath);  
		try {  
            FileInputStream inputStream = new FileInputStream(file);  
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());

            byte[] buffer = new byte[inputStream.available()];  
            int length = 0;
            while ((length = inputStream.read(buffer)) != -1) {
            	out.write(buffer, 0, length);
            }
             
            inputStream.close();  
            out.close();  
            out.flush();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
		
		if(file.exists()){
			file.delete();
		}
	}*/

	
	//生成并下载excel文件
	public static void export(Map<String, String> head, List<Map<String, Object>> dataList, String filePath,String filename, HttpServletResponse response) throws Exception {
		ExcelExportUtil.export(head, dataList, filePath);
		//文件下载
		response.setContentType("application/msexcel;CHARSET=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename="+filename+".xls");
		//response.setContentType("octets/stream");  
		response.setCharacterEncoding("GB2312");
		File file = new File(filePath);  
		try {  
            FileInputStream inputStream = new FileInputStream(file);  
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());

            byte[] buffer = new byte[inputStream.available()];  
            int length = 0;
            while ((length = inputStream.read(buffer)) != -1) {
            	out.write(buffer, 0, length);
            }
             
            inputStream.close();  
            out.close();  
            out.flush();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
		
		if(file.exists()){
			file.delete();
		}
	}
}
