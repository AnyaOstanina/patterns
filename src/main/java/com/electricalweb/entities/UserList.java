package com.electricalweb.entities;
import java.util.ArrayList;
import java.util.List;

public class UserList {
    private static final List<User> USER_LIST = new ArrayList();

    private UserList(){
    }

    static {
        USER_LIST.add(new User("admin", "admin"));
    }

    public static List <User> getInstance(){
        return USER_LIST;
    }
}
