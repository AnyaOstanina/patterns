package com.electricalweb.controllers;

import com.electricalweb.entities.*;
import java.util.List;
import java.util.Optional;

public class GameService {
    List<Event> eventList = EventList.getInstance();
    List<Protocol> protocolList = ProtocolList.getInstance();
    List<Player> playerList = PlayerList.getInstance();
    List<Game> gameList = GameList.getInstance();
    public List<Event> getAllEvents() {
        return eventList;
    }
    public List<Game> getAllGames() {
        return gameList;
    }

    public List<Player> getAllPlayers() {
        return playerList;
    }


    public List addEvent(Event event) {
        eventList.add(event);
        return eventList;
    }

    public void addProtocol(Protocol proto) {
        protocolList.add(proto);
    }

    public Player getPlayer(long id) throws Exception {
        Optional<Player> match
                = playerList.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
        if (match.isPresent()) {
            return match.get();
        } else {
            throw new Exception("The Game id " + id + " not found");
        }
    }

    public List<Protocol> getAllProtocols() {
        return protocolList;
    }
}
