package com.electricalweb.controllers;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.electricalweb.entities.*;
import java.io.IOException;

@WebServlet(name = "GameController", urlPatterns = "/game")
public class GameController extends HttpServlet {
    GameService gameService = new GameService();
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
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

    public void addProtocol(HttpServletRequest req, HttpServletResponse resp) {
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

    public void forwardListEvents(HttpServletRequest req, HttpServletResponse resp, Protocol protocol) {
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
        Protocol proto = (Protocol) gameService.searchProtocolById(req);
        if(date!=null) { proto.setDate(date);}
        proto.setEvents(Event.createEventObject(req, gameService.playerList));
        forwardListEvents(req, resp, proto);
    }
}
