package com.electricalweb.entities;

import com.electricalweb.interfaces.Entity;
import com.electricalweb.interfaces.IEntityList;

import java.util.ArrayList;
import java.util.List;

public class EventList extends EntityList implements IEntityList {

    private static final List<Entity> eventList = new ArrayList();

    public EventList() {
        super(eventList);
    }

    @Override
    public List<Entity> getInstance() {
        return eventList;
    }
}
