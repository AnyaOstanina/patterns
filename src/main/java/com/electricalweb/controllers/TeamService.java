package com.electricalweb.controllers;

import com.electricalweb.entities.*;

import java.util.List;

public class TeamService {
    List<Entity> teamList = TeamList.getInstance();

    public List getAllTeams() {
        return  teamList;
    }
    public List addTeam(Team team) {
        teamList.add(team);
        return teamList;
    }
}
