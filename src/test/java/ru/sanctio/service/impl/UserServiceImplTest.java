package ru.sanctio.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sanctio.dao.DBManagerUser;
import ru.sanctio.service.SelectService;
import ru.sanctio.servlet.ViewListServlet;

import static org.junit.jupiter.api.Assertions.*;
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