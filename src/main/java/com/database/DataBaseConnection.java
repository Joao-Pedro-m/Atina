package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	private static final String URL = "jdbc:mysql://db:3306/meu_banco";
	private static final String USER = "usuario";
	private static final String PASSWORD = "senha";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
