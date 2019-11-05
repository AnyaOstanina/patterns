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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
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
            String name = req.getParameter("gameName");
            String date = req.getParameter("date");
            String idCustomer = req.getParameter("idCustomer");
            Protocol protocol = new Protocol(date, name, Integer.valueOf(idCustomer));
            gameService.addProtocol(protocol);
            req.setAttribute("protocols", gameService.getAllProtocols());
            req.setAttribute("players", gameService.getAllPlayers());
            req.setAttribute("idCustomer", idCustomer);
            req.setAttribute("protocol", protocol);
            String url = "/WEB-INF/views/game.jsp";
            forwardResponse(url, req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String url = determineUrl();
        forwardResponse(url, req, resp);
    }

    private void addProtocol(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("gameName");
        String date = req.getParameter("date");
        String idCustomer = req.getParameter("idCustomer");
        Protocol protocol = new Protocol(date, name, Integer.valueOf(idCustomer));
        gameService.addProtocol(protocol);
        req.setAttribute("protocols", gameService.getAllProtocols());
        req.setAttribute("players", gameService.getAllPlayers());
        req.setAttribute("gameList", gameService.getAllGames());
        req.setAttribute("idCustomer", idCustomer);
        req.setAttribute("protocol", protocol);
        String url = "/WEB-INF/views/customerinfo.jsp";
        forwardResponse(url, req, resp);
    }


    private String determineUrl() {
        return "/WEB-INF/views/game.jsp";
    }

    private void forwardResponse(String url, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void forwardListEvents(HttpServletRequest req, HttpServletResponse resp, Protocol protocol)
            throws ServletException, IOException {
        String url = determineUrl();
        Protocol proto = searchProtocolById(req, resp);
        String idCustomer = req.getParameter("idCustomer");
        req.setAttribute("protocol", protocol);
        req.setAttribute("players", gameService.getAllPlayers());
        req.setAttribute("idCustomer", idCustomer);
        forwardResponse(url, req, resp);
    }

    private void addEventAction(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        String name = req.getParameter("name");
        String time = req.getParameter("time");
        String date = req.getParameter("date");
        String[] players = req.getParameterValues("players[]");
        List<Player> playersList = new ArrayList<Player>();
        for(int i=0; i < players.length; i++) {
            long playerId =  Integer.valueOf(players[i]);
            playersList.add(gameService.getPlayer(playerId));
        }
        Event event = new Event(name, time, playersList);
        Protocol proto = searchProtocolById(req, resp);
        if(date!=null) { proto.setDate(date);}
        proto.setEvents(event);
        forwardListEvents(req, resp, proto);
    }



    private Protocol searchProtocolById(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        long idProtocol = Integer.valueOf(req.getParameter("idProtocol"));
        Protocol protocol = null;
        try {
            protocol = gameService.getProtocol(idProtocol);
        } catch (Exception ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return protocol;
//        req.setAttribute("protocol", protocol);
//        req.setAttribute("action", "edit");
//        forwardListEvents(req, resp, protocol.getEvents());
    }

    private Player searchPlayerById(HttpServletRequest req, HttpServletResponse resp, long idPlayer)
            throws ServletException, IOException {
        Player player = null;
        try {
            player = gameService.getPlayer(idPlayer);
        } catch (Exception ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return player;
//        req.setAttribute("protocol", protocol);
//        req.setAttribute("action", "edit");
//        forwardListEvents(req, resp, protocol.getEvents());
    }
}
