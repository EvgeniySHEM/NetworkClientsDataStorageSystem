package ru.sanctio.dao.impl;

import com.zaxxer.hikari.HikariConfig;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import ru.sanctio.dao.DataSource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
class DBManagerUserImplTest {

    private static DBManagerUserImpl dbManagerUser;
    private static HikariConfig config;

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:9.6.12"));

    @BeforeAll
    static void setUp() {
        dbManagerUser = new DBManagerUserImpl();

        config = new HikariConfig();
        config.setJdbcUrl(postgres.getJdbcUrl());
        config.setUsername(postgres.getUsername());
        config.setDriverClassName("org.postgresql.Driver");
        config.setPassword(postgres.getPassword());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        DataSource.setDs(config);

        try {
            ScriptRunner scriptRunner = new ScriptRunner(DataSource.getConnection());
            Reader reader = new BufferedReader(new FileReader("/Users/evgeniysharychenkov" +
                    "/IdeaProjects/NetworkClientsDataStorageSystem/src/test/resources/create.sql"));
            scriptRunner.runScript(reader);
        } catch (SQLException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void checkUser_ShouldReturnTrueExistingUser() {

        assertTrue(dbManagerUser.checkUser("Mark", "123qwe"));
    }

    @Test
    void checkUser_ShouldReturnFalseNotExistingUser() {

        assertFalse(dbManagerUser.checkUser("Tom", "123qwe"));
    }

    @Test
    void checkUser_ShouldReturnFalseByFalsePassword() {

        assertFalse(dbManagerUser.checkUser("Mark", "false"));
    }
}