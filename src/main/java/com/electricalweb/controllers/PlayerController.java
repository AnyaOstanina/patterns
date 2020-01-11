package com.electricalweb.controllers;
import com.electricalweb.entities.Player;
import com.electricalweb.entities.Team;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
        Team team = playerService.searchTeamById(idTeam);
        List<Player> players=team.getPlayers();
        setAttributes(req, idTeam,players);
        String url = playerService.determineUrl();
        playerService.forwardResponse(url, req, resp);
    }

    private void setAttributes(HttpServletRequest req, long idTeam, List<Player> players) {
        req.setAttribute("players", players);
        req.setAttribute("idTeam", idTeam);
    }

    private void addPlayer(HttpServletRequest req, HttpServletResponse resp)
            throws Exception {
        String name = req.getParameter("playerName");
        long idTeam = Integer.valueOf(req.getParameter("teamId"));
        Team team = playerService.searchTeamById(idTeam);
        Player player = new Player(name,team.getName());
        List<Player> players=playerService.addPlayer(player, idTeam);
        setAttributes(req, idTeam, players);
        String url = playerService.determineUrl();
        playerService.forwardResponse(url, req, resp);
    }
}
