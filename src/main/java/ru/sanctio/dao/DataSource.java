package ru.sanctio.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    private static Properties prop;

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        if (ds == null) {
            return configDs();
        } else {
            return ds.getConnection();
        }
    }

    public static void setDs(HikariConfig config) {
        DataSource.ds = new HikariDataSource(config);
    }

    private static Connection configDs() throws SQLException {
        try {
            prop = new Properties();
            InputStream resourceAsStream = DataSource.class.getClassLoader().getResourceAsStream("db.properties");
            if (resourceAsStream != null) {
                prop.load(resourceAsStream);
            }
            config.setJdbcUrl(prop.getProperty("JdbcUrl"));
            config.setUsername(prop.getProperty("dataSource.user"));
            config.setDriverClassName(prop.getProperty("driverClassName"));
            config.addDataSourceProperty("cachePrepStmts", prop.getProperty("dataSource.cachePrepStmts"));
            config.addDataSourceProperty("prepStmtCacheSize", prop.getProperty("dataSource.prepStmtCacheSize"));
            config.addDataSourceProperty("prepStmtCacheSqlLimit", prop.getProperty("dataSource.prepStmtCacheSqlLimit"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ds = new HikariDataSource(config);
        return ds.getConnection();
    }
}
