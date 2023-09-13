package ru.sanctio.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.sanctio.model.Address;
import ru.sanctio.model.Client;
import ru.sanctio.model.dto.AddressDTO;
import ru.sanctio.model.dto.ClientDTO;
import ru.sanctio.service.CreateService;

import java.io.IOException;

@WebServlet(name = "Create", value = "/Create")
public class CreateServlet extends HttpServlet {
    @EJB
    private CreateService createService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String clientId = request.getParameter("clientId");
        String ip = request.getParameter("ip");
        String mac = request.getParameter("mac");
        String model = request.getParameter("model");
        String address = request.getParameter("address");

        Address newAddress = null;
        try {
            newAddress = new Address(ip, mac, model, address);
        } catch (NullPointerException | IllegalArgumentException ex) {
            response.sendError(490, ex.getMessage());
        }
        if (!response.isCommitted()) {
            if (createService.addClientAddress(newAddress, clientId)) {
                response.sendRedirect("ViewListServlet");
            } else {
                response.sendError(490, "Такой адрес уже есть в базе данных");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        ClientDTO clientDTO = new ClientDTO(request.getParameter("clientName"),
                request.getParameter("select"),
                request.getParameter("date"));

        AddressDTO addressDTO = new AddressDTO(request.getParameter("ip"),
                request.getParameter("mac"),
                request.getParameter("model"),
                request.getParameter("address"));

        try {
            if (createService.createNewClient(clientDTO, addressDTO)) {
                response.sendRedirect("ViewListServlet");
            } else {
                response.sendError(490, "Клиент с таким адресом уже есть в базе данных");
            }

        } catch (NullPointerException | IllegalArgumentException ex) {
            response.sendError(490, ex.getMessage());
        }
//            if (createService.createNewClient(newClient, newAddress)) {
//                response.sendRedirect("ViewListServlet");
//            } else {
//                response.sendError(490, "Клиент с таким адресом уже есть в базе данных");
//            }

//        Client newClient = null;
//        Address newAddress = null;
//        try {
//            newClient = new Client(clientName, selectType, date);
//            newAddress = new Address(ip, mac, model, address);
//            newClient.addAddress(newAddress);
//        } catch (NullPointerException | IllegalArgumentException ex) {
//            response.sendError(490, ex.getMessage());
//        }
//        if (!response.isCommitted()) {
//            if (createService.createNewClient(newClient, newAddress)) {
//                response.sendRedirect("ViewListServlet");
//            } else {
//                response.sendError(490, "Клиент с таким адресом уже есть в базе данных");
//            }
//        }
    }
}