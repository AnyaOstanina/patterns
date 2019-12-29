import static org.mockito.Mockito.when;
import java.io.IOException;
import com.electricalweb.entities.Player;
import org.junit.BeforeClass;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.electricalweb.controllers.GameController;
import com.electricalweb.controllers.GameService;
import com.electricalweb.entities.Protocol;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import org.mockito.*;
import static org.junit.Assert.*;

public class testClassGameController {
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
    @Captor
    ArgumentCaptor<String> stringCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(request.getParameter("gameName")).thenReturn("Football");
        when(request.getParameter("date")).thenReturn("11.11.11");
        when(request.getParameter("idCustomer")).thenReturn(String.valueOf(1111));
        when(request.getParameter("name")).thenReturn("Football");
        when(request.getParameter("time")).thenReturn("11:20");
        when(request.getParameter("idProtocol")).thenReturn("111");
        String[] playersIdArray = new String[1];
        playersIdArray[0]="111";
        when(request.getParameterValues("players[]")).thenReturn(playersIdArray);
    }

    @Test
    public void testForwardResponseCalledOnce() throws IOException, ServletException, NullPointerException {
        when(request.getParameter("action")).thenReturn(null);
        GameController gameController = new GameController();
        GameController spyGameController = Mockito.spy(gameController);
        doNothing().when(spyGameController).forwardResponse("/WEB-INF/views/game.jsp", request, response);
        spyGameController.doPost(request, response);
        verify(spyGameController, times(1)).forwardResponse("/WEB-INF/views/game.jsp", request, response);
    }

    @Test
    public void testAddEventActionCalledOnce() throws Exception {
        when(request.getParameter("action")).thenReturn("addEvent");
        GameController gameController = new GameController();
        GameController spyGameController = Mockito.spy(gameController);
        doNothing().when(spyGameController).addEventAction(request, response);
        spyGameController.doPost(request, response);
        verify(spyGameController, times(1)).addEventAction(request, response);
    }

    @Test
    public void testAddProtocolCalledOnce() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn("addProtocol");
        GameController gameController = new GameController();
        GameController spyGameController = Mockito.spy(gameController);
        doNothing().when(spyGameController).addProtocol(request, response);
        spyGameController.doPost(request, response);
        verify(spyGameController, times(1)).addProtocol(request, response);
    }

    @Test
    public void testForwardListEvents() throws ServletException, IOException {
        GameController gameController = new GameController();
        GameController spyGameController = Mockito.spy(gameController);
        Protocol protocol = new Protocol("11.11.11", "Football", 111);
        doNothing().when(spyGameController).forwardResponse("/WEB-INF/views/game.jsp", request, response);
        spyGameController.forwardListEvents(request, response, protocol);
        verify(spyGameController, times(1)).forwardResponse("/WEB-INF/views/game.jsp", request, response);
    }

    @Test
    public void testAddEventAction() throws Exception {
        GameController gameController = new GameController();
        GameController spyGameController =  Mockito.spy(gameController);
        GameService gameService = new GameService();
        GameService spyGameService =  Mockito.spy(gameService);
        spyGameController.setGameService(spyGameService);
        doReturn(new Player("Ivan", "Team 1")).when(spyGameService).getPlayer(111);
        Protocol protocol = new Protocol("11.11.11", "Football", 111);
        doReturn(protocol).when(spyGameController).searchProtocolById(request,response);
        doNothing().when(spyGameController).forwardResponse("/WEB-INF/views/game.jsp", request, response);
        spyGameController.addEventAction(request, response);
        verify(spyGameController).forwardListEvents(requestCaptor.capture(),responseCaptor.capture(),protocolCaptor.capture());
        assertEquals(111, protocolCaptor.getValue().getCreatorId());
    }

    @Test
    public void testAddProtocol() throws Exception {
        GameController gameController = new GameController();
        GameController spyGameController =  Mockito.spy(gameController);
        doNothing().when(spyGameController).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        spyGameController.addProtocol(request, response);
        verify(spyGameController).forwardResponse(stringCaptor.capture(),requestCaptor.capture(),responseCaptor.capture());
        assertEquals("/WEB-INF/views/customerinfo.jsp", stringCaptor.getValue());
        assertEquals(request, requestCaptor.getValue());
        assertEquals(response, responseCaptor.getValue());
    }
}