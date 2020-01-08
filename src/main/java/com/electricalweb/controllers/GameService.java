package com.electricalweb.controllers;
import com.electricalweb.entities.*;
import com.electricalweb.interfaces.IEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GameService extends Service {
    public GameService() {
        super("/WEB-INF/views/game.jsp");
    }
    public ProtocolList protocolList =  new ProtocolList();
    public  PlayerList playerList = new PlayerList();
    public  GameList gameList = new GameList();
    public List<IEntity> getAllGames() {
        return gameList.getInstance();
    }

    public List<IEntity> getAllPlayers() {
        return playerList.getInstance();
    }

    public IEntity searchProtocolById(HttpServletRequest req) throws Exception {
        long idProtocol = Integer.valueOf(req.getParameter("idProtocol"));
        protocolList.searchEntityById(idProtocol);
        return protocolList.searchEntityById(idProtocol);
    }

    public void addProtocol(Protocol proto) {
        protocolList.addProtocol(proto);
    }

    public IEntity getPlayer(long id) throws Exception {
        return playerList.searchEntityById(id);
    }

    public void setAttributes(HttpServletRequest req) {
        String name = req.getParameter("gameName");
        String date = req.getParameter("date");
        String idCustomer = req.getParameter("idCustomer");
        Protocol protocol = new Protocol(date, name, Integer.valueOf(idCustomer));
        addProtocol(protocol);
        req.setAttribute("protocols", getAllProtocols());
        req.setAttribute("players", getAllPlayers());
        req.setAttribute("idCustomer", idCustomer);
        req.setAttribute("protocol", protocol);
    }

    public void forwardListEvents(HttpServletRequest req, HttpServletResponse resp, Protocol protocol) {
        String url = determineUrl();
        String idCustomer = req.getParameter("idCustomer");
        req.setAttribute("protocol", protocol);
        req.setAttribute("players", getAllPlayers());
        req.setAttribute("idCustomer", idCustomer);
        forwardResponse(url, req, resp);
    }

    public List<IEntity> getAllProtocols() {
        return protocolList.getInstance();
    }
}
