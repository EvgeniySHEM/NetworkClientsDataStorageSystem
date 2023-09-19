package ru.sanctio.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sanctio.service.AddressService;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteServletTest {
    private final static String servlet = "ViewListServlet";

    @InjectMocks
    private DeleteServlet deleteServlet;
    @Spy
    private DeleteServlet deleteServletSpy;
    @Mock
    private AddressService addressService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Test
    void doDelete_servletSendRedirectToViewListServlet() throws ServletException, IOException {
        deleteServlet.doDelete(request, response);

        verify(addressService, times(1)).deleteAddress(any());
        verify(response).sendRedirect(servlet);
    }

    @Test
    void doGet_servletSendRedirectToDoDelete() throws ServletException, IOException {
        deleteServletSpy = spy(deleteServlet);
        deleteServletSpy.doGet(request, response);

        verify(deleteServletSpy, times(1)).doDelete(request, response);
    }
}