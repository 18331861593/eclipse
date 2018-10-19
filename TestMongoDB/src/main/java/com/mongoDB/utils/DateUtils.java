package com.mongoDB.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
	
	
	/**
	 * 返回 指定格式的时间
	 * @param format
	 * @return
	 */
	public static String getDate(String format){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(calendar.getTime());
	}
	
	
	
}
