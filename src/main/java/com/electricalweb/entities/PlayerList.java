package com.electricalweb.entities;

import java.util.ArrayList;
import java.util.List;

public class PlayerList {
    private static final List<Player> playerList = new ArrayList();

    private PlayerList() {
    }

    static {
        playerList.add(new Player("Ivan", "Team 1"));
        playerList.add(new Player("Serega", "Team 2"));
        playerList.add(new Player("Vasya", "Team 1"));
        playerList.add(new Player("Danil", "Team 2"));
    }


    public static List <Player> getInstance(){
        return playerList;
    }
}
