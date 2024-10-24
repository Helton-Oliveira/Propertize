package com.digisphere.propertize.adapter.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class PostgreSqlAdapter implements IConnection {

    private Connection connection;

    @Override
    public PreparedStatement query(String sql) {
        try {
            return Objects.requireNonNull(connect()).prepareStatement(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection connect() {
        try {
            Properties properties = new Properties();
            InputStream input = getClass().getClassLoader().getResourceAsStream("env.properties");
            if (input == null) {
                System.out.println("Desculpe, não foi possível encontrar o arquivo env.properties");
                return null;
            }
            properties.load(input);
            String DB_URL = properties.getProperty("DB_URL");
            String PASS = properties.getProperty("DB_PASSWORD");
            String USER = properties.getProperty("DB_USER");

            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException | ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
