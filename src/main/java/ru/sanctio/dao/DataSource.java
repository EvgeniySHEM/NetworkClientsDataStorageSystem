package ru.sanctio.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static HikariConfig config = new HikariConfig(
            "/Users/evgeniysharychenkov/IdeaProjects/NetworkClientsDataStorageSystem" +
                    "/src/main/resources/db.properties");
    private static HikariDataSource ds;

    static {
        ds = new HikariDataSource(config);
    }

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
