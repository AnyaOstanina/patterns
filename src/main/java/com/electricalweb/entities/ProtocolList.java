package com.electricalweb.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProtocolList extends EntityList {

    private static final List<Entity> protocolList = new ArrayList();

    public ProtocolList() {
        super(protocolList);
    }

    public void addProtocol(Protocol proto) {
        protocolList.add(proto);
    }

    public static List<Entity> getInstance() {
        return protocolList;
    }
}
