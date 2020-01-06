package com.electricalweb.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.electricalweb.entities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "GameController", urlPatterns = "/game")
public class GameController extends HttpServlet {
    GameService gameService = new GameService();
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "addEvent":
                    try {
                        addEventAction(req, resp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "addProtocol":
                    addProtocol(req, resp);
                    break;
            }
        } else {
            setAttributes(req);
            String url = "/WEB-INF/views/game.jsp";
            forwardResponse(url, req, resp);
        }
    }

    private void setAttributes(HttpServletRequest req) {
        String name = req.getParameter("gameName");
        String date = req.getParameter("date");
        String idCustomer = req.getParameter("idCustomer");
        Protocol protocol = new Protocol(date, name, Integer.valueOf(idCustomer));
        gameService.addProtocol(protocol);
        req.setAttribute("protocols", gameService.getAllProtocols());
        req.setAttribute("players", gameService.getAllPlayers());
        req.setAttribute("idCustomer", idCustomer);
        req.setAttribute("protocol", protocol);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String url = determineUrl();
        forwardResponse(url, req, resp);
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public void addProtocol(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        setAttributes(req);
        req.setAttribute("gameList", gameService.getAllGames());
        String url = "/WEB-INF/views/customerinfo.jsp";
        forwardResponse(url, req, resp);
    }


    public String determineUrl() {
        return "/WEB-INF/views/game.jsp";
    }

    public void forwardResponse(String url, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void forwardListEvents(HttpServletRequest req, HttpServletResponse resp, Protocol protocol)
            throws ServletException, IOException {
        String url = determineUrl();
        String idCustomer = req.getParameter("idCustomer");
        req.setAttribute("protocol", protocol);
        req.setAttribute("players", gameService.getAllPlayers());
        req.setAttribute("idCustomer", idCustomer);
        forwardResponse(url, req, resp);
    }

    public void addEventAction(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        String date = req.getParameter("date");
        Protocol proto = (Protocol) searchProtocolById(req, resp);
        if(date!=null) { proto.setDate(date);}
        proto.setEvents(сreateEventObject(req));
        forwardListEvents(req, resp, proto);
    }

    private Event сreateEventObject(HttpServletRequest req) throws Exception {
        String name = req.getParameter("name");
        String time = req.getParameter("time");
        String[] players = req.getParameterValues("players[]");
        return new Event(name, time, createPlayersList(players));
    }

    private List<Entity> createPlayersList(String[] players) throws Exception {
        List<Entity> playersList = new ArrayList<Entity>();
        for(int i=0; i < players.length; i++) {
            long playerId =  Long.valueOf(players[i]);
            playersList.add(gameService.getPlayer(playerId));
        }
        return playersList;
    }

    public Entity searchProtocolById(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long idProtocol = Integer.valueOf(req.getParameter("idProtocol"));
        Entity protocol = null;
        try {
            protocol = ProtocolList.getProtocol(idProtocol);
        } catch (Exception ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return protocol;
//        req.setAttribute("protocol", protocol);
//        req.setAttribute("action", "edit");
//        forwardListEvents(req, resp, protocol.getEvents());
    }
}
