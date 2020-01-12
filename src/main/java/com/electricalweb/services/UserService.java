package com.electricalweb.services;
import com.electricalweb.entities.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserService extends Service {
    public UserService() {
        super("/WEB-INF/views/customerinfo.jsp");
    }

    List<User> userList = UserList.getInstance();
    GameList gameList = new GameList();

    public List getAllCustomers() {
        return userList;
    }

    public List addCustomer(User user) {
        userList.add(user);
        return userList;
    }

    public User getCustomerByPassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = null;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Optional<User> match
                = userList.stream()
                .filter(e -> e.getLogin().equalsIgnoreCase(login) && e.getLogin().equalsIgnoreCase(password))
                .findFirst();
        if (match.isPresent()) {
            user = match.get();
        } else {
            response.sendRedirect("http://localhost:8080/Customer_Application_war/");
        }
        return user;
    }
}
