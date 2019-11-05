package com.electricalweb.entities;
import java.util.concurrent.atomic.AtomicLong;

public class Player {
    private long id;
    private String name;
    private String team;
    private static final AtomicLong counter = new AtomicLong(100);

    public Player(String Name, String Team) {
        this.name = Name;
        this.team = Team;
        this.id= counter.incrementAndGet();
    }
    public String getName() {
        return name;
    }
    public String getTeam() {
        return team;
    }
    public long getId() {
        return id;
    }
}
