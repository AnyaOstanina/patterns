package com.electricalweb.entities;

import java.util.concurrent.atomic.AtomicLong;

public class Customer implements Entity {
    private long id;
    private String login;
    private String password;
    private static final AtomicLong counter = new AtomicLong(100);
    public Customer(String Login, String Password) {
        this.id= counter.incrementAndGet();
        this.login = Login;
        this.password = Password;
    }

    public String getPassword() {
        return password;
    }
    public long getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
}
