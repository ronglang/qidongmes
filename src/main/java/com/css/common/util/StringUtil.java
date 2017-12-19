 package com.css.common.util;
 
 import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.druid.sql.visitor.functions.Char;
 
 public class StringUtil
 {
   private static final Log log = LogFactory.getLog(StringUtil.class);
   
   public static boolean isEmpty(String source)
   {
     return (source == null) || (source.isEmpty());
   }
   
   public static boolean isNotEmpty(String source)
   {
     return !isEmpty(source);
   }
   
   public static String transformUtf8(String str)
   {
     if (isEmpty(str)) {
       return "";
     }
     String restr = str;
     try
     {
       return new String(str.getBytes("ISO8859-1"), "utf-8");
     }
     catch (UnsupportedEncodingException e)
     {
       log.error(e);
     }
     return restr;
   }
   
   public static String getUUID()
   {
     String s = UUID.randomUUID().toString();
     
     return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
   }
   
   public static String transformNull(String str)
   {
     return str == null ? "" : str;
   }
   
   public static String getPingYin(String src)
   {
     char[] t1 = null;
     t1 = src.toCharArray();
     String[] t2 = new String[t1.length];
     HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
     t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
     t3.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
     t3.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
     String t4 = "";
     int t0 = t1.length;
     try
     {
       for (int i = 0; i < t0; i++) {
         if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+"))
         {
           t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
           t4 = t4 + t2[0];
         }
         else
         {
           t4 = t4 + Character.toString(t1[i]);
         }
       }
       return t4;
     }
     catch (BadHanyuPinyinOutputFormatCombination e1)
     {
       e1.printStackTrace();
     }
     return t4;
   }
   
   public static Object parseOriginalType(String str)
   {
     if (("true".equals(str)) || ("false".equals(str))) {
       return Boolean.valueOf(str);
     }
     if (isNumeric(str)) {
       return new BigDecimal(str);
     }
     return str;
   }
   
   public static boolean isNumeric(String str)
   {
     return str.matches("\\d+|\\d+.\\d+");
   }
   
   /**
    * 字符串list去重
    * @param a 需要去重的list
    * @return
    * @author leitao
    */
   public static List<String> listUnique(List<String> a) {  
	    List<String> list = new LinkedList<String>();  
	    for(int i = 0; i < a.size(); i++) {  
	        if(!list.contains(a.get(i))) {  
	            list.add(a.get(i));  
	        }  
	    }  
	    return list;  
	}  
   
   /**
    * 字符串数组去重
    * @param a 需要去重的数组
    * @return
    * @author leitao
    */
   public static String[] arrayUnique(String[] a) {  
	    List<String> list = new LinkedList<String>();  
	    for(int i = 0; i < a.length; i++) {  
	        if(!list.contains(a[i])) {  
	            list.add(a[i]);  
	        }  
	    }  
	    return (String[])list.toArray(new String[list.size()]);  
	}  
   
   /**
    * @TODO: 为一个字符串，使用c补到长度为len长度的字符串。 相当于数据库函数， lpad
    * @author: zhaichunlei
    & @DATE : 2017年7月28日
    * @param str
    * @param len
    * @param c
    * @return
    */
   public static String lpad(String str, int len, char c){
	   int l = len - str.length();
	   
	   String ret = str;
	   for(int i = l ;i > 0 ; i -- ){
		   ret = c + ret;
	   }
	   return ret;
   } 
   
 }






