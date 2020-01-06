package com.electricalweb.entities;

import java.util.ArrayList;
import java.util.List;

public class EventList extends EntityList{

    private static final List<Entity> eventList = new ArrayList();

    public EventList() {
        super(eventList);
    }

    public static List<Entity> getInstance() {
        return eventList;
    }
}
