package com.electricalweb.entities;

import com.electricalweb.interfaces.Entity;
import com.electricalweb.interfaces.IEntityList;

import java.util.ArrayList;
import java.util.List;

public class PlayerList extends EntityList implements IEntityList {
    private static final List<Entity> playerList = new ArrayList();

    public PlayerList() {
        super(playerList);
    }

    static {
        playerList.add(new Player("Ivan", "Team 1"));
        playerList.add(new Player("Serega", "Team 2"));
        playerList.add(new Player("Vasya", "Team 1"));
        playerList.add(new Player("Danil", "Team 2"));
    }

    public void addPlayer(Entity player) {
        playerList.add(player);
    }

    public static List<Entity> createPlayersList(String[] players, PlayerList playerList) throws Exception {
        List<Entity> playersList = new ArrayList<Entity>();
        for(int i=0; i < players.length; i++) {
            long playerId =  Long.valueOf(players[i]);
            playersList.add(playerList.searchEntityById(playerId));
        }
        return playersList;
    }

    @Override
    public List <Entity> getInstance() {
        return playerList;
    }
}
