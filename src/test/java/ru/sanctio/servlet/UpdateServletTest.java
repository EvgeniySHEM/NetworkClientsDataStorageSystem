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
import ru.sanctio.model.dto.AddressDTO;
import ru.sanctio.model.dto.ClientDTO;
import ru.sanctio.service.AddressService;
import ru.sanctio.service.ClientService;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateServletTest {
    private final static String SERVLET = "ViewListServlet";
    private final static String JSP = "/UpdateClient.jsp";

    @InjectMocks
    private UpdateServlet updateServlet;
    @Mock
    private AddressService addressService;
    @Mock
    private ClientService clientService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;


    @Test
    void doGet_servletShouldForwardUpdateClientJsp() throws ServletException, IOException {
        when(request.getRequestDispatcher(JSP)).thenReturn(requestDispatcher);

        updateServlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(JSP);
        verify(requestDispatcher, times(1)).forward(request, response);
    }

    @Test
    void doGet_servletShouldGetAndAddAddressDto() throws ServletException, IOException {
        AddressDTO addressDTO = new AddressDTO(0, "ip", "mac", "model", "address",
                new ClientDTO(0, "name", "type", "added"));

        when(addressService.selectAddressById(any())).thenReturn(addressDTO);
        when(request.getRequestDispatcher(JSP)).thenReturn(requestDispatcher);

        updateServlet.doGet(request, response);

        verify(request, times(1)).getParameter(any());
        verify(addressService, times(1)).selectAddressById(request.getParameter(anyString()));
        verify(request, times(1)).setAttribute(anyString(), eq(addressDTO));
    }

    @Test
    void doPost_servletShouldSendRedirectViewListServlet() throws ServletException, IOException {
        when(clientService.updateClient(any(), any())).thenReturn(true);
        when(request.getParameter(anyString())).thenReturn("0");

        updateServlet.doPost(request, response);

        verify(response, times(1)).sendRedirect(SERVLET);
    }

    @Test
    void doPost_servletShouldSendError() throws ServletException, IOException {
        when(clientService.updateClient(any(), any())).thenReturn(false);
        when(request.getParameter(anyString())).thenReturn("0");

        updateServlet.doPost(request, response);

        verify(response, times(1)).
                sendError(490, "Клиент с таким адресом уже есть в базе данных");
    }

    @Test
    void doPost_servletNullPointerExceptionSendError() throws ServletException, IOException {
        when(request.getParameter(anyString())).thenReturn("0");

        when(clientService.updateClient(any(), any()))
                .thenThrow(new NullPointerException("NullPointerException"));

        updateServlet.doPost(request, response);

        verify(response).sendError(490, "NullPointerException");
    }

    @Test
    void doPost_servletIllegalArgumentExceptionSendError() throws ServletException, IOException {
        when(request.getParameter(anyString())).thenReturn("0");

        when(clientService.updateClient(any(), any()))
                .thenThrow(new IllegalArgumentException("IllegalArgumentException"));

        updateServlet.doPost(request, response);

        verify(response).sendError(490, "IllegalArgumentException");
    }
}