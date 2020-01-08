package com.electricalweb.controllers;

import com.electricalweb.entities.*;
import com.electricalweb.interfaces.Entity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class GameService {
    public ProtocolList protocolList =  new ProtocolList();
    public  PlayerList playerList = new PlayerList();
    public  GameList gameList = new GameList();
    public List<Entity> getAllGames() {
        return gameList.getInstance();
    }

    public List<Entity> getAllPlayers() {
        return playerList.getInstance();
    }

    public Entity searchProtocolById(HttpServletRequest req) throws Exception {
        long idProtocol = Integer.valueOf(req.getParameter("idProtocol"));
        protocolList.searchEntityById(idProtocol);
        return protocolList.searchEntityById(idProtocol);
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
