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
            gameService.setAttributes(req);
            String url = "/WEB-INF/views/game.jsp";
            gameService.forwardResponse(url, req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String url = gameService.determineUrl();
        gameService.forwardResponse(url, req, resp);
    }

    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    public void addProtocol(HttpServletRequest req, HttpServletResponse resp) {
        gameService.setAttributes(req);
        req.setAttribute("gameList", gameService.getAllGames());
        String url = "/WEB-INF/views/customerinfo.jsp";
        gameService.forwardResponse(url, req, resp);
    }


    public void addEventAction(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        String date = req.getParameter("date");
        Protocol proto = (Protocol) gameService.searchProtocolById(req);
        if(date!=null) { proto.setDate(date);}
        proto.setEvents(Event.createEventObject(req, gameService.playerList));
        gameService.forwardListEvents(req, resp, proto);
    }
}
