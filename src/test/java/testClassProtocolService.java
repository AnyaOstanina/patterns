
import com.electricalweb.controllers.ProtocolService;
import com.electricalweb.entities.Event;
import com.electricalweb.entities.Player;
import com.electricalweb.entities.Protocol;
import com.electricalweb.entities.ProtocolList;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class testClassProtocolService {
    private  List<Player> playersList;
    private  Protocol proto;

    @Before
    public void setUp() throws Exception {
        proto = new Protocol("11.11.11", "Football",1111);
        Player player = new Player("Ivan","Team 1");
        playersList = new ArrayList<Player>();
        playersList.add(player);
    }
    // tests for getStatisticGolPlayer()
    @Test
    public void countGolLargeNumber() throws Exception {
        for(int i=0;i < 1000; i++) {
            Event event = new Event("goal", "11.11.11", playersList);
            proto.setEvents(event);
        }
        ProtocolService protoService = new ProtocolService();
        Map<Player, Integer> play = protoService.getStatisticGolPlayer(proto);
        int res = play.values().stream().findFirst().get();
        assertEquals(1000, res);
    }

    @Test
    public void countGol() {
        Event event = new Event("goal", "11.11.11", playersList);
        proto.setEvents(event);
        ProtocolService protoService = new ProtocolService();
        Map<Player, Integer> play = protoService.getStatisticGolPlayer(proto);
        int res = play.values().stream().findFirst().get();
        assertEquals(1, res);
    }

    @Test
    public void checkPlayerName() {
        Event event = new Event("goal", "11.11.11", playersList);
        proto.setEvents(event);
        ProtocolService protoService = new ProtocolService();
        Map<Player, Integer> play = protoService.getStatisticGolPlayer(proto);
        String res =  play.keySet().stream().findFirst().get().getName();
        assertEquals("Ivan", res);
    }

    @Test
    public void checkEmptyEvents() {
        Protocol proto = new Protocol("11.11.11", "Football", 1111);
        ProtocolService protoService = new ProtocolService();
        Map<Player, Integer> play = protoService.getStatisticGolPlayer(proto);
        String res =  play.keySet().stream().findFirst().get().getName();
        assertEquals("Not", res);
    }


    // tests for getStatisticGol()
    @Test
    public void checkNoGolEventCount() {
        Event event = new Event("aaa", "11.11.11", null);
        proto.setEvents(event);
        ProtocolService protoService = new ProtocolService();
        int res = protoService.getStatisticGol(proto);
        assertEquals(0, res);
    }

    @Test
    public void checkGolEventCount() {
        Event event = new Event("goal", "11.11.11", null);
        proto.setEvents(event);
        ProtocolService protoService = new ProtocolService();
        int res = protoService.getStatisticGol(proto);
        assertEquals(1, res);
    }

    @Test
    public void checkGolEventCountLargeNumber() {
        for(int i=0;i < 1000; i++) {
            Event event = new Event("goal", "11.11.11", null);
            proto.setEvents(event);
        }

        ProtocolService protoService = new ProtocolService();
        int res = protoService.getStatisticGol(proto);
        assertEquals(1000, res);
    }

    @Test
    public void checkGolEventRussianSpelling() {
        Protocol proto = new Protocol("11.11.11", "Football",1111);

        for(int i=0;i < 10; i++) {
            Event event = new Event("гол", "11.11.11", null);
            proto.setEvents(event);
        }

        ProtocolService protoService = new ProtocolService();
        int res = protoService.getStatisticGol(proto);
        assertEquals(10, res);
    }

    @Test
    public void checkGolEventMixedSpelling() {
        Protocol proto = new Protocol("11.11.11", "Football",1111);

        for(int i=0;i < 10; i++) {
            Event eventRu = new Event("гол", "11.11.11", null);
            Event eventEn = new Event("goal", "11.11.11", null);
            proto.setEvents(eventRu);
            proto.setEvents(eventEn);
        }

        ProtocolService protoService = new ProtocolService();
        int res = protoService.getStatisticGol(proto);
        assertEquals(20, res);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testGetProtocolThrowException() throws Exception {
        ProtocolService protoService = new ProtocolService();
        ProtocolService spyProtoService = Mockito.spy(protoService);
        expectedException.expect(Exception.class);
        expectedException.expectMessage("The Protocol id 0 not found");
        given(ProtocolList.getProtocol( 0)).willThrow(new Exception("The Protocol id 0 not found"));
    }
}
