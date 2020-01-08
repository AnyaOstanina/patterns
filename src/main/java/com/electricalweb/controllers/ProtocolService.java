package com.electricalweb.controllers;
import com.electricalweb.entities.*;
import com.electricalweb.interfaces.Entity;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ProtocolService {
    public ProtocolList protoList = new ProtocolList();
    List<Entity> protocolList = protoList.getInstance();

    public void setProtoList(List<Entity> protoList) {
        this.protocolList = protoList;
    }

    public int getStatisticGol(Protocol protocol) {
        List<Event> events =  protocol.getEvents();
        int gol = 0;
        for(int i=0; i< events.size(); i++) {
            if(isGoal(events, i)) {
                gol++;
            }
        }
        return gol;
    }

    public Map<Player, Integer> getStatisticGolPlayer(Protocol protocol) {
        List<Event> events =  protocol.getEvents();
        if (events.size() <= 0) {
            Player MockPlayer = new Player("Not", "Found");
            Map<Player, Integer> pair = new HashMap<Player, Integer>();
            pair.put(MockPlayer, 0);
            return pair;
        }

        int gol = 0;
        Map<Player, Integer> map = new HashMap<Player, Integer>();
        for(int i=0; i< events.size(); i++) {
            if(isGoal(events, i)) {
                gol++;
                int count = map.containsKey(events.get(i).getPlayers().get(0)) ? map.get(events.get(i).getPlayers().get(0)) : 0;
                map.put(events.get(i).getPlayers().get(0), count + 1);
            }
        }

        Player play = getResultOfGoalStatistic(map).getKey();
        int val = getResultOfGoalStatistic(map).getValue();
        Map<Player, Integer> pair = new HashMap<Player, Integer>();
        pair.put(play, val);
        return pair;
    }

    private Map.Entry<Player, Integer> getResultOfGoalStatistic(Map<Player, Integer> map) {
        return map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get();
    }

    private boolean isGoal(List<Event> events, int i) {
        return events.get(i).getName().equalsIgnoreCase("goal") || events.get(i).getName().equalsIgnoreCase("гол");
    }
}
