package com.electricalweb.controllers;
import com.electricalweb.entities.*;
import com.electricalweb.interfaces.IEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class TeamService extends Service {
    public TeamService() {
        super("/WEB-INF/views/team.jsp");
    }
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
}
