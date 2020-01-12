package com.electricalweb.services;
import com.electricalweb.entities.*;
import com.electricalweb.interfaces.IEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Stream;

public class ProtocolService extends Service {
    public ProtocolService() {
        super("/WEB-INF/views/protocol.jsp");
    }
    public ProtocolList protoList = new ProtocolList();
    private Map<Player, Integer> map = new HashMap<>();
    List<IEntity> protocolList = protoList.getInstance();
    public void setProtoList(List<IEntity> protoList) {
        this.protocolList = protoList;
    }

    public int getStatisticGol(Protocol protocol,Boolean isForPlayer) {
        List<Event> events =  protocol.getEvents();
        map = new HashMap<>();
        int gol = 0;
        for(int i=0; i< events.size(); i++) {
            if(isGoal(events, i)) {
                gol++;
                if(isForPlayer) {
                    int count = map.containsKey(events.get(i).getPlayers().get(0)) ? map.get(events.get(i).getPlayers().get(0)) : 0;
                    map.put(events.get(i).getPlayers().get(0), count + 1);
                }
            }
        }
        return gol;
    }

    public Map<Player, Integer> getStatisticGolPlayer(Protocol protocol) {
        List<Event> events =  protocol.getEvents();
        if (events.size() <= 0) {
            return createMockPlayer();
        }
        getStatisticGol(protocol, true);
        Player play = getResultOfGoalStatistic(map).getKey();
        int val = getResultOfGoalStatistic(map).getValue();
        Map<Player, Integer> pair = new HashMap<Player, Integer>();
        pair.put(play, val);
        return pair;
    }

    private Map<Player, Integer> createMockPlayer() {
        Player MockPlayer = new Player("Not", "Found");
        Map<Player, Integer> pair = new HashMap<Player, Integer>();
        pair.put(MockPlayer, 0);
        return pair;
    }

    public <K, V extends Comparable<V>> Map.Entry<Player, Integer> getResultOfGoalStatistic(Map<K, V> map) {
        Map.Entry<K, V> maxEntry = Collections.max(map.entrySet(), Comparator.comparing(Map.Entry::getValue));
        return (Map.Entry<Player, Integer>) maxEntry;
    }

    private boolean isGoal(List<Event> events, int i) {
        return events.get(i).getName().equalsIgnoreCase("goal") || events.get(i).getName().equalsIgnoreCase("гол");
    }

    public Protocol getProtocolById(HttpServletRequest req) {
        long idProtocol = Integer.valueOf(req.getParameter("idProtocol"));
        Protocol protocol = null;
        try {
            protocol = (Protocol) protoList.searchEntityById(idProtocol);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return protocol;
    }
}
