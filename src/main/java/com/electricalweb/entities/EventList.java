package com.electricalweb.entities;

import java.util.ArrayList;
import java.util.List;

public class EventList {

    private static final List<Event> eventList = new ArrayList();

    private EventList() {
    }

    public static List<Event> getInstance() {
        return eventList;
    }
}
