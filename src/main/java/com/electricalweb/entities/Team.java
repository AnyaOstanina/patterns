package com.electricalweb.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Team implements Entity {
    private long id;
    private String name;
    private List<Player> players;
    private static final AtomicLong counter = new AtomicLong(100);

    public Team(String Name) {
        this.name = Name;
        this.players = new ArrayList<Player>();
        this.id= counter.incrementAndGet();
    }
    public String getName() {
        return name;
    }
    public long getId() {
        return id;
    }
    public List getPlayers() {
        return players;
    }
    public void setPlayer(Player player) {
         players.add(player);
    }
}
