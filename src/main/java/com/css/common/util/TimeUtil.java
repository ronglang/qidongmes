package com.css.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	
	/**
	 * 比较两个Timestamp 返回大的时间
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	@SuppressWarnings("all")
	public static Timestamp  compTime(Timestamp time1, Timestamp time2) {
		Timestamp maxTime = null;
		Calendar ca1 = Calendar.getInstance();
		ca1.setTime(time1);
		Calendar ca2 = Calendar.getInstance();
		ca2.setTime(time2);
		int comp = ca1.compareTo(ca2);
		if (comp > 0) {
			maxTime = time1;
		} else {
			maxTime = time2;
		}

		return maxTime;
	}
	/**
	 * 时间字符串转时间戳
	 * 
	 * @param str
	 * @return
	 */
	public static Timestamp stringToTimestamp(String str) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	public static Timestamp calendarToTimestamp(Calendar cal) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd H:m");
		Date date = null;
		try {
			String str = sf.format(cal.getTime()).toString();
			date = sf.parse(str);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Timestamp(date.getTime());
	}


}
