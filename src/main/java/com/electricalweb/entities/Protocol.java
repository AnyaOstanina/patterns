package com.electricalweb.entities;
import com.electricalweb.interfaces.IEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Protocol  implements IEntity {
    private long id;
    private String date;
    private String game;
    private List<Event> events;
    private long creatorId;
    private static final AtomicLong counter = new AtomicLong(100);

    public Protocol(String Date, String Game, long CreatorId) {
        this.date = Date;
        this.game = Game;
        this.events = new ArrayList<Event>();
        this.creatorId = CreatorId;
        this.id= counter.incrementAndGet();
    }
    public String getDate() {
        return date;
    }
    public void setDate(String Date) {
         date = Date;
    }
    public String getGame() {
        return game;
    }
    public long getCreatorId() {
        return creatorId;
    }

    public void setEvents(Event event) {
        events.add(event);
    }
    public List getEvents() {
        return events;
    }
    public long getId() {
        return id;
    }
}
