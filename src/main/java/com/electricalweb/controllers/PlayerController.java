package com.electricalweb.controllers;
import com.electricalweb.entities.Player;
import com.electricalweb.entities.Team;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "PlayerController", urlPatterns = "/player")
public class PlayerController extends HttpServlet {
    PlayerService playerService = new PlayerService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "addPlayer":
                    try {
                        addPlayer(req, resp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        long idTeam = Integer.valueOf(req.getParameter("idTeam"));
        Team team = searchTeamById(idTeam);
        List<Player> players=team.getPlayers();
        req.setAttribute("players", players);
        req.setAttribute("idTeam", idTeam);
        String url = playerService.determineUrl();
        playerService.forwardResponse(url, req, resp);
    }

    private void addPlayer(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        String name = req.getParameter("playerName");
        long idTeam = Integer.valueOf(req.getParameter("teamId"));
        Team team = searchTeamById(idTeam);
        Player player = new Player(name,team.getName());
        List<Player> players=playerService.addPlayer(player, idTeam);
        req.setAttribute("players", players);
        req.setAttribute("idTeam", req.getParameter("teamId"));
        String url = playerService.determineUrl();
        playerService.forwardResponse(url, req, resp);
    }

    private Team searchTeamById(long id) {
        Team team = null;
        try {
            team = (Team) playerService.teamList.searchEntityById(id);
        } catch (Exception ex) {
            Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return team;
    }
}
