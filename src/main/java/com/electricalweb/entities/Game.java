package com.electricalweb.entities;

import com.electricalweb.interfaces.Entity;

import java.util.concurrent.atomic.AtomicLong;

public class Game implements Entity {
    private long id;
    private String name;
    private static final AtomicLong counter = new AtomicLong(100);

    public Game(String Name) {
        this.name = Name;
        this.id= counter.incrementAndGet();
    }
    public String getName() {
        return name;
    }
    public long getId() {
        return id;
    }
}
