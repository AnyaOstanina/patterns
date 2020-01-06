package com.electricalweb.entities;

import java.util.ArrayList;
import java.util.List;

public class PlayerList extends EntityList {
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


    public static List <Entity> getInstance(){
        return playerList;
    }
}
