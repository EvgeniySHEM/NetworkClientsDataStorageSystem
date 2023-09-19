package ru.sanctio.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sanctio.dao.DBManagerClient;
import ru.sanctio.model.dto.AddressDTO;
import ru.sanctio.model.dto.ClientDTO;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    private ClientDTO client;
    private AddressDTO address;

    @InjectMocks
    private ClientServiceImpl clientServiceImpl;
    @Mock
    private DBManagerClient dbManagerClient;

    @BeforeEach
    void create() {
        client = new ClientDTO(1, "Макс", "Физическое лицо", "2022-02-01");
        address = new AddressDTO(1, "111.111.111.111", "dd-dd-dd-dd-dd-dd",
                "model", "address", client);
    }

    @Test
    void createNewClient() {
        clientServiceImpl.createNewClient(client, address);

        verify(dbManagerClient, times(1)).createNewClient(any(), any());
    }

    @Test
    void updateClient() {
        clientServiceImpl.updateClient(client, address);

        verify(dbManagerClient, times(1)).update(any(), any());
    }
}