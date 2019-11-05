package com.electricalweb.controllers;

import com.electricalweb.entities.Player;
import com.electricalweb.entities.Protocol;
import com.electricalweb.entities.Team;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TeamController", urlPatterns = "/team")
public class TeamController extends HttpServlet {
    TeamService teamService = new TeamService();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "addTeam":
                    addTeam(req, resp);
                    break;
            }
        } else {
            String url = determineUrl();
            List<Team> teams = new ArrayList<Team>();
            teams=teamService.getAllTeams();
            req.setAttribute("teams", teams);
            forwardResponse(url, req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = determineUrl();
        List<Team> teams = new ArrayList<Team>();
        teams=teamService.getAllTeams();
        req.setAttribute("teams", teams);
        forwardResponse(url, req, resp);
    }

    private void addTeam(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("teamName");
        String date = req.getParameter("date");
        Team team = new Team(name);
        List<Team> teams = new ArrayList<Team>();
        teams=teamService.addTeam(team);
        req.setAttribute("teams", teams);
        String url = determineUrl();
        forwardResponse(url, req, resp);
    }


    private String determineUrl() {
        return "/WEB-INF/views/team.jsp";
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
}
