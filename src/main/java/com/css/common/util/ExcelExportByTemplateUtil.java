package com.css.common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelExportByTemplateUtil {

	 //模板map
    private Map<String,Workbook> tempWorkbook = new HashMap<String, Workbook>();
    //模板输入流map
    private Map<String,FileInputStream> tempStream = new HashMap<String, FileInputStream>();
     
    /**
     * 
     * <p class="detail">
     * 描述：临时单元格数据
     * </p>
     * @ClassName: Cell
     * @version V1.0  
     * @date 2015年9月26日 
     * @author <a href="mailto:1435290472@qq.com">zq</a>
     */
    class TempCell{
        private int row;
        private int column;
        private CellStyle cellStyle;
        private Object data;
        //用于列表合并，表示几列合并
        private int columnSize = -1;
 
        public int getColumn() {
            return column;
        }
        public void setColumn(int column) {
            this.column = column;
        }
        public int getRow() {
            return row;
        }
        public void setRow(int row) {
            this.row = row;
        }
        public CellStyle getCellStyle() {
            return cellStyle;
        }
        public void setCellStyle(CellStyle cellStyle) {
            this.cellStyle = cellStyle;
        }
        public Object getData() {
            return data;
        }
        public void setData(Object data) {
            this.data = data;
        }
        public int getColumnSize() {
            return columnSize;
        }
        public void setColumnSize(int columnSize) {
            this.columnSize = columnSize;
        }
    }
     
    /**
     * 
     * <p class="detail">
     * 功能：按模板向Excel中相应地方填充数据
     * </p>
     * @date 2015年9月26日
     * @author <a href="mailto:1435290472@qq.com">zq</a>
     * @param tempFilePath
     * @param dataMap
     * @param sheetNo
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
	public void writeData(String tempFilePath,Map<String,Object> dataMap,int sheetNo) throws IOException{
        //获取模板填充格式位置等数据
    //    HashMap tem = getTemp(tempFilePath,sheet);
        //读取模板
        Workbook wbModule = getTempWorkbook(tempFilePath);
        //数据填充的sheet
        Sheet wsheet = wbModule.getSheetAt(sheetNo);
         
        Iterator it = dataMap.entrySet().iterator();
        while(it.hasNext()){
            @SuppressWarnings("unchecked")
			Entry<String, Object> entry = (Entry<String, Object>) it.next();
            String point = entry.getKey();
            Object data = entry.getValue();
             
            TempCell cell = getCell(point, data,wsheet);
            //指定坐标赋值
            setCell(cell,wsheet);
        }
        //设置生成excel中公式自动计算
        wsheet.setForceFormulaRecalculation(true);
    }
     
    /**
     * 
     * <p class="detail">
     * 功能：按模板向Excel中列表填充数据。    只支持列合并
     * </p>
     * @date 2015年9月27日
     * @author <a href="mailto:1435290472@qq.com">zq</a>
     * @param tempFilePath
     * @param heads   列表头部位置集合
     * @param datalist
     * @param sheetNo
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void writeDateList(String tempFilePath,String[] heads,List<Map<Integer, Object>> datalist,int sheetNo) throws FileNotFoundException, IOException {
        //读取模板
        Workbook wbModule = getTempWorkbook(tempFilePath);
        //数据填充的sheet
        Sheet wsheet = wbModule.getSheetAt(sheetNo);
        //列表数据模板cell
        List<TempCell> tempCells = new ArrayList<TempCell>();
        for(int i=0;i<heads.length;i++){
            String point = heads[i];
            TempCell tempCell = getCell(point,null,wsheet);
            //取得合并单元格位置  -1：表示不是合并单元格
            int pos = isMergedRegion(wsheet, tempCell.getRow(), tempCell.getColumn());
            if(pos>-1){
                CellRangeAddress range = wsheet.getMergedRegion(pos);
                tempCell.setColumnSize(range.getLastColumn()-range.getFirstColumn());
            }
            tempCells.add(tempCell);
        }
        //赋值
        for(int i=0;i<datalist.size();i++){
            Map<Integer, Object> dataMap = datalist.get(i);
            for(int j=0;j<tempCells.size();j++){
                TempCell tempCell = tempCells.get(j);
                tempCell.setRow(tempCell.getRow()+1);
                tempCell.setData(dataMap.get(j+1));
                setCell(tempCell, wsheet);
            }
        }
 
         
    }
     
     
 
    /**
     * 
     * <p class="detail">
     * 功能：获取输入工作区
     * </p>
     * @date 2015年9月26日
     * @author <a href="mailto:1435290472@qq.com">zq</a>
     * @param tempFilePath
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    private Workbook getTempWorkbook(String tempFilePath) throws FileNotFoundException, IOException {
        if(!tempWorkbook.containsKey(tempFilePath)){
            if(tempFilePath.endsWith(".xlsx")){
                tempWorkbook.put(tempFilePath, new XSSFWorkbook(getFileInputStream(tempFilePath)));
            }else if(tempFilePath.endsWith(".xls")){
                tempWorkbook.put(tempFilePath, new HSSFWorkbook(getFileInputStream(tempFilePath)));
            }
        }
        return tempWorkbook.get(tempFilePath);
    }
     
    /**
     * 
     * <p class="detail">
     * 功能：获得模板输入流
     * </p>
     * @date 2015年9月26日
     * @author <a href="mailto:1435290472@qq.com">zq</a>
     * @param tempFilePath
     * @return
     * @throws FileNotFoundException
     */
    private FileInputStream getFileInputStream(String tempFilePath) throws FileNotFoundException {
        if(!tempStream.containsKey(tempFilePath)){
            tempStream.put(tempFilePath, new FileInputStream(tempFilePath));
        }
        return tempStream.get(tempFilePath);
    }
     
    /**
     * 
     * <p class="detail">
     * 功能：设置单元格数据,样式  (根据坐标：B3)
     * </p>
     * @date 2015年9月27日
     * @author <a href="mailto:1435290472@qq.com">zq</a>
     * @param point
     * @param data
     * @param sheet
     * @return
     */
    private TempCell getCell(String point,Object data,Sheet sheet){
        TempCell tempCell = new TempCell();
 
        //得到列 字母 
        String lineStr = "";
        String reg = "[A-Z]+";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(point);       
        while(m.find()){
            lineStr = m.group();
        }
        //将列字母转成列号  根据ascii转换
        char[] ch = lineStr.toCharArray();
        int column = 0;
        for(int i=0;i<ch.length;i++){
            char c = ch[i];
            int post = ch.length-i-1;
            int r = (int) Math.pow(10, post);
            column = column + r*((int)c-65);
        }
        tempCell.setColumn(column);
         
        //得到行号
        reg = "[1-9]+";
        p = Pattern.compile(reg);
        m = p.matcher(point);       
        while(m.find()){
            tempCell.setRow((Integer.parseInt(m.group())-1));
        }
         
        //获取模板指定单元格样式，设置到tempCell （写列表数据的时候用）
        Row rowIn = sheet.getRow(tempCell.getRow());
        if(rowIn == null) {
            rowIn = sheet.createRow(tempCell.getRow());
        }
        Cell cellIn = rowIn.getCell(tempCell.getColumn());
        if(cellIn == null) {
            cellIn = rowIn.createCell(tempCell.getColumn());
        }
        tempCell.setCellStyle(cellIn.getCellStyle());
         
        tempCell.setData(data);
        return tempCell;
    }
 
    /**
     * 
     * <p class="detail">
     * 功能：给指定坐标赋值
     * </p>
     * @date 2015年9月27日
     * @author <a href="mailto:1435290472@qq.com">zq</a>
     * @param tempCell
     * @param sheet
     */
    private void setCell(TempCell tempCell,Sheet sheet) {
        if(tempCell.getColumnSize()>-1){
            CellRangeAddress rangeAddress = mergeRegion(sheet, tempCell.getRow(), tempCell.getRow(), tempCell.getColumn(), tempCell.getColumn()+tempCell.getColumnSize());
            setRegionStyle(tempCell.getCellStyle(), rangeAddress, sheet);
        }
         
        Row rowIn = sheet.getRow(tempCell.getRow());
        if(rowIn == null) {
            rowIn = sheet.createRow(tempCell.getRow());
        }
        Cell cellIn = rowIn.getCell(tempCell.getColumn());
        if(cellIn == null) {
            cellIn = rowIn.createCell(tempCell.getColumn());
        }
        //根据data类型给cell赋值
        if(tempCell.getData() instanceof String){
            cellIn.setCellValue((String)tempCell.getData());
        }else if(tempCell.getData() instanceof Integer){
            cellIn.setCellValue((Integer)tempCell.getData());
        }else if(tempCell.getData() instanceof Double){
            cellIn.setCellValue((Double)tempCell.getData());
        }else if(tempCell.getData() instanceof Float){
            cellIn.setCellValue((Float)tempCell.getData());
        }else{
//            cellIn.setCellValue((String)tempCell.getData());
        	cellIn.setCellValue(tempCell.getData()+"");
        }
        //样式
        if(tempCell.getCellStyle()!=null && tempCell.getColumnSize()==-1){
            cellIn.setCellStyle(tempCell.getCellStyle());
        }
         
         
    }
     
    /**
     * 
     * <p class="detail">
     * 功能：写到输出流并移除资源
     * </p>
     * @date 2015年9月27日
     * @author <a href="mailto:1435290472@qq.com">zq</a>
     * @param tempFilePath
     * @param os
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void writeAndClose(String tempFilePath,String filePath,OutputStream os,HttpServletResponse response,String fileName) throws FileNotFoundException, IOException{
        if(getTempWorkbook(tempFilePath)!=null){
            getTempWorkbook(tempFilePath).write(os);
            tempWorkbook.remove(tempFilePath);
        }
        if(getFileInputStream(tempFilePath)!=null){
            getFileInputStream(tempFilePath).close();
            tempStream.remove(tempFilePath);
        }

		// 文件下载,下载完成后删除生成文件
		response.setContentType("application/msexcel;CHARSET=UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ fileName);
		// response.setContentType("octets/stream");
		response.setCharacterEncoding("GB2312");
		File file = new File(filePath);
		try {
			FileInputStream inputStream = new FileInputStream(file);
			BufferedOutputStream out = new BufferedOutputStream(
					response.getOutputStream());

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

		if (file.exists()) {
			file.delete();
		}
    }
     
    /**
     * 
     * <p class="detail">
     * 功能：判断指定的单元格是否是合并单元格
     * </p>
     * @date 2015年9月27日
     * @author <a href="mailto:1435290472@qq.com">zq</a>
     * @param sheet
     * @param row
     * @param column
     * @return  0:不是合并单元格，i:合并单元格的位置
     */
    private Integer isMergedRegion(Sheet sheet,int row ,int column) {  
        int sheetMergeCount = sheet.getNumMergedRegions();  
        for (int i = 0; i < sheetMergeCount; i++) {  
            CellRangeAddress range = sheet.getMergedRegion(i);  
            int firstColumn = range.getFirstColumn();  
            int lastColumn = range.getLastColumn();  
            int firstRow = range.getFirstRow();  
            int lastRow = range.getLastRow();  
            if(row >= firstRow && row <= lastRow){  
                if(column >= firstColumn && column <= lastColumn){  
                    return i;  
                }  
            }  
        }  
        return -1;  
    }
     
    /**
     * 
     * <p class="detail">
     * 功能：合并单元格
     * </p>
     * @date 2015年9月27日
     * @author <a href="mailto:1435290472@qq.com">zq</a>
     * @param sheet
     * @param firstRow
     * @param lastRow
     * @param firstCol
     * @param lastCol
     */
    private CellRangeAddress mergeRegion(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        CellRangeAddress rang = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        sheet.addMergedRegion(rang); 
        return rang;
    }
     
    /**
     * 
     * <p class="detail">
     * 功能：设置合并单元格样式
     * </p>
     * @date 2015年9月27日
     * @author <a href="mailto:1435290472@qq.com">zq</a>
     * @param cs
     * @param region
     * @param sheet
     */
    private static void setRegionStyle(CellStyle cs, CellRangeAddress region, Sheet sheet){  
        for(int i=region.getFirstRow();i<=region.getLastRow();i++){  
            Row row=sheet.getRow(i);  
            if(row==null) row=sheet.createRow(i);  
            for(int j=region.getFirstColumn();j<=region.getLastColumn();j++){  
                Cell cell=row.getCell(j);  
                if(cell==null){  
                    cell=row.createCell(j);  
                    cell.setCellValue("");  
                }  
                cell.setCellStyle(cs);  
            }  
        }  
    } 
 
 
 
     
    public static void main(String[] args) throws FileNotFoundException, IOException {
//      String tempFilePath = ExcelExportByTemplateUtil.class.getResource("demo.xlsx").getPath();
        String tempFilePath = "D:/demo.xlsx";
        File file = new File("d:/data.xlsx");
        OutputStream os = new FileOutputStream(file);
         
        ExcelExportByTemplateUtil excel = new ExcelExportByTemplateUtil();
//        Map<String, Object> dataMap = new HashMap<String, Object>();
//        dataMap.put("B1", "dddd");
//        dataMap.put("B2", 55);
//        dataMap.put("B3", 55);
//        excel.writeData(tempFilePath, dataMap, 0);
         
        List<Map<Integer, Object>> datalist = new ArrayList<Map<Integer,Object>>();
        Map<Integer, Object> data = new HashMap<Integer,Object>();
        data.put(1, "雷涛");
        data.put(2, "男");
        data.put(3, 4222);
        datalist.add(data);
        data = new HashMap<Integer,Object>();
        data.put(1, "小花");
        data.put(2, "女");
        data.put(3, "5555555");
        datalist.add(data);
        data = new HashMap<Integer,Object>();
        data.put(1, "小华");
        data.put(2, "女");
        data.put(3, "66665");
        datalist.add(data);
        String[] heads = new String[]{"A1","B1","C1"};   //必须为列表头部所有位置集合，   输出 数据单元格样式和头部单元格样式保持一致
        excel.writeDateList(tempFilePath,heads,datalist,0);
         
        //写到输出流并移除资源
//        excel.writeAndClose(tempFilePath, os);
        System.out.println("导出成功");
        os.flush();
        os.close();
    }
	
}
