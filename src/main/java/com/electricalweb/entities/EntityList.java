package com.electricalweb.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityList {
    private static List<Entity> entityList = new ArrayList();

    public EntityList(List entityList){
        this.entityList=entityList;
    }

    public static List<Entity> getInstance() {
        return entityList;
    }

    public static Entity searchEntityById(long id) throws Exception {
        Optional<Entity> match
                = getInstance().stream()
                .filter(e  -> e.getId() == id)
                .findFirst();
        if (match.isPresent()) {
            return match.get();
        } else {
            throw new Exception("The Game id " + id + " not found");
        }
    }
}
