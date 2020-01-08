package com.electricalweb.entities;
import com.electricalweb.interfaces.IEntity;
import com.electricalweb.interfaces.IEntityList;
import java.util.ArrayList;
import java.util.List;

public class ProtocolList extends EntityList implements IEntityList {

    private static final List<IEntity> protocolList = new ArrayList();

    public ProtocolList() {
        super(protocolList);
    }

    public void addProtocol(Protocol proto) {
        protocolList.add(proto);
    }

    @Override
    public List<IEntity> getInstance() {
        return protocolList;
    }
}
