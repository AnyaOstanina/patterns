package com.electricalweb.controllers;
import com.electricalweb.interfaces.IEntity;
import com.electricalweb.entities.Team;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "TeamController", urlPatterns = "/team")
public class TeamController extends HttpServlet {
    TeamService teamService = new TeamService();
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "addTeam":
                    addTeam(req, resp);
                    break;
            }
        } else {
            String url = teamService.determineUrl();
            List<Team> teams = teamService.getAllTeams();
            req.setAttribute("teams", teams);
            teamService.forwardResponse(url, req, resp);
        }
    }

    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String url = teamService.determineUrl();
        List<Team> teams = teamService.getAllTeams();
        req.setAttribute("teams", teams);
        teamService.forwardResponse(url, req, resp);
    }

    public void addTeam(HttpServletRequest req, HttpServletResponse resp) {
        List<IEntity> teams = teamService.addTeam(req);
        req.setAttribute("teams", teams);
        String url = teamService.determineUrl();
        teamService.forwardResponse(url, req, resp);
    }
}
