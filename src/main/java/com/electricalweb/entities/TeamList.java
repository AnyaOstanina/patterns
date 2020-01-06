package com.electricalweb.entities;

import java.util.ArrayList;
import java.util.List;

public class TeamList extends EntityList {
    private static final List<Entity> teamList = new ArrayList();

    public TeamList() {
        super(teamList);
    }

    static {
        teamList.add(new Team("Team1"));
        teamList.add(new Team("Team2"));
        teamList.add(new Team("Team3"));
    }


    public static List <Entity> getInstance(){
        return teamList;
    }
}
