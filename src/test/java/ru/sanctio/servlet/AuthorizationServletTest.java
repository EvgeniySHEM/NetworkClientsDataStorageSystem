package ru.sanctio.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sanctio.service.UserService;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorizationServletTest {
    private final static String SERVLET = "/ViewListServlet";

    @InjectMocks
    private AuthorizationServlet authorizationServlet;
    @Mock
    private UserService userService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;

    @Test
    void doPost_servletForwardViewListServlet() throws ServletException, IOException {
        when(userService.checkUser(any(), any())).thenReturn(true);
        when(request.getRequestDispatcher(SERVLET)).thenReturn(requestDispatcher);

        authorizationServlet.doPost(request, response);

        verify(request, times(1)).getRequestDispatcher(SERVLET);
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    void doPost_servletSendError() throws ServletException, IOException {
        when(userService.checkUser(any(), any())).thenReturn(false);

        authorizationServlet.doPost(request, response);

        verify(response).sendError(401, "В доступе отказано : логин или пароль не найдены");
    }
}