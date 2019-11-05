package com.electricalweb.controllers;

import com.electricalweb.entities.Player;
import com.electricalweb.entities.PlayerList;
import com.electricalweb.entities.Team;
import com.electricalweb.entities.TeamList;

import java.util.List;
import java.util.Optional;

public class PlayerService {
    List<Player> playerList = PlayerList.getInstance();
    List<Team> teamList = TeamList.getInstance();


    public List getAllPlayers() {
        return  playerList;
    }

    public List addPlayer(Player player, long id) throws Exception {
        playerList.add(player);
        Team team = getTeam(id);
        team.setPlayer(player);
        return team.getPlayers();
    }

    public Team getTeam(long id) throws Exception {
        Optional<Team> match
                = teamList.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
        if (match.isPresent()) {
            return match.get();
        } else {
            throw new Exception("The Game id " + id + " not found");
        }
    }
}
