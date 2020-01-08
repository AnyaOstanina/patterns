package com.electricalweb.controllers;
import com.electricalweb.entities.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CustomerService extends Service {
    List<Customer> customerList = CustomerList.getInstance();
    GameList gameList = new GameList();

    public List getAllCustomers() {
        return  customerList;
    }

    public List addCustomer(Customer customer) {
        customerList.add(customer);
        return customerList;
    }

    public Customer findCustomer(String login, String password) throws Exception {
        Optional<Customer> match
                = customerList.stream()
                .filter(e -> e.getLogin().equalsIgnoreCase(login) && e.getLogin().equalsIgnoreCase(password))
                .findFirst();
        if (match.isPresent()) {
            return match.get();
        } else {
            throw new Exception("not found");
        }
    }

    public String determineUrl() {
        return "/WEB-INF/views/customerinfo.jsp";
    }
}
