//package ru.sanctio.dao.impl;
//
//import com.zaxxer.hikari.HikariConfig;
//import org.apache.ibatis.jdbc.ScriptRunner;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.utility.DockerImageName;
//import ru.sanctio.dao.DBManagerCreate;
//import ru.sanctio.dao.DBManagerSelect;
//import ru.sanctio.dao.DataSource;
//import ru.sanctio.model.Address;
//import ru.sanctio.model.Client;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.Reader;
//import java.sql.SQLException;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@Testcontainers
//class DBManagerSelectImplTest {
//
//    private static DBManagerSelect dbManagerSelect;
//    private static DBManagerCreate dbManagerCreate;
//    private static HikariConfig config;
//
//    private Client client;
//    private Address address;
//    private Client client2;
//    private Address address2;
//
//    @Container
//    static PostgreSQLContainer<?> postgres =
//            new PostgreSQLContainer<>(DockerImageName.parse("postgres:14-alpine"));
//
//    @BeforeAll
//    static void setUp() {
//
//        dbManagerSelect = new DBManagerSelectImpl();
//        dbManagerCreate = new DBManagerCreateImpl();
//        config = new HikariConfig();
//        config.setJdbcUrl(postgres.getJdbcUrl());
//        config.setUsername(postgres.getUsername());
//        config.setDriverClassName("org.postgresql.Driver");
//        config.setPassword(postgres.getPassword());
//        config.addDataSourceProperty("cachePrepStmts", "true");
//        config.addDataSourceProperty("prepStmtCacheSize", "250");
//        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
//        DataSource.setDs(config);
//
//        try {
//            ScriptRunner scriptRunner = new ScriptRunner(DataSource.getConnection());
//            Reader reader = new BufferedReader(new FileReader("/Users/evgeniysharychenkov" +
//                    "/IdeaProjects/NetworkClientsDataStorageSystem/src/test/resources/create.sql"));
//            scriptRunner.runScript(reader);
//        } catch (SQLException | FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @BeforeEach
//    void createClientAndAddress() {
//        client = new Client(1, "Паша", "Физическое лицо", "2023-05-13");
//        address = new Address(1, "111.111.111.111", "dd-dd-dd-dd-dd-dd", "model1",
//                "Minsk", client);
//        dbManagerCreate.createNewClient(client, address);
//        client2 = new Client(2, "Ваня", "Физическое лицо", "2023-05-13");
//        address2 = new Address(2, "222.222.222.222", "ff-ff-ff-ff-ff-ff", "model2",
//                "Moscow", client2);
//        dbManagerCreate.createNewClient(client2, address2);
//    }
//
//    @Test
//    void getAllInformation() {
//        List<Address> allInformation = dbManagerSelect.getAllInformation();
//
//        assertEquals(address, allInformation.get(0));
//        assertEquals(address2, allInformation.get(1));
//        assertEquals(2, allInformation.size());
//    }
//
//    @Test
//    void readAddressesFromResultSet() {
//        Address address1 = dbManagerSelect.selectAddressById(String.valueOf(address.getId()));
//
//        assertEquals(address, address1);
//    }
//
//    @Test
//    void selectAddressById() {
//        Address address1 = dbManagerSelect.selectAddressById(String.valueOf(address.getId()));
//
//        assertEquals(address, address1);
//    }
//}