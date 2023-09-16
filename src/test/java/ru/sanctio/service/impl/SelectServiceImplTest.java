package ru.sanctio.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sanctio.dao.DBManagerSelect;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SelectServiceImplTest {
    @InjectMocks
    private SelectServiceImpl selectService;
    @Spy
    private SelectServiceImpl selectServiceSpy;
    @Mock
    private DBManagerSelect dbManagerSelect;


    @Test
    void getData() {
        selectService.getData();

        verify(dbManagerSelect, times(1)).getAllInformation();
    }

    @Test
    void getSortedData() {
        selectServiceSpy = spy(selectService);
        selectServiceSpy.getSortedData();

        verify(selectServiceSpy, times(1)).getData();
    }
}