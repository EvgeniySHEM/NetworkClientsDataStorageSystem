package ru.sanctio.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.sanctio.model.dto.AddressDTO;
import ru.sanctio.service.AddressService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ViewListServlet", value = "/ViewListServlet")
public class ViewListServlet extends HttpServlet {
    @EJB
    private AddressService addressService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendResponse(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sendResponse(request, response);
    }

    private void sendResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        List<AddressDTO> filteredList = addressService.getSortedData();

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<form action=\"Registration.jsp\" method=\"get\" align=\"center\" autocomplete=\"off\">" +
                "<input type=\"submit\" value=\"Registration new client\"></form>");

        out.println("<table align=\"center\" cellpadding=\"5\" border=\"5px double #000\" cellspacing=\"0\">");
        out.println("<tr>");
        out.println("<th>clientId</th>");
        out.println("<th>clientName</th>");
        out.println("<th>type</th>");
        out.println("<th>added</th>");
        out.println("<th>ip</th>");
        out.println("<th>mac</th>");
        out.println("<th>model</th>");
        out.println("<th>address</th>");
        out.println("<th></th>");
        out.println("<th></th>");
        out.println("</tr>");
        for (AddressDTO address : filteredList) {
            out.println("<tr>");
            out.println("<td>" + address.client().clientId() + "</td>");
            out.println("<td>" + address.client().clientName() + "</td>");
            out.println("<td>" + address.client().type() + "</td>");
            out.println("<td>" + address.client().added() + "</td>");
            out.println("<td>" + address.ip() + "</td>");
            out.println("<td>" + address.mac() + "</td>");
            out.println("<td>" + address.model() + "</td>");
            out.println("<td>" + address.address() + "</td>");
            out.println("<td><form action=\"UpdateServlet\" method=\"get\" align=\"center\">");
            out.println("<input type=\"hidden\" name=\"addressId\" value=\"" + address.id() + "\">");
            out.println("<input type=\"submit\" value=\"Update\"></form></td>");
            out.println("<td><form action=\"Delete\" method=\"delete\" align=\"center\">");
            out.println("<input type=\"hidden\" name=\"addressId\" value=\"" + address.id() + "\">");
            out.println("<input type=\"submit\" value=\"Delete\"></form></td>");
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body></html>");
    }
}