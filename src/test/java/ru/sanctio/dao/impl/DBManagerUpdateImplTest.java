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
//import ru.sanctio.dao.DBManagerUpdate;
//import ru.sanctio.dao.DataSource;
//import ru.sanctio.model.Address;
//import ru.sanctio.model.Client;
//
//import java.io.*;
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@Testcontainers
//class DBManagerUpdateImplTest {
//
//    private static DBManagerUpdate dbManagerUpdate;
//    private static DBManagerSelect dbManagerSelect;
//    private static DBManagerCreate dbManagerCreate;
//    private static HikariConfig config;
//
//    private Client client;
//    private Address address;
//
//    @Container
//    static PostgreSQLContainer<?> postgres =
//            new PostgreSQLContainer<>(DockerImageName.parse("postgres:14-alpine"));
//
//    @BeforeAll
//    static void setUp() {
//        dbManagerUpdate = new DBManagerUpdateImpl();
//        dbManagerSelect = new DBManagerSelectImpl();
//        dbManagerCreate = new DBManagerCreateImpl();
//        config = new HikariConfig();
//        config.setJdbcUrl( postgres.getJdbcUrl() );
//        config.setUsername( postgres.getUsername() );
//        config.setDriverClassName("org.postgresql.Driver");
//        config.setPassword(postgres.getPassword() );
//        config.addDataSourceProperty( "cachePrepStmts" , "true" );
//        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
//        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
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
//            client = new Client(1,"Паша", "Физическое лицо", "2023-05-13");
//            address = new Address(1, "111.111.111.111", "dd-dd-dd-dd-dd-dd", "model1",
//                    "Minsk", client);
//            dbManagerCreate.createNewClient(client, address);
//    }
//
//    @Test
//    void update_UpdateClient() {
//            client.setClientName("Макс");
//            dbManagerUpdate.update(client, address);
//            Address address1 = dbManagerSelect.selectAddressById(String.valueOf(address.getId()));
//
//            assertEquals(client.getClientName(), address1.getClient().getClientName());
//    }
//
//    @Test
//    void update_UpdateAddress() {
//            address.setIp("111.111.111.222");
//            assertTrue(dbManagerUpdate.update(client, address));
//
//            Address address1 = dbManagerSelect.selectAddressById(String.valueOf(address.getId()));
//            assertEquals(address.getIp(), address1.getIp());
//    }
//}