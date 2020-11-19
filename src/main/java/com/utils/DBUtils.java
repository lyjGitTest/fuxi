package com.utils;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtils {
	private static String driver;
	private static String url;
	private static String user;
	private static String password;

	static Connection conn = null;

	static {
		Properties properties = new Properties();
		Reader inReader; 

		try {
			String path = Thread.currentThread().getContextClassLoader().getResource("jdbc.properties").getPath();
			inReader = new FileReader(path); 
			properties.load(inReader); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		driver = properties.getProperty("driver");
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
	}


	public static Connection open() {
		try {
			Class.forName(driver);
			System.out.println("连接成功......");
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("连接数据库失败");
		}
		return null;
	}


	public static Connection close() {
		if (conn != null) {
			try {
				conn.close();
				System.out.println("关闭数据库...");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("数据库关闭失败...");
			}
		}
		return null;
	}
}
