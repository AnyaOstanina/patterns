package com.electricalweb.controllers;
import com.electricalweb.entities.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PlayerService extends Service {
    PlayerList playerList = new PlayerList();
    TeamList teamList = new TeamList();

    public List addPlayer(Player player, long id) throws Exception {
        playerList.addPlayer(player);
        Team team = (Team) teamList.searchEntityById(id);
        team.setPlayer(player);
        return team.getPlayers();
    }

    public String determineUrl() {
        return "/WEB-INF/views/player.jsp";
    }
}
