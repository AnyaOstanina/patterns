package com.electricalweb.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProtocolList {

    private static final List<Protocol> protocolList = new ArrayList();

    private ProtocolList() {
    }

    public static Protocol getProtocol(long id) throws Exception {
        Optional<Protocol> match
                = getInstance().stream()
                .filter(e -> e.getId() == id)
                .findFirst();
        if (match.isPresent()) {
            return match.get();
        } else {
            throw new Exception("The Protocol id " + id + " not found");
        }
    }

    public static List<Protocol> getInstance() {
        return protocolList;
    }
}
