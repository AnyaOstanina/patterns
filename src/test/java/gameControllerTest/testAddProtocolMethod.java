package gameControllerTest;

import com.electricalweb.controllers.GameController;
import com.electricalweb.controllers.GameService;
import com.electricalweb.entities.Protocol;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class testAddProtocolMethod {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;
    @Captor
    ArgumentCaptor<String> stringCaptor;

    @Captor
    ArgumentCaptor<HttpServletResponse> responseCaptor;

    @Captor
    ArgumentCaptor<HttpServletRequest> requestCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(request.getParameter("gameName")).thenReturn("Football");
        when(request.getParameter("date")).thenReturn("11.11.11");
        when(request.getParameter("idCustomer")).thenReturn(String.valueOf(1111));
        when(request.getParameter("name")).thenReturn("Football");
        when(request.getParameter("time")).thenReturn("11:20");
        when(request.getParameter("idProtocol")).thenReturn("111");
    }

    @Test
    public void testAddProtocolCheckParameters() throws Exception {
        GameController gameController = new GameController();
        GameController spyGameController =  Mockito.spy(gameController);
        doNothing().when(spyGameController).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(spyGameController).forwardResponse(stringCaptor.capture(),requestCaptor.capture(),responseCaptor.capture());
        assertEquals("/WEB-INF/views/customerinfo.jsp", stringCaptor.getValue());
        assertEquals(request, requestCaptor.getValue());
        assertEquals(response, responseCaptor.getValue());
    }

    @Test
    public void testAddProtocolCallSetProtocols() throws Exception {
        GameController gameController = new GameController();
        GameController spyGameController =  Mockito.spy(gameController);
        doNothing().when(spyGameController).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(request, times(1)).setAttribute(eq("protocols"), anyList());
    }

    @Test
    public void testAddProtocolCallSetPlayers() throws Exception {
        GameController gameController = new GameController();
        GameController spyGameController =  Mockito.spy(gameController);
        doNothing().when(spyGameController).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(request, times(1)).setAttribute(eq("players"), anyList());
    }

    @Test
    public void testAddProtocolCallSetGameList() throws Exception {
        GameController gameController = new GameController();
        GameController spyGameController =  Mockito.spy(gameController);
        doNothing().when(spyGameController).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(request, times(1)).setAttribute(eq("gameList"), anyList());
    }

    @Test
    public void testAddProtocolCallSetIdCustomer() throws Exception {
        GameController gameController = new GameController();
        GameController spyGameController =  Mockito.spy(gameController);
        doNothing().when(spyGameController).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(request, times(1)).setAttribute(eq("idCustomer"), anyString());
    }

    @Test
    public void testAddProtocolCallSetProtocol() throws Exception {
        GameController gameController = new GameController();
        GameController spyGameController =  Mockito.spy(gameController);
        doNothing().when(spyGameController).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(request, times(1)).setAttribute(eq("protocol"), any(Protocol.class));
    }

    @Test
    public void testAddProtocolToList() throws Exception {
        GameController gameController = new GameController();
        GameController spyGameController =  Mockito.spy(gameController);
        GameService gameService = new GameService();
        GameService spyGameService =  Mockito.spy(gameService);
        spyGameController.setGameService(spyGameService);
        doNothing().when(spyGameController).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(spyGameService, times(1)).addProtocol(any(Protocol.class));
    }

    @Test
    public void testGetAllProtocols() throws Exception {
        GameController gameController = new GameController();
        GameController spyGameController =  Mockito.spy(gameController);
        GameService gameService = new GameService();
        GameService spyGameService =  Mockito.spy(gameService);
        spyGameController.setGameService(spyGameService);
        doNothing().when(spyGameController).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(spyGameService, times(1)).getAllProtocols();
    }

    @Test
    public void testGetAllPlayers() throws Exception {
        GameController gameController = new GameController();
        GameController spyGameController =  Mockito.spy(gameController);
        GameService gameService = new GameService();
        GameService spyGameService =  Mockito.spy(gameService);
        spyGameController.setGameService(spyGameService);
        doNothing().when(spyGameController).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(spyGameService, times(1)).getAllPlayers();
    }

    @Test
    public void testGetAllGames() throws Exception {
        GameController gameController = new GameController();
        GameController spyGameController =  Mockito.spy(gameController);
        GameService gameService = new GameService();
        GameService spyGameService =  Mockito.spy(gameService);
        spyGameController.setGameService(spyGameService);
        doNothing().when(spyGameController).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(spyGameService, times(1)).getAllGames();
    }
}
