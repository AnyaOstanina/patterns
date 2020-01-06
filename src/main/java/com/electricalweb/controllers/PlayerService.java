package com.electricalweb.controllers;

import com.electricalweb.entities.*;

import java.util.List;
import java.util.Optional;

public class PlayerService {
    List<Entity> playerList = PlayerList.getInstance();
    List<Entity> teamList = TeamList.getInstance();


    public List getAllPlayers() {
        return  playerList;
    }

    public List addPlayer(Player player, long id) throws Exception {
        playerList.add(player);
        Team team = (Team) TeamList.searchEntityById(id);
        team.setPlayer(player);
        return team.getPlayers();
    }
}
