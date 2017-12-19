 package com.css.common.util;
 
 import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
 
 public class PinyinUtil
 {
   public static String spell(String chinese){
     if (chinese == null) {
       return null;
     }
     HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
     format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
     format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
     format.setVCharType(HanyuPinyinVCharType.WITH_V);
     try
     {
       StringBuilder sb = new StringBuilder();
       for (int i = 0; i < chinese.length(); i++)
       {
         String[] array = PinyinHelper.toHanyuPinyinStringArray(chinese.charAt(i), format);
         if ((array != null) && (array.length != 0))
         {
           String s = array[0];
           char c = s.charAt(0);
           if (i == 0) {
             sb.append(s);
           } else {
             sb.append(c);
           }
         }
       }
       return sb.toString();
     }
     catch (BadHanyuPinyinOutputFormatCombination e)
     {
       e.printStackTrace();
     }
     return null;
   }
   
   public static String spell(String chinese, int num)
   {
     if (chinese == null) {
       return null;
     }
     HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
     format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
     format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
     format.setVCharType(HanyuPinyinVCharType.WITH_V);
     try
     {
       StringBuilder sb = new StringBuilder();
       for (int i = 0; i < chinese.length(); i++)
       {
         String[] array = PinyinHelper.toHanyuPinyinStringArray(chinese.charAt(i), format);
         if ((array != null) && (array.length != 0))
         {
           String s = array[0];
           char c = s.charAt(0);
           if (i <= num) {
             sb.append(s);
           } else {
             sb.append(c);
           }
         }
       }
       return sb.toString();
     }
     catch (BadHanyuPinyinOutputFormatCombination e)
     {
       e.printStackTrace();
     }
     return null;
   }
   
   public static void main(String[] args)
   {
     //System.out.println(spell("刘宝瑞"));
	   System.out.println(spell("苟玉军"));
   }
 }