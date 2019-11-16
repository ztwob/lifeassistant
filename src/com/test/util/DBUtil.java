package com.lifeassistant.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {
	// 声明成员变量 接受参数
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	// 静态数据块 类加载时自动加载
	static {
		try {
			// 读取配置文件 加载jdbc参数
			Properties dbProperties = new Properties();
			InputStream iStream = DBUtil.class.getResourceAsStream("/db.properties");
			// 加载文件
			dbProperties.load(iStream);
			// 获取参数
			driver = dbProperties.getProperty("driverClassName");
			url = dbProperties.getProperty("url");
			user = dbProperties.getProperty("username");
			password = dbProperties.getProperty("password");
			// 加载驱动
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取数据库连接 返回连接
	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public static void releaseDb(ResultSet resultSet, Statement statement, Connection connection) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}
	
}

