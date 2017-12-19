package com.css.common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

import com.css.common.web.syscommon.bean.ExcelGridVo;

/**
 * @TODO  : 
 * @author: 翟春磊
 * @DATE  : 2016年11月4日
 */
public class ExcelExportUtilZcl {
	private static ExcelExportUtilZcl instance;
	
	/*static{
		if(instance == null)
			instance = new ExcelExportUtilZcl();
	}*/
	
	private  ExcelExportUtilZcl(){}
	
	public static ExcelExportUtilZcl getInstance() {
		if(instance == null)
			instance = new ExcelExportUtilZcl();
		return instance;
	}

	//生成excel文件
	private  void export_poi(Map<String, Object> head, List<Map<String, Object>> dataList, String filePath) throws Exception  {
		//poi方式
		// 创建工作簿对象
		HSSFWorkbook hssfWorkBook = new HSSFWorkbook();
		// 创建 sheet 页
		HSSFSheet sheet = hssfWorkBook.createSheet();
		
		HSSFRow row = null;
		HSSFCell cell = null;
		
		int rownum = 0;
		int j = 0;
		
		//Set<String> keySet = null;
		Iterator<String> keyIter = null;
		Map<String, Object> dataMap = null;
		//List<String> headList = null;
		
		//创建表头
		wrapperHeaderCreateCell(sheet,head,0,0);
		
		//列名.head是分级包含合并单元格的map
		if(head != null && head.size() > 0){
			/*row = sheet.createRow(rownum);
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
			}*/
		}
		
		/*//列名
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
		}*/
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
	
	/**
	 * @TODO: jxl方式写excel
	 * @author: zhaichunlei
	 & @DATE : 2016年11月10日
	 * @param head
	 * @param dataList
	 * @param filePath
	 * @throws Exception
	 */
	private  void export_jxl(Map<String, Object> head, List<Map<String, Object>> dataList, String filePath) throws Exception  {
		// 输出工作簿
		FileOutputStream fos =  new FileOutputStream(filePath);
		jxl.write.WritableWorkbook wwb = jxl.Workbook.createWorkbook(fos);
		jxl.write.WritableSheet ws = wwb.createSheet("sheet",0); 
		//创建表头
		wrapperHeaderCreateCell_jxl(ws,head,0,0);
		wwb.write();
		wwb.close();
		fos.flush();
		// 关闭输出流
		fos.close();
	}

	//生成并下载excel文件
	public  void export_poi(Map<String, Object> head, List<Map<String, Object>> dataList, String filePath,String filename, HttpServletResponse response) throws Exception {
		getInstance().export_poi(head, dataList, filePath);
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
	
	//生成并下载excel文件
	public  void export_jxl(Map<String, Object> head, List<Map<String, Object>> dataList, String filePath,String filename, HttpServletResponse response) throws Exception {
		getInstance().export_jxl(head, dataList, filePath);
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
	
	/**
	 * @TODO:根据前台提交的grid头的参数，包装为excel头结构。支持多表头
	 * @author: zhaichunlei
	 & @DATE : 2016年10月31日
	 * @param json
	 * @return
	 */
	public void wrapperExcelHeader(Map<String, Object> hm,JSONArray json){
		List<ExcelGridVo> lst = new ArrayList<ExcelGridVo>(); 
		ExcelExportUtilZcl.getInstance().wrapperHeaderForChild(json,lst,null);//构建父子结构 
		
		ExcelExportUtilZcl.getInstance().wrapperHeaderForRegionColumn(lst);//构建合并单元格列层次
		//根节点的父级总是-1.rownum从1开始
		ExcelExportUtilZcl.getInstance().wrapperHeaderForRegionSetRowNum(lst,"-1",1);
		
		ExcelExportUtilZcl.getInstance().wrapperHeaderForRegionMap(lst,hm);
		System.out.println("122:" + hm);
	}
	
	//构建父子关系
	private  void wrapperHeaderForChild(JSONArray json,List<ExcelGridVo> lst,ExcelGridVo v){
		if(v == null){
			for(int i = 0 ; i < json.size(); i ++){
				ExcelGridVo f = (ExcelGridVo)(JSONObject.toBean((JSONObject)json.get(i), ExcelGridVo.class));
				if("-1".equals(f.getPid())){
					f.setIschild(false);
					lst.add(f);
				}
			}
			for(ExcelGridVo f : lst){
				wrapperHeaderForChild(json,lst,f);//找子id
			}
		}
		else{
			for(int i = 0 ; i < json.size(); i ++){
				ExcelGridVo f = (ExcelGridVo)(JSONObject.toBean((JSONObject)json.get(i), ExcelGridVo.class));
				/*if(v.getId().equalsIgnoreCase("c104"))//f.getPid().equalsIgnoreCase("c104") && 
					System.out.println("1111111111");*/
				
				if( f.getPid().equalsIgnoreCase(v.getId()) ){
					
					v.getColchild().add(f);
					f.setIschild(true);
					
					/*if(f.getColchild().size() > 0){
						wrapperHeaderForChild(json,lst,f);//找子id
					}*/
					wrapperHeaderForChild(json,lst,f);//找子id
				}
			}
			
			/*for(int i = 0 ; i < json.size(); i ++){
				ExcelGridVo f = (ExcelGridVo)(JSONObject.toBean((JSONObject)json.get(i), ExcelGridVo.class));
				
				if( f.getId().equals(v.getPid()) ){
					int j = lst.indexOf(f) ;
					//从lst中取值
					if(lst.indexOf(f) != -1)
						f = lst.get(j);
					
					if( "-1".equals(f.getPid())){
						if(!f.getColchild().contains(v))
							f.getColchild().add(v);
						v.setIschild(true);
						f.setIschild(false);
						if(!lst.contains(f)){
							lst.add(f);
						}
						else{
							lst.remove(f);
							lst.add(f);
						}
					}
					else if(  !"-1".equals(f.getPid())){
						if(!f.getColchild().contains(v))
							f.getColchild().add(v);
						v.setIschild(true);
						f.setIschild(true);
						wrapperHeaderForChild(json,lst,f);//找父id
					}
					break;
				}
			}*/
		}
	}
	
	//层合并的级数colspan:合并列数
	private  void wrapperHeaderForRegionColumn(List<ExcelGridVo> lst){ //Object json){
		//设置合并列数
		for(ExcelGridVo f : lst){
			if(f.getColchild().size() > 0 ){
				f.setColspan(f.getColchild().size());
				wrapperHeaderForRegionColumn(f.getColchild());
			}
		}
		/*if(json instanceof JSONArray){
			for(int i = 0 ; i < ((JSONArray)json).size(); i ++){
				ExcelGridVo f = (ExcelGridVo)(JSONObject.toBean((JSONObject)((JSONArray)json).get(i), ExcelGridVo.class));
				if(f.getColchild().size() > 0 ){
					f.setColspan(f.getColchild().size());
					wrapperHeaderForRegionColumn(f.getColchild());
				}
			}
		}
		else if(json instanceof List){
			for(int i = 0 ; i < ((List<ExcelGridVo>)json).size(); i ++){
				ExcelGridVo f = ((List<ExcelGridVo>)json).get(i);
				if(f.getColchild().size() > 0 ){
					f.setColspan(f.getColchild().size());
					wrapperHeaderForRegionColumn(f.getColchild());
				}
			}
		}*/
	}
	
	//递归设置行序号rownum
	private  void wrapperHeaderForRegionSetRowNum(List<ExcelGridVo> lst,String pid,Integer rownum){ //Object json,
		for(ExcelGridVo f : lst){
			if(pid.equals(f.getPid())){
				f.setRownum(rownum);
				List<ExcelGridVo> clst = f.getColchild();
				if(clst.size()  > 0)
					wrapperHeaderForRegionSetRowNum(clst,f.getId(),rownum+1);
			}
		}
		/*if(json instanceof JSONArray){
			for(int i = 0 ; i < ((JSONArray)json).size(); i ++){
				ExcelGridVo f = (ExcelGridVo)(JSONObject.toBean((JSONObject)((JSONArray)json).get(i), ExcelGridVo.class));
				if(pid.equals(f.getPid())){
					f.setRownum(rownum);
					List<ExcelGridVo> clst = f.getColchild();
					if(clst.size()  > 0)
						wrapperHeaderForRegionSetRowNum(clst,f.getId(),rownum+1);
				}
			}
		}
		else if(json instanceof List){
			for(int i = 0 ; i < ((List)json).size(); i ++){
				ExcelGridVo f = ((List<ExcelGridVo>)json).get(i);
				if(pid.equals(f.getPid())){
					f.setRownum(rownum);
					List<ExcelGridVo> clst = f.getColchild();
					if(clst.size()  > 0)
						wrapperHeaderForRegionSetRowNum(clst,f.getId(),rownum+1);
				}
			}
		}*/
	}
	
	//根据json封装headermap
	private  void wrapperHeaderForRegionMap(List<ExcelGridVo> lst,Map<String,Object> hm){//Object json
		for(ExcelGridVo f : lst){
			String display = f.getDisplay();
			String cn = f.getColumnname();
			//columnname 可能有undifine,重复出现时，再看怎么定名
			if(f.getColchild().size() > 0){
				Map<String, Object> cmap = new HashMap<String, Object>();
				hm.put(display, cmap);
				wrapperHeaderForRegionMap(f.getColchild(),cmap);
			}
			else{
				hm.put(cn,display);
			}	
		}
		/*if(json instanceof JSONArray){
			for(int i = 0 ; i < ((JSONArray)json).size(); i ++){
				ExcelGridVo f = (ExcelGridVo)(JSONObject.toBean((JSONObject)((JSONArray)json).get(i), ExcelGridVo.class));
				String display = f.getDisplay();
				String cn = f.getColumnname();
				if(f.getColchild().size() > 0){
					Map<String, Object> cmap = new HashMap<String, Object>();
					hm.put(cn, cmap);
					wrapperHeaderForRegionMap(f.getColchild(),cmap);
				}
				else{
					hm.put(cn,display);
				}	
			}
		}
		else if(json instanceof List){
			for(int i = 0 ; i < ((List<?>)json).size(); i ++){
				@SuppressWarnings("unchecked")
				ExcelGridVo f = ((List<ExcelGridVo>)json).get(i);
				String display = f.getDisplay();
				String cn = f.getColumnname();
				if(f.getColchild().size() > 0){
					Map<String, Object> cmap = new HashMap<String, Object>();
					hm.put(cn, cmap);
					wrapperHeaderForRegionMap(f.getColchild(),cmap);
				}
				else{
					hm.put(cn,display);
				}	
			}
		}*/
	}
	
	/**
	 * @TODO: 创建表头
	 * @author: zhaichunlei
	 & @DATE : 2016年11月8日
	 * @param sheet
	 * @param head
	 */
	@SuppressWarnings("unchecked")
	private  void wrapperHeaderCreateCell(HSSFSheet sheet,Map<String,Object> head,final Integer rownum,final Integer col){
		//int rownum = 0;
		int j = col;
		
		Set<String> keySet = null;
		Iterator<String> keyIter = null;
		//Map<String, Object> dataMap = null;
		//List<String> headList = null;
		
		HSSFRow row = null;
		HSSFCell cell = null;
		
		HSSFFont font = sheet.getWorkbook().createFont();  
        font.setFontHeightInPoints((short) 10);  
        font.setFontName("新宋体");  
        font.setColor(HSSFColor.BLUE.index);  
        font.setBoldweight((short) 0.8);  
        //2.生成样式对象  
        HSSFCellStyle style = sheet.getWorkbook().createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        style.setFont(font); //调用字体样式对象  
        style.setWrapText(true);  
        //增加表格边框的样式 例子  
        style.setBorderTop(HSSFCellStyle.BORDER_DOUBLE);  
        style.setBorderLeft(HSSFCellStyle.BORDER_DOUBLE);  
        style.setTopBorderColor(HSSFColor.GOLD.index);  
        style.setLeftBorderColor(HSSFColor.PLUM.index);
		
		//列名.head是分级包含合并单元格的map
		if(head != null && head.size() > 0){
			row = sheet.createRow(rownum);
			keySet = head.keySet();
			keyIter = keySet.iterator();
			//headList = new ArrayList<String>();
			
			String key = "";
			while(keyIter.hasNext()){
				key = keyIter.next();
				
				Object o = head.get(key);
				//未发生合并
				if(o instanceof String){
					cell = row.createCell(j);
					cell.setCellValue(String.valueOf(head.get(key)));
					cell.setCellStyle(style);
					j ++;
				}
				else if(o instanceof Map){
					int k = wrapperHeaderFetchRegionWidth((Map<String,Object>)o) ;
					for(int x = j ; x < k + j ; x ++){
						cell = row.createCell(x);
						/*if("驻村工作组".equals(key)){
							key = "af";
						}*/
						cell.setCellValue(key);
						cell.setCellStyle(style);
					}
					sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(),row.getRowNum(),j,k+j));
					int y = j;
					wrapperHeaderCreateCell(sheet,(Map<String,Object>)o,rownum + 1, y);
					j += k;
				}
			}
		}
	}
	@SuppressWarnings("unchecked")
	private  void wrapperHeaderCreateCell_jxl(WritableSheet sheet,Map<String,Object> head,final Integer rownum,final Integer col) throws RowsExceededException, WriteException{
		//int rownum = 0;
		int j = col;
		
		Set<String> keySet = null;
		Iterator<String> keyIter = null;
		//Map<String, Object> dataMap = null;
		//List<String> headList = null;
		
		
		//设置写入字体  
        jxl.write.WritableFont wf = new jxl.write.WritableFont(jxl.write.WritableFont.ARIAL, 11,jxl.write.WritableFont.BOLD, false);
        //设置CellFormat
        jxl.write.WritableCellFormat wcfF = new jxl.write.WritableCellFormat(wf);
     // 把垂直对齐方式指定为居中对齐
        wcfF.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
        wcfF.setAlignment(jxl.format.Alignment.CENTRE);
		
		
		//列名.head是分级包含合并单元格的map
		if(head != null && head.size() > 0){
			keySet = head.keySet();
			keyIter = keySet.iterator();
			//headList = new ArrayList<String>();
			
			String key = "";
			int h = wrapperHeaderFetchRegionRows(head);
			while(keyIter.hasNext()){
				key = keyIter.next();
				
				Object o = head.get(key);
				//未发生合并
				if(o instanceof String){
					jxl.write.Label lab = new jxl.write.Label(j, rownum, o.toString(),wcfF);
					sheet.addCell(lab);
					
					if(h > 1){
						sheet.mergeCells(j, rownum, j , rownum + h - 1);
					}
					j ++;
				}
				else if(o instanceof Map){
					int k = wrapperHeaderFetchRegionWidth((Map<String,Object>)o) ;
					for(int x = j ; x < k + j ; x ++){
						jxl.write.Label lab = new jxl.write.Label(x, rownum, key,wcfF);
						sheet.addCell(lab);
					}
					sheet.mergeCells(j, rownum, j+k-1, rownum);
					int y = j;
					wrapperHeaderCreateCell_jxl(sheet,(Map<String,Object>)o,rownum + 1, y);
					j += k;
				}
			}
		}
	}

	//计算合并单元格父格占用的列数
	@SuppressWarnings("unchecked")
	private int wrapperHeaderFetchRegionWidth(Map<String,Object> h){
		int c = 0;
		for(Object o : h.values()){
			if(o instanceof String){
				c ++;
			}
			else if(o instanceof Map){
				int x = wrapperHeaderFetchRegionWidth((Map<String,Object>)o);
				c = c + x;
			}
		}
		return c;
	}
	
	//计算合并单元格父格占用的行数
	@SuppressWarnings("unchecked")
	private int wrapperHeaderFetchRegionRows(Map<String,Object> h){
		int c = 0;
		boolean isFoot = false;
		for(Object o : h.values()){
			 if(o instanceof Map){
				isFoot = true;
				int x = wrapperHeaderFetchRegionWidth((Map<String,Object>)o);
				c = c + x;
				break;
			}
		}
		if(! isFoot){
			c ++;
		}
		return c;
	}
}
