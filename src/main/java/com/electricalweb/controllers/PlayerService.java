package com.electricalweb.controllers;
import com.electricalweb.entities.*;
import java.util.List;

public class PlayerService {
    PlayerList playerList = new PlayerList();
    TeamList teamList = new TeamList();

    public List addPlayer(Player player, long id) throws Exception {
        playerList.addPlayer(player);
        Team team = (Team) teamList.searchEntityById(id);
        team.setPlayer(player);
        return team.getPlayers();
    }
}
