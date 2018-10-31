package ru.sertok.hibernate.repository;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ru.sertok.hibernate.utils.Constants.*;

public class DateBaseRepository {
    protected static Connection connection;
    protected static DriverManagerDataSource driverManagerDataSource;
    static {
        try {
            driverManagerDataSource = new DriverManagerDataSource();
            driverManagerDataSource.setUsername(DB_USER);
            driverManagerDataSource.setUrl(DB_URL);
            driverManagerDataSource.setPassword(DB_PASSWORD);
            connection = DriverManager
                    .getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
