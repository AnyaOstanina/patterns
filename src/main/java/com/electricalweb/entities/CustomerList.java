package com.electricalweb.entities;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerList extends EntityList {
    private static final List<Customer> customerList = new ArrayList();

    public CustomerList(){
        super(customerList);
    }

    static {
        customerList.add(new Customer("admin", "admin"));
    }

    public static List <Customer> getInstance(){
        return customerList;
    }
}
