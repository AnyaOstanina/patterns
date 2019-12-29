package com.electricalweb.controllers;
import com.electricalweb.entities.Event;
import com.electricalweb.entities.Player;
import com.electricalweb.entities.Protocol;
import com.electricalweb.entities.ProtocolList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;

public class ProtocolService {
    List<Protocol> protoList = ProtocolList.getInstance();

    public Protocol getProtocol(long id) throws Exception {
        Optional<Protocol> match
                = protoList.stream()
                .filter(e -> e.getId() == id)
                .findFirst();
        if (match.isPresent()) {
            return match.get();
        } else {
            throw new Exception("The Game id " + id + " not found");
        }
    }

    public void setProtoList(List<Protocol> protoList) {
        this.protoList = protoList;
    }

    public int getStatisticGol(Protocol protocol) {
        List<Event> events =  protocol.getEvents();
        int gol = 0;
        for(int i=0; i< events.size(); i++) {
            if(events.get(i).getName().equalsIgnoreCase("gol") || events.get(i).getName().equalsIgnoreCase("гол")) {
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
            if(events.get(i).getName().equalsIgnoreCase("gol") || events.get(i).getName().equalsIgnoreCase("гол")) {
                gol++;
                int count = map.containsKey(events.get(i).getPlayers().get(0)) ? map.get(events.get(i).getPlayers().get(0)) : 0;
                map.put(events.get(i).getPlayers().get(0), count + 1);
            }
        }

        Player play = map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
        int val = map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getValue();
        Map<Player, Integer> pair = new HashMap<Player, Integer>();
        pair.put(play, val);
        return pair;
    }
}
