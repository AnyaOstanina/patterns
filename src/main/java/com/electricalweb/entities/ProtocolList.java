package com.electricalweb.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProtocolList extends EntityList {

    private static final List<Entity> protocolList = new ArrayList();

    public ProtocolList() {
        super(protocolList);
    }

    public static Entity getProtocol(long id) throws Exception {
        Optional<Entity> match
                = getInstance().stream()
                .filter(e -> e.getId() == id)
                .findFirst();
        if (match.isPresent()) {
            return match.get();
        } else {
            throw new Exception("The Protocol id " + id + " not found");
        }
    }

    public void addProtocol(Protocol proto) {
        protocolList.add(proto);
    }

    public static List<Entity> getInstance() {
        return protocolList;
    }
}
