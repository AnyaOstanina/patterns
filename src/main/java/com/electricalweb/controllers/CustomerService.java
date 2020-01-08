package com.electricalweb.controllers;

import com.electricalweb.entities.*;

import java.util.List;
import java.util.Optional;

public class CustomerService {
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
}
