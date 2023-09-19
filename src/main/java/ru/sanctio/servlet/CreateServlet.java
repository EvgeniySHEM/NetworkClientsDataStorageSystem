package ru.sanctio.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.sanctio.model.dto.AddressDTO;
import ru.sanctio.model.dto.ClientDTO;
import ru.sanctio.service.ClientService;

import java.io.IOException;

@WebServlet(name = "Create", value = "/Create")
public class CreateServlet extends HttpServlet {

    @EJB
    private ClientService clientService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        ClientDTO clientDTO = new ClientDTO(0,
                request.getParameter("clientName"),
                request.getParameter("select"),
                request.getParameter("date"));

        AddressDTO addressDTO = new AddressDTO(0, request.getParameter("ip"),
                request.getParameter("mac"),
                request.getParameter("model"),
                request.getParameter("address"),
                clientDTO);

        try {
            if (clientService.createNewClient(clientDTO, addressDTO)) {
                response.sendRedirect("ViewListServlet");
            } else {
                response.sendError(490, "Клиент с таким адресом уже есть в базе данных");
            }

        } catch (NullPointerException | IllegalArgumentException ex) {
            response.sendError(490, ex.getMessage());
        }
    }
}