package com.Date.date;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class TestDate {
	
	public static void main(String[] args) {
		
		Date date = new Date(1534301078223l);
		// gettime 返回毫秒数
//		System.out.println(date.getTime());
		//tostring
//		System.out.println(date.toString());
		
		SimpleDateFormat format = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		
		System.out.println(format.format(date));
		
		
		
		
	}
}
