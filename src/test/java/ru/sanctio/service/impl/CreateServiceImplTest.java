package ru.sanctio.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sanctio.dao.DBManagerCreate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateServiceImplTest {
    @InjectMocks
    private CreateServiceImpl createService;
    @Mock
    private DBManagerCreate dbManagerCreate;

    @Test
    void createNewClient() {
        createService.createNewClient(any(), any());

        verify(dbManagerCreate, times(1)).createNewClient(any(), any());
    }
}