package com.electricalweb.controllers;
import com.electricalweb.entities.*;
import com.electricalweb.interfaces.IEntity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TeamService extends Service {
    TeamList teamList = new TeamList();

    public List getAllTeams() {
        return  teamList.getInstance();
    }

    public List<IEntity> addTeam(HttpServletRequest req) {
        String name = req.getParameter("teamName");
        Team team = new Team(name);
        teamList.addTeam(team);
        return teamList.getInstance();
    }

    public String determineUrl() {
        return "/WEB-INF/views/team.jsp";
    }
}
