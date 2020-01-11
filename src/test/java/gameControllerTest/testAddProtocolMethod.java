package gameControllerTest;

import com.electricalweb.controllers.GameController;
import com.electricalweb.services.GameService;
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

    private GameController gameController ;
    private GameController spyGameController;
    private GameService gameService ;
    private GameService spyGameService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        initSpy();
        when(request.getParameter("gameName")).thenReturn("Football");
        when(request.getParameter("date")).thenReturn("11.11.11");
        when(request.getParameter("idCustomer")).thenReturn(String.valueOf(1111));
        when(request.getParameter("name")).thenReturn("Football");
        when(request.getParameter("time")).thenReturn("11:20");
        when(request.getParameter("idProtocol")).thenReturn("111");
    }

    private void initSpy() {
        gameController = new GameController();
        spyGameController =  Mockito.spy(gameController);
        gameService = new GameService();
        spyGameService =  Mockito.spy(gameService);
        spyGameController.setGameService(spyGameService);
    }

    @Test
    public void testAddProtocolCheckParameters() throws Exception {
        doNothing().when(spyGameService).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(spyGameService).forwardResponse(stringCaptor.capture(),requestCaptor.capture(),responseCaptor.capture());
        assertEquals("/WEB-INF/views/customerinfo.jsp", stringCaptor.getValue());
        assertEquals(request, requestCaptor.getValue());
        assertEquals(response, responseCaptor.getValue());
    }

    @Test
    public void testAddProtocolCallSetProtocols() throws Exception {
        doNothing().when(spyGameService).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(request, times(1)).setAttribute(eq("protocols"), anyList());
    }

    @Test
    public void testAddProtocolCallSetPlayers() throws Exception {
        doNothing().when(spyGameService).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(request, times(1)).setAttribute(eq("players"), anyList());
    }

    @Test
    public void testAddProtocolCallSetGameList() throws Exception {
        doNothing().when(spyGameService).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(request, times(1)).setAttribute(eq("gameList"), anyList());
    }

    @Test
    public void testAddProtocolCallSetIdCustomer() throws Exception {
        doNothing().when(spyGameService).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(request, times(1)).setAttribute(eq("idCustomer"), anyString());
    }

    @Test
    public void testAddProtocolCallSetProtocol() throws Exception {
        doNothing().when(spyGameService).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(request, times(1)).setAttribute(eq("protocol"), any(Protocol.class));
    }

    @Test
    public void testAddProtocolToList() throws Exception {
        doNothing().when(spyGameService).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(spyGameService, times(1)).addProtocol(any(Protocol.class));
    }

    @Test
    public void testGetAllProtocols() throws Exception {
        doNothing().when(spyGameService).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(spyGameService, times(1)).getAllProtocols();
    }

    @Test
    public void testGetAllPlayers() throws Exception {
        doNothing().when(spyGameService).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(spyGameService, times(1)).getAllPlayers();
    }

    @Test
    public void testGetAllGames() throws Exception {
        doNothing().when(spyGameService).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(spyGameService, times(1)).getAllGames();
    }
}
