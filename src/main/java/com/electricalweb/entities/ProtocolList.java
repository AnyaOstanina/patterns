package com.electricalweb.entities;
import com.electricalweb.interfaces.Entity;
import com.electricalweb.interfaces.IEntityList;
import java.util.ArrayList;
import java.util.List;

public class ProtocolList extends EntityList implements IEntityList {

    private static final List<Entity> protocolList = new ArrayList();

    public ProtocolList() {
        super(protocolList);
    }

    public void addProtocol(Protocol proto) {
        protocolList.add(proto);
    }

    @Override
    public List<Entity> getInstance() {
        return protocolList;
    }
}
