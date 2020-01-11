package com.electricalweb.entities;
import com.electricalweb.interfaces.IEntity;
import com.electricalweb.interfaces.IEntityList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityList implements IEntityList {
    private static List<IEntity> entityList = new ArrayList();

    public EntityList(List entityList){
        this.entityList=entityList;
    }

    public List<IEntity> getInstance() {
        return entityList;
    }

    public IEntity searchEntityById(long id) throws Exception {
        Optional<IEntity> match
                = this.getInstance().stream()
                .filter(e  -> e.getId() == id)
                .findFirst();
        if (match.isPresent()) {
            return match.get();
        } else {
            throw new Exception("The Element id " + id + " not found");
        }
    }
}
