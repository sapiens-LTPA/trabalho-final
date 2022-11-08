package br.sapiens.configs;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSingleton {

    private final String jdbcURL = "jdbc:h2:mem:test";
    static Connection connection;

    public Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed())
            connection = DriverManager.getConnection(jdbcURL);
        return connection;
    }

}