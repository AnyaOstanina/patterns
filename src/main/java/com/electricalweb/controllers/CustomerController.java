package com.electricalweb.controllers;
import com.electricalweb.entities.*;
import com.electricalweb.interfaces.IEntity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", urlPatterns = "/processcustomer")
public class CustomerController extends HttpServlet {
    GameService gameService = new GameService();
    CustomerService customerService = new CustomerService();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Customer customer = customerService.getCustomerByPassword(request, response);
        if(customer != null) {
            setAttributes(request, customer);
            String url = customerService.determineUrl();
            customerService.forwardResponse(url, request, response);
        } else {
            response.sendRedirect("http://localhost:8080/Customer_Application_war/");
        }
    }

    private void setAttributes(HttpServletRequest request, Customer customer) {
        List<IEntity> gameList = gameService.gameList.getInstance();
        List<IEntity> protocols = gameService.getAllProtocols();
        request.setAttribute("protocols", protocols);
        request.setAttribute("idCustomer", customer.getId());
        request.setAttribute("gameList", gameList);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String url = "/WEB-INF/views/customerinfo.jsp";
        List<IEntity> protocols = gameService.getAllProtocols();
        req.setAttribute("protocols", protocols);
        customerService.forwardResponse(url, req, resp);
    }
}
