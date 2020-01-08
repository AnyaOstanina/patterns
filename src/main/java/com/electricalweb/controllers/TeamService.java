package com.electricalweb.controllers;

import com.electricalweb.entities.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TeamService {
    List<Entity> teamList = TeamList.getInstance();

    public List getAllTeams() {
        return  teamList;
    }

    public List<Entity> addTeam(HttpServletRequest req) {
        String name = req.getParameter("teamName");
        Team team = new Team(name);
        teamList.add(team);
        return teamList;
    }

    public String determineUrl() {
        return "/WEB-INF/views/team.jsp";
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
}
