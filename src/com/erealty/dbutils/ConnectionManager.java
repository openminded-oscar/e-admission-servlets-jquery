package com.erealty.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static String jdbcUrl = "jdbc:mysql://localhost:3306/admission?characterEncoding=UTF-8";
	private static String user = "root";
	private static String password = "root";
	
	private static Connection connection = null;
	
	public static Connection getConnection(){
		if(connection == null){
			initializeConnection();
		}
		return connection;
	}
	
	private static void initializeConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcUrl,user,password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
