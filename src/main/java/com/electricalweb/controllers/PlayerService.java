package com.electricalweb.controllers;

import com.electricalweb.entities.*;

import java.util.List;
import java.util.Optional;

public class PlayerService {
    PlayerList playerList = new PlayerList();
    TeamList teamList = new TeamList();


    public List getAllPlayers() {
        return  playerList.getInstance();
    }

    public List addPlayer(Player player, long id) throws Exception {
        playerList.addPlayer(player);
        Team team = (Team) teamList.searchEntityById(id);
        team.setPlayer(player);
        return team.getPlayers();
    }
}
