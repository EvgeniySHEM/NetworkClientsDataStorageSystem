package ru.sanctio.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.sanctio.model.Address;
import ru.sanctio.model.dto.AddressDTO;
import ru.sanctio.service.UpdateService;

import java.io.IOException;

@WebServlet(name = "UpdateServlet", value = "/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    @EJB
    private UpdateService updateService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String addressId = request.getParameter("addressId");

        AddressDTO address = updateService.selectAddress(addressId);
        System.out.println(address);

        request.setAttribute("address", address);

        getServletContext().getRequestDispatcher("/UpdateClient.jsp").forward(request, response);
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html; charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
//
//        String clientId = request.getParameter("clientId");
//        String addressId = request.getParameter("addressId");
//        String clientName = request.getParameter("clientName");
//        String type = request.getParameter("type");
//        String date = request.getParameter("date");
//        String ip = request.getParameter("ip");
//        String mac = request.getParameter("mac");
//        String model = request.getParameter("model");
//        String address = request.getParameter("address");
//
//        ClientEntity client = null;
//        AddressEntity addressEntity = null;
//        try {
//            client = new ClientEntity(Integer.parseInt(clientId),clientName,type,date);
//            addressEntity = new AddressEntity(Integer.parseInt(addressId),ip,mac,model,address);
//            client.addAddress(addressEntity);
//        } catch (NullPointerException | IllegalArgumentException ex) {
//            response.sendError(490, ex.getMessage());
//        }
//        if(!response.isCommitted()) {
//            if(updateBean.update(client, addressEntity)) {
//                response.sendRedirect("ViewListServlet");
//            } else {
//                response.sendError(490, "Клиент с таким адресом уже есть в базе данных");
//            }
//        }
//    }
}