package com.electricalweb.services;
import com.electricalweb.entities.*;
import com.electricalweb.services.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserService extends Service {
    public UserService() {
        super("/WEB-INF/views/customerinfo.jsp");
    }

    List<Customer> customerList = CustomerList.getInstance();
    GameList gameList = new GameList();

    public List getAllCustomers() {
        return  customerList;
    }

    public List addCustomer(Customer customer) {
        customerList.add(customer);
        return customerList;
    }

    public Customer getCustomerByPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Customer customer = null;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Optional<Customer> match
                = customerList.stream()
                .filter(e -> e.getLogin().equalsIgnoreCase(login) && e.getLogin().equalsIgnoreCase(password))
                .findFirst();
        if (match.isPresent()) {
            customer = match.get();
        } else {
            response.sendRedirect("http://localhost:8080/Customer_Application_war/");
        }
        return customer;
    }
}
