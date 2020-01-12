package com.electricalweb.controllers;
import com.electricalweb.entities.*;
import com.electricalweb.interfaces.IEntity;
import com.electricalweb.services.UserService;
import com.electricalweb.services.GameService;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = "/processcustomer")
public class UserController extends HttpServlet {
    GameService gameService = new GameService();
    UserService userService = new UserService();
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Customer customer = userService.getCustomerByPassword(request, response);
        if(customer != null) {
            setAttributes(request, customer);
            String url = userService.determineUrl();
            userService.forwardResponse(url, request, response);
        } else {
            response.sendRedirect("http://localhost:8080/Customer_Application_war/");
        }
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setAttributes(HttpServletRequest request, Customer customer) {
        List<IEntity> gameList = gameService.gameList.getInstance();
        List<IEntity> protocols = gameService.getAllProtocols();
        request.setAttribute("protocols", protocols);
        request.setAttribute("idCustomer", customer.getId());
        request.setAttribute("gameList", gameList);
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String url = "/WEB-INF/views/customerinfo.jsp";
        List<IEntity> protocols = gameService.getAllProtocols();
        req.setAttribute("protocols", protocols);
        userService.forwardResponse(url, req, resp);
    }
}
