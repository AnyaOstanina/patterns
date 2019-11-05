package com.electricalweb.entities;

import java.util.ArrayList;
import java.util.List;

public class ProtocolList {

    private static final List<Protocol> protocolList = new ArrayList();

    private ProtocolList() {
    }

    public static List<Protocol> getInstance() {
        return protocolList;
    }
}
