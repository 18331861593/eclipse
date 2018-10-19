package com.hello.shiro.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtils {
	
	
	/**
	 * 获取数据库链接
	 * @return
	 * @throws Exception
	 */
	public static Connection getConn() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_shiro","root","root");
		return conn;
	}
	
	/**
	 * 关闭链接
	 * @param conn
	 * @throws Exception
	 */
	public static void closeConn(Connection conn) throws Exception{
		if(conn != null){
			conn.close();
		}
	}
	
	
	public static void main(String[] args) {
		DbUtils dbUtil=new DbUtils();
		try {
			dbUtil.getConn();
			System.out.println("数据库连接成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
	
	
}
