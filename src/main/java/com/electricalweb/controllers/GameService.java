package com.electricalweb.controllers;

import com.electricalweb.entities.*;
import java.util.List;
import java.util.Optional;

public class GameService {
    List<Entity> eventList = EventList.getInstance();
    List<Entity> protocolList = ProtocolList.getInstance();
    List<Entity> playerList = PlayerList.getInstance();
    List<Entity> gameList = GameList.getInstance();
    public List<Entity> getAllEvents() {
        return eventList;
    }
    public List<Entity> getAllGames() {
        return gameList;
    }

    public List<Entity> getAllPlayers() {
        return playerList;
    }


    public List addEvent(Event event) {
        eventList.add(event);
        return eventList;
    }

    public void addProtocol(Protocol proto) {
        protocolList.add(proto);
    }

    public Entity getPlayer(long id) throws Exception {
        Optional<Entity> match
                = playerList.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
        if (match.isPresent()) {
            return match.get();
        } else {
            throw new Exception("The Game id " + id + " not found");
        }
    }

    public List<Entity> getAllProtocols() {
        return protocolList;
    }
}
