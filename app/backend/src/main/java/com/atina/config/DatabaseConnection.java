package com.atina.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String PROPERTIES_FILE = "db.properties";
    private static final Properties properties = new Properties();
    
    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream input = DatabaseConnection.class.getClassLoader()
                .getResourceAsStream(PROPERTIES_FILE)) {
            
            if (input == null) {
                throw new RuntimeException("Não foi possível encontrar " + PROPERTIES_FILE);
            }
            
            properties.load(input);
            
            // Carrega o driver explicitamente (opcional para versões recentes do JDBC)
            Class.forName(properties.getProperty("db.driver"));
            
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar configurações do banco de dados", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            properties.getProperty("db.url"),
            properties.getProperty("db.user"),
            properties.getProperty("db.password")
        );
    }

    // Método adicional para obter propriedades (opcional)
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
