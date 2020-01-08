package com.electricalweb.entities;
import com.electricalweb.interfaces.IEntity;
import com.electricalweb.interfaces.IEntityList;
import java.util.ArrayList;
import java.util.List;

public class TeamList extends EntityList implements IEntityList {
    private static final List<IEntity> teamList = new ArrayList();

    public TeamList() {
        super(teamList);
    }

    static {
        teamList.add(new Team("Team1"));
        teamList.add(new Team("Team2"));
        teamList.add(new Team("Team3"));
    }

    public void addTeam(IEntity team) {
        teamList.add(team);
    }

    @Override
    public List <IEntity> getInstance(){
        return teamList;
    }
}
