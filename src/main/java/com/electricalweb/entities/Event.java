package com.electricalweb.entities;
import com.electricalweb.interfaces.Entity;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Event implements Entity {
    private long id;
    private String name;
    private String time;
    private List<Player> players;
    private static final AtomicLong counter = new AtomicLong(100);

    public Event(String Name, String Time, List Players) {
        this.name = Name;
        this.time = Time;
        this.players = Players;
        this.id = counter.incrementAndGet();
    }
    public String getName() {
        return name;
    }
    public List<Player> getPlayers() {
        return players;
    }
    public long getId() {
        return id;
    }

    public static Event createEventObject(HttpServletRequest req, PlayerList playerList) throws Exception {
        String name = req.getParameter("name");
        String time = req.getParameter("time");
        String[] players = req.getParameterValues("players[]");
        return new Event(name, time, playerList.createPlayersList(players, playerList));
    }
}
