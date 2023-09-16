package ru.sanctio.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sanctio.dao.DBManagerSelect;
import ru.sanctio.dao.DBManagerUpdate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateServiceImplTest {
    @InjectMocks
    private UpdateServiceImpl updateService;
    @Mock
    private DBManagerSelect dbManagerSelect;
    @Mock
    private DBManagerUpdate dbManagerUpdate;

    @Test
    void selectAddress() {
        updateService.selectAddress(any());

        verify(dbManagerSelect, times(1)).selectAddressById(any());
    }

    @Test
    void updateClient() {
        updateService.updateClient(any(), any());

        verify(dbManagerUpdate, times(1)).update(any(), any());
    }
}