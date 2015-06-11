package com.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	private static SimpleDateFormat dateFormat=
			new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat time=
			new SimpleDateFormat("hh:mm:ss");
	private static SimpleDateFormat longFormat=new 
			SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static SimpleDateFormat chFormat=new 
			SimpleDateFormat("yyyy年MM月dd日");
	private static SimpleDateFormat chFormat2=new 
			SimpleDateFormat("yyyy年MM月dd日 E");
	
	public static Date toDate(String strDate) 
			throws ParseException{
		return dateFormat.parse(strDate);
		
	}
	
	public static String toFormatDate(Date date){
		return chFormat.format(date);
	}
	public static String toFormatDateWithWeek(Date date){
		return chFormat2.format(date);
	}
	
	
	public static void main(String[] args) {
		String str=DateUtil.toFormatDateWithWeek(new Date());
		
		System.out.println(str);
	}
}
