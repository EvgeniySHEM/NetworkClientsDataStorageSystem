package ru.sanctio.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sanctio.dao.DBManagerDelete;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteServiceImplTest {
    @InjectMocks
    private DeleteServiceImpl deleteService;
    @Mock
    private DBManagerDelete dbManagerDelete;


    @Test
    void deleteAddress() {
        deleteService.deleteAddress(anyString());

        verify(dbManagerDelete, times(1)).deleteAddressById(anyString());
    }
}