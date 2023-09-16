package ru.sanctio.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sanctio.service.CreateService;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateServletTest {
    private final static String servlet = "ViewListServlet";

    @InjectMocks
    private CreateServlet createServlet;
    @Mock
    private CreateService createService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Test
    void doPost_servletSendRedirectToViewListServlet() throws ServletException, IOException {
        when(createService.createNewClient(any(), any())).thenReturn(true);

        createServlet.doPost(request, response);

        verify(response).sendRedirect(servlet);
    }

    @Test
    void doPost_servletSendError() throws ServletException, IOException {
        when(createService.createNewClient(any(), any())).thenReturn(false);

        createServlet.doPost(request, response);

        verify(response).sendError(490, "Клиент с таким адресом уже есть в базе данных");
    }

    @Test
    void doPost_servletNullPointerExceptionSendError() throws ServletException, IOException {
        when(createService.createNewClient(any(), any()))
                .thenThrow(new NullPointerException("NullPointerException"));

        createServlet.doPost(request, response);

        verify(response).sendError(490, "NullPointerException");
    }

    @Test
    void doPost_servletIllegalArgumentExceptionSendError() throws ServletException, IOException {
        when(createService.createNewClient(any(), any()))
                .thenThrow(new IllegalArgumentException("IllegalArgumentException"));

        createServlet.doPost(request, response);

        verify(response).sendError(490, "IllegalArgumentException");
    }
}