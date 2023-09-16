package ru.sanctio.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sanctio.service.SelectService;

import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ViewListServletTest {
    @InjectMocks
    private ViewListServlet viewListServlet;
    @Mock
    private SelectService selectService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Test
    void doGet() throws ServletException, IOException {
        when(response.getWriter()).thenReturn(new PrintWriter("test.txt"));

        viewListServlet.doGet(request, response);

        verify(selectService, times(1)).getSortedData();
    }

    @Test
    void doPost() throws ServletException, IOException {
        when(response.getWriter()).thenReturn(new PrintWriter("test.txt"));

        viewListServlet.doPost(request, response);

        verify(selectService, times(1)).getSortedData();
    }
}