package com.electricalweb.controllers;

import com.electricalweb.entities.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class GameService {
    List<Entity> eventList = EventList.getInstance();
    public ProtocolList protocolList =  new ProtocolList();
    public  PlayerList playerList = new PlayerList();
    List<Entity> gameList = GameList.getInstance();
    public List<Entity> getAllGames() {
        return gameList;
    }

    public List<Entity> getAllPlayers() {
        return playerList.getInstance();
    }

    public Entity searchProtocolById(HttpServletRequest req) throws Exception {
        long idProtocol = Integer.valueOf(req.getParameter("idProtocol"));
        protocolList.getProtocol(idProtocol);
        return protocolList.getProtocol(idProtocol);
    }

    public void addProtocol(Protocol proto) {
        protocolList.addProtocol(proto);
    }

    public Entity getPlayer(long id) throws Exception {
        return playerList.searchEntityById(id);
    }

    public List<Entity> getAllProtocols() {
        return protocolList.getInstance();
    }
}
