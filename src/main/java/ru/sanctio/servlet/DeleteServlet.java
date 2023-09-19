package ru.sanctio.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.sanctio.service.AddressService;

import java.io.IOException;

@WebServlet(name = "Delete", value = "/Delete")
public class DeleteServlet extends HttpServlet {
    @EJB
    private AddressService addressService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDelete(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String addressId = request.getParameter("addressId");
        addressService.deleteAddress(addressId);
        response.sendRedirect("ViewListServlet");
    }
}