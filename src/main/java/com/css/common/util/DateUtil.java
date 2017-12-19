 package com.css.common.util;
 
 import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
 public class DateUtil
 {
   private static Log log = LogFactory.getLog(DateUtil.class);
   private static String defaultDatePattern = "yyyy-MM-dd";
   private static String timePattern = "HH:mm:ss";
   private static String datetimePattern = "yyyy-MM-dd HH:mm:ss";
   private static int firstDay = 2;
   private static SimpleDateFormat dateFormat = new SimpleDateFormat(datetimePattern);
   static final String NUMBER_ZHCN_MAP = "〇一二三四五六七八九";
   
   public static Date getFirstDayOfWeek(int year, int week)
   {
     Calendar c = new GregorianCalendar();
     c.set(1, year);
     c.set(3, week);
     c.set(7, 1);
     return c.getTime();
   }
   
   public static int getWeekNumber(Date date)
   {
     Calendar c = new GregorianCalendar();
     c.setFirstDayOfWeek(firstDay);
     c.setMinimalDaysInFirstWeek(4);
     c.setTime(date);
     int weeknum = c.get(3);
     return weeknum;
   }
   
   public static String getWeek(Date date)
   {
     Calendar cd = Calendar.getInstance();
     cd.setTime(date);
     int mydate = cd.get(7);
     String showDate = "星期六";
     switch (mydate)
     {
     case 1: 
       showDate = "星期日";
       break;
     case 2: 
       showDate = "星期一";
       break;
     case 3: 
       showDate = "星期二";
       break;
     case 4: 
       showDate = "星期三";
       break;
     case 5: 
       showDate = "星期四";
       break;
     case 6: 
       showDate = "星期五";
       break;
     default: 
       showDate = "星期六";
     }
     return showDate;
   }
   
   public static String getWeekDay(Date date)
   {
     Calendar cd = Calendar.getInstance();
     cd.setTime(date);
     int mydate = cd.get(7);
     String showDate = "周六";
     switch (mydate)
     {
     case 1: 
       showDate = "周日";
       break;
     case 2: 
       showDate = "周一";
       break;
     case 3: 
       showDate = "周二";
       break;
     case 4: 
       showDate = "周三";
       break;
     case 5: 
       showDate = "周四";
       break;
     case 6: 
       showDate = "周五";
       break;
     default: 
       showDate = "周六";
     }
     return showDate;
   }

   public static final String getDate(Date aDate)
   {
     return getDateTime(defaultDatePattern, aDate);
   }
   
   public static final Date convertStringToDate(String aMask, String strDate)
     throws ParseException
   {
     SimpleDateFormat df = null;
     Date date = null;
     df = new SimpleDateFormat(aMask);
     if (log.isDebugEnabled()) {
       log.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");
     }
     try
     {
       date = df.parse(strDate);
     }
     catch (ParseException pe)
     {
       log.error("ParseException: " + pe);
       throw new ParseException(pe.getMessage(), pe.getErrorOffset());
     }
     return date;
   }
   
   public static String getTimeNow(Date theTime)
   {
     return getDateTime(timePattern, theTime);
   }
   
   public static final String getDateTime(String aMask, Date aDate)
   {
     SimpleDateFormat df = null;
     String returnValue = "";
     if (aDate == null)
     {
       log.error("aDate is null!");
     }
     else
     {
       df = new SimpleDateFormat(aMask);
       returnValue = df.format(aDate);
     }
     return returnValue;
   }
   
   public static final String getDateTime(Date aDate)
   {
     SimpleDateFormat df = null;
     String returnValue = "";
     if (aDate == null)
     {
       log.error("aDate is null!");
     }
     else
     {
       df = new SimpleDateFormat(datetimePattern);
       returnValue = df.format(aDate);
     }
     return returnValue;
   }
   
   public static String getCurrentDateTime()
   {
     return getDateTime(datetimePattern, new Date());
   }
   
   public static Date adjustDate(Date date, Integer windage)
   {
     return adjust(date, windage, 5);
   }
   
   public static Date adjustYear(Date date, Integer windage)
   {
     return adjust(date, windage, 1);
   }
   
   public static Date adjustMonth(Date date, Integer windage)
   {
     return adjust(date, windage, 2);
   }
   
   public static String[] getFirstOrLashDayByMonth(String date)
   {
     String[] dates = new String[2];
     Calendar c = Calendar.getInstance();
     int year = Integer.parseInt(date.substring(0, 4));
     int month = Integer.parseInt(date.substring(5, 7));
     c.set(1, year);
     c.set(2, month - 1);
     dates[0] = (year + "-" + month + "-" + c.getActualMinimum(5));
     dates[1] = (year + "-" + month + "-" + c.getActualMaximum(5));
     return dates;
   }
   
   public static Date[] getFirstOrLastDayOfMonth(Date date)
   {
     Calendar firstDate = new GregorianCalendar();
     firstDate.setTime(date);
     Calendar lastDate = (Calendar)firstDate.clone();
     firstDate.set(5, firstDate.getActualMinimum(5));
     lastDate.set(5, firstDate.getActualMaximum(5));
     return new Date[] { firstDate.getTime(), lastDate.getTime() };
   }
   
   public static Date[] getFirstOrLastDayOfMonth()
   {
     return getFirstOrLastDayOfMonth(new Date());
   }
   
   public static String[] getDateForLastOrNextMonth(Integer lastOrNext, Date date)
   {
     if (lastOrNext.intValue() == 0) {
       date = adjustMonth(date, Integer.valueOf(-1));
     } else {
       date = adjustMonth(date, Integer.valueOf(1));
     }
     String str = getDateTime("yyyy-MM-dd", date);
     return getFirstOrLashDayByMonth(str);
   }
   
   private static Date adjust(Date date, Integer windage, int flag)
   {
     Calendar cal = new GregorianCalendar();
     cal.setTime(date);
     cal.add(flag, windage.intValue());
     return cal.getTime();
   }
   
   public static Date getFirstDateOfYear()
   {
     Calendar cld = Calendar.getInstance();
     cld.set(cld.get(1), 0, 1, 0, 0, 0);
     return cld.getTime();
   }
   
   public static String getFirstDateStringOfYear(String pattern)
   {
     Date date = getFirstDateOfYear();
     return getDateTime(null == pattern ? defaultDatePattern : pattern, date);
   }
   
   public static Date getLastDateOfYear()
   {
     Calendar cld = Calendar.getInstance();
     cld.set(cld.get(1), 11, 31, 0, 0, 0);
     return cld.getTime();
   }
   
   public static String getLastDateStringOfYear(String pattern)
   {
     Date date = getLastDateOfYear();
     return getDateTime(null == pattern ? defaultDatePattern : pattern, date);
   }
   
   public static synchronized String format(Date date, String pattern)
   {
     if (pattern != null) {
       dateFormat.applyPattern(pattern);
     }
     return dateFormat.format(date);
   }
   
   public static String formatChineseDate(Date date)
   {
     String[] dstr = format(date, "yyyy-M-d").split("-");
     StringBuilder rstr = new StringBuilder();
     for (int i = 0; i < 3; i++) {
       rstr.append("〇一二三四五六七八九".charAt(Integer.valueOf(dstr[0].substring(i, i + 1)).intValue()));
     }
     rstr.append('年');
     if (dstr[1].length() == 2)
     {
       rstr.append('十');
       if (!dstr[1].equals("10")) {
         rstr.append("〇一二三四五六七八九".charAt(Integer.valueOf(dstr[1].substring(1, 2)).intValue()));
       }
     }
     else
     {
       rstr.append("〇一二三四五六七八九".charAt(Integer.valueOf(dstr[1]).intValue()));
     }
     rstr.append('月');
     
     int d = Integer.valueOf(dstr[2]).intValue();
     if (d < 10) {
       rstr.append("〇一二三四五六七八九".charAt(d));
     } else if (d == 10) {
       rstr.append("十");
     } else {
       rstr.append("〇一二三四五六七八九".charAt(Integer.valueOf(dstr[2].substring(0, 1)).intValue())).append("十").append("〇一二三四五六七八九".charAt(Integer.valueOf(dstr[2].substring(1, 2)).intValue()));
     }
     return "日";
   }
   
   /**
    * @TODO:取当前工作日，从今天开始算.并且用最简单的方式排除掉周日
    * @author: zhaichunlei
    & @DATE : 2017年8月8日
    */
   public static int getCurrentDateInYyyyMMdd_intVal(){
	   java.util.Date wd = new java.util.Date();
		String wkd = DateUtil.getWeekDay(wd);
		if("周日".equals(wkd)){
			 Calendar cd = Calendar.getInstance();
		     cd.setTime(wd);
		     cd.add(Calendar.DATE, 1);
		     wd = new Date(cd.getTimeInMillis());
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String wds = sdf.format(wd);
		Integer workDay = Integer.parseInt(wds);				
		return workDay;
   }
   
   /**
    * @TODO:日期加1，取年月月，从今天开始算.并且用最简单的方式排除掉周日
    * @author: zhaichunlei
    & @DATE : 2017年8月8日
    * @param workDay
    * @return
 * @throws ParseException 
    */
   public static  int increaseDateInYyyyMMdd_intVal(int workDay) throws ParseException{
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	   Date wd = sdf.parse(workDay+1+"");
	   String wkd = getWeekDay(wd);
	   while("周日".equals(wkd)){
		   Calendar cd = Calendar.getInstance();
	       cd.setTime(wd);
	       cd.add(Calendar.DATE, 1);
	       wd = new Date(cd.getTimeInMillis());
	       wkd = getWeekDay(wd);
	   }
	   String wds = sdf.format(wd);
	   Integer wDay = Integer.parseInt(wds);				
	   return wDay;
   }
   
   /**
    * 字符串转date
    * @param date
    * @param pattern
    * @return
 * @throws ParseException 
    */
   public static Date getDateFromStr(String dateStr,String pattern) throws ParseException{
	   SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	   Date date = sdf.parse(dateStr);
	   return date;
   }
   
   public static String getDayString(Integer minutes){
	   if(minutes == null) return "";
	   
	   String str = "";
	   int i = 0;
	   if((i = minutes / 60 * 24 * 365) > 0){
		   str += i + "年";
	   }
	   if((i = minutes / 60 * 24 ) > 0){
		   str += i + "天";
	   }
	   if((i = minutes / 60 ) > 0){
		   str += i + "小时";
	   }
	   if((i % 60) > 0){
		   str += i + "分";
	   }
	   return str;
   }
   
   public static void main(String[] args)
   {
     System.out.println(formatChineseDate(new Date()));
   }
 }






