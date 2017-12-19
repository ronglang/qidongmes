package com.css.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.junit.Test;

public class DocWriteUtil {
 
//	public static void main(String[] args) throws Exception{
//		XwpfTest t = new XwpfTest();
//		t.testTemplateWrite();
//	}
	
   /**
    * 用一个docx文档作为模板，然后替换其中的内容，再写入目标文档中。
    * @throws Exception
    */
   @Test
   public String writeEventReport(InputStream is,HttpServletRequest request,String title,String eventinfo,String surrounding,String weather,String orgDuty,String actInfo) throws Exception {
	   Map<String, Object> params = new HashMap<String, Object>();
	   params.put("title",  title);
	   params.put("time", new Date().toString());
	   params.put("event", eventinfo);
	   params.put("surrounding", surrounding);
	   params.put("weather", weather);
	   params.put("orginfo", orgDuty);
	   params.put("actinfo", actInfo);
//	   String filePath = "D:\\111.docx";
//	   InputStream is = new FileInputStream(filePath);
	   XWPFDocument doc = new XWPFDocument(is);
	   //替换段落里面的变量
	   this.replaceInPara(doc, params);
	   //替换表格里面的变量
	   this.replaceInTable(doc, params);
	   String reportName = "";
		String reportDir = "reportdocs" + File.separatorChar;
	   //String autoCreatedDateDirByParttern = "yyyy" + File.separatorChar + "MM" + File.separatorChar + "dd"+ File.separatorChar;
		//String autoCreatedDate = DateFormatUtils.format(new java.util.Date(), autoCreatedDateDirByParttern);
		reportName = title+"报告.docx";
		String projectDic = request.getSession().getServletContext().getRealPath("")+File.separatorChar;
		File saveDir = new File(projectDic+reportDir);
		if(!saveDir.exists()){
			saveDir.mkdirs();
		}
	   OutputStream os = new FileOutputStream(projectDic+reportDir+reportName);
	   doc.write(os);
	   this.close(os);
	   this.close(is);
	   return reportDir+reportName;
   }
   
   /**
    * 生成文档
    * @param is 模板文件输入流
    * @param request http请求
    * @param typeName 调用的实体类型名称，如事件，值班等
    * @param params 文档生成参数
    * @param docName 文档名称，如打印文档，报告文档等
    * @return 文档服务器储存路径
    * @throws Exception
    */
   public String writeDoc(InputStream is,HttpServletRequest request, String typeName, Map<String, Object> params,String docName) throws Exception {
	   XWPFDocument doc = new XWPFDocument(is);
	   //替换段落里面的变量
	   this.replaceInPara(doc, params);
	   //替换表格里面的变量
	   this.replaceInTable(doc, params);
	   String reportName = "";
	   String reportDir = "printdocs" + File.separatorChar;
	   String autoCreatedDateDirByParttern = "yyyyMMddHHmmss";
		String autoCreatedDate = DateFormatUtils.format(new java.util.Date(), autoCreatedDateDirByParttern);
		reportName = typeName+autoCreatedDate+docName+".docx";
		String projectDic = request.getSession().getServletContext().getRealPath("")+File.separatorChar;
		System.out.println(projectDic+reportDir+reportName);
		File saveDir = new File(projectDic+reportDir);
		if(!saveDir.exists()){
			saveDir.mkdirs();
		}
	   OutputStream os = new FileOutputStream(projectDic+reportDir+reportName);
	   doc.write(os);
	   this.close(os);
	   this.close(is);
	   return reportDir+reportName;
   }
   
   /**
    * 替换段落里面的变量
    * @param doc 要替换的文档
    * @param params 参数
    */
   private void replaceInPara(XWPFDocument doc, Map<String, Object> params) {
      Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();
      XWPFParagraph para;
      while (iterator.hasNext()) {
         para = iterator.next();
         this.replaceInPara(para, params);
      }
   }
  
   /**
    * 替换段落里面的变量
    * @param para 要替换的段落
    * @param params 参数
    */
   private void replaceInPara(XWPFParagraph para, Map<String, Object> params) {
      List<XWPFRun> runs;
      Matcher matcher;
      if (this.matcher(para.getParagraphText()).find()) {
         runs = para.getRuns();
         for (int i=0; i<runs.size(); i++) {
            XWPFRun run = runs.get(i);
            String runText = run.toString();
            matcher = this.matcher(runText);
            if (matcher.find()) {
                while ((matcher = this.matcher(runText)).find()) {
                   runText = matcher.replaceFirst(String.valueOf(params.get(matcher.group(1))));
                }
                //直接调用XWPFRun的setText()方法设置文本时，在底层会重新创建一个XWPFRun，把文本附加在当前文本后面，
                //所以我们不能直接设值，需要先删除当前run,然后再自己手动插入一个新的run。
                para.removeRun(i);
                para.insertNewRun(i).setText(runText);
            }
         }
      }
   }
  
   /**
    * 替换表格里面的变量
    * @param doc 要替换的文档
    * @param params 参数
    */
   private void replaceInTable(XWPFDocument doc, Map<String, Object> params) {
      Iterator<XWPFTable> iterator = doc.getTablesIterator();
      XWPFTable table;
      List<XWPFTableRow> rows;
      List<XWPFTableCell> cells;
      List<XWPFParagraph> paras;
      while (iterator.hasNext()) {
         table = iterator.next();
         rows = table.getRows();
         for (XWPFTableRow row : rows) {
            cells = row.getTableCells();
            for (XWPFTableCell cell : cells) {
                paras = cell.getParagraphs();
                for (XWPFParagraph para : paras) {
                   this.replaceInPara(para, params);
                }
            }
         }
      }
   }
  
   /**
    * 正则匹配字符串
    * @param str
    * @return
    */
   private Matcher matcher(String str) {
      Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}", Pattern.CASE_INSENSITIVE);
      Matcher matcher = pattern.matcher(str);
      return matcher;
   }
  
   /**
    * 关闭输入流
    * @param is
    */
   private void close(InputStream is) {
      if (is != null) {
         try {
            is.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
  
   /**
    * 关闭输出流
    * @param os
    */
   private void close(OutputStream os) {
      if (os != null) {
         try {
            os.close();
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }
  
}