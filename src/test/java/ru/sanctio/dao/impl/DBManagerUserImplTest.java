package ru.sanctio.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
class DBManagerUserImplTest {

    private DBManagerUserImpl dbManagerUser;

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:9.6.12"))
                    .withDatabaseName("jakarta")
                    .withUsername("evgeniysharychenkov");

    @BeforeEach
    void setUp() {
        dbManagerUser = new DBManagerUserImpl();
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