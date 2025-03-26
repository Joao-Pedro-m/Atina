package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	private static final String URL = "jdbc:mysql://db:3306/mydb";
	private static final String USER = "user";
	private static final String PASSWORD = "password";
	
	public static Connection getConnection() {
        	try {
            		// Tenta carregar o driver do MySQL
            		Class.forName("com.mysql.cj.jdbc.Driver"); // Adiciona o driver

            		// Retorna a conexão com o banco
            		return DriverManager.getConnection(URL, USER, PASSWORD);
        	} catch (ClassNotFoundException e) {
            		System.out.println("Driver JDBC do MySQL não encontrado!");
            		e.printStackTrace();
            		return null;  // Retorna null se falhar ao carregar o driver
        	} catch (SQLException e) {
          		System.out.println("Erro ao conectar ao banco de dados!");
          	  	e.printStackTrace();
            		return null;
        	}
    	}
}
