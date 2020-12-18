package com.servinglynk.hmis.warehouse.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public final class DateUtil {
	
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	
	 public static long diff(Date date1, Date date2){
		 long date1Millis = date1.getTime();
		 long date2Millis = date2.getTime();
		 return (date1Millis - date2Millis);
	 }
	 
	 public static long diffinHours(Date date1, Date date2){
		 	return diff(date1,date2) / DateUtils.MILLIS_PER_HOUR;
	 }
	 
	 public static long diffinDays(Date date1, Date date2){
		 	return diff(date1,date2) / DateUtils.MILLIS_PER_DAY;
	 }
	 
	 public static long diffinMinutes(Date date1, Date date2){
		 	return diff(date1,date2) / DateUtils.MILLIS_PER_MINUTE;
	 }

	 public static Date addDays(Date currentDate, Integer days) {
		return DateUtils.addDays(currentDate, days);
	 }
	 
	 
	 public static Date stringToDate(String dateString) throws Exception {
		 SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
		 return format.parse(dateString);
	 }

}
