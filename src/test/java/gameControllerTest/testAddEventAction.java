package gameControllerTest;

import com.electricalweb.controllers.GameController;
import com.electricalweb.controllers.GameService;
import com.electricalweb.entities.*;
import com.electricalweb.interfaces.Entity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class testAddEventAction {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Captor
    ArgumentCaptor<Protocol> protocolCaptor;

    @Captor
    ArgumentCaptor<HttpServletResponse> responseCaptor;

    @Captor
    ArgumentCaptor<HttpServletRequest> requestCaptor;

    private String[] playersIdArray;
    private GameController gameController;
    private GameController spyGameController;
    private GameService gameService;
    private GameService spyGameService;
    public  List<Entity> playerList = new PlayerList().getInstance();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        initSpy();
        setReturningValues();
        setReturningValuesRequest();
    }

    public void setReturningValuesRequest() {
        when(request.getParameter("gameName")).thenReturn("Football");
        when(request.getParameter("date")).thenReturn("11.11.11");
        when(request.getParameter("idCustomer")).thenReturn(String.valueOf(1111));
        when(request.getParameter("name")).thenReturn("gol");
        when(request.getParameter("time")).thenReturn("11:20");
        when(request.getParameter("idProtocol")).thenReturn("111");
        setPlayersIdArray(1);
        when(request.getParameterValues("players[]")).thenReturn(playersIdArray);
    }

    public void setReturningValues() throws Exception {
        doReturn(new Player("Ivan", "Team 1")).when(spyGameService).getPlayer(anyLong());
        Entity protocol = new Protocol("11.11.11", "Football", 111);
        doReturn(protocol).when(spyGameService).searchProtocolById(any(HttpServletRequest.class));
        doNothing().when(spyGameController).forwardResponse("/WEB-INF/views/game.jsp", request, response);
    }

    public void initSpy() {
        gameController = new GameController();
        spyGameController =  Mockito.spy(gameController);
        gameService = new GameService();
        spyGameService =  Mockito.spy(gameService);
    }

    private String[] setPlayersIdArray(int n) {
        playersIdArray = new String[n];
        for (int i=0; i<n; i++) {
            playersIdArray[i]= String.valueOf(playerList.get(i).getId());
        }
        return playersIdArray;
    }

    // start test method addEventAction
    @Test
    public void testAddEventAction() throws Exception {
        spyGameController.setGameService(spyGameService);
        spyGameController.addEventAction(request, response);
        verify(spyGameController).forwardListEvents(requestCaptor.capture(),responseCaptor.capture(),protocolCaptor.capture());
        assertEquals(111, protocolCaptor.getValue().getCreatorId());
        assertEquals(1, protocolCaptor.getValue().getEvents().size());
    }

    @Test
    public void testAddEventActionCallSetEvent() throws Exception {
        spyGameController.setGameService(spyGameService);
        Protocol protocol = new Protocol("11.11.11", "Football", 111);
        Protocol spyProtocol = spy(protocol);
        doReturn(spyProtocol).when(spyGameService).searchProtocolById(request);
        spyGameController.addEventAction(request, response);
        verify(spyProtocol, times(1)).setEvents(any(Event.class));
    }

    @Test
    public void testAddEventActionCallGetPlayer() throws Exception {
        playersIdArray = setPlayersIdArray(3);
        when(request.getParameterValues("players[]")).thenReturn(playersIdArray);
        spyGameController.setGameService(spyGameService);
        spyGameController.addEventAction(request, response);
        verify(spyGameService, times(3)).getPlayer(anyLong());
    }

    @Test
    public void testAddEventActionCallSearchProtocolById() throws Exception {
        playersIdArray = setPlayersIdArray(3);
        when(request.getParameterValues("players[]")).thenReturn(playersIdArray);
        spyGameController.setGameService(spyGameService);
        spyGameController.addEventAction(request, response);
        verify(spyGameService, times(1)).searchProtocolById(request);
    }

    @Test
    public void testAddEventActionNotCallSetDate() throws Exception {
        when(request.getParameter("date")).thenReturn(null);
        playersIdArray = setPlayersIdArray(3);
        when(request.getParameterValues("players[]")).thenReturn(playersIdArray);
        Protocol protocol = new Protocol("11.11.11", "Football", 111);
        Protocol spyProtocol = spy(protocol);
        doReturn(spyProtocol).when(spyGameService).searchProtocolById(request);
        spyGameController.setGameService(spyGameService);
        spyGameController.addEventAction(request, response);
        verify(spyProtocol, times(0)).setDate(anyString());
    }

    @Test
    public void testAddEventActionCallSetDate() throws Exception {
        playersIdArray = setPlayersIdArray(3);
        when(request.getParameterValues("players[]")).thenReturn(playersIdArray);
        Protocol protocol = new Protocol("11.11.11", "Football", 111);
        Protocol spyProtocol = spy(protocol);
        doReturn(spyProtocol).when(spyGameService).searchProtocolById(request);
        spyGameController.setGameService(spyGameService);
        spyGameController.addEventAction(request, response);
        verify(spyProtocol, times(1)).setDate(anyString());
    }

    // end test method addEventAction
}
