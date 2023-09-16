package ru.sanctio.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sanctio.dao.DBManagerUser;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private DBManagerUser dbManagerUser;

    @Test
    void checkUser() {
        userService.checkUser(any(), any());

        verify(dbManagerUser, times(1)).checkUser(any(), any());
    }
}