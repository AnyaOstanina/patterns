package gameControllerTest;

import static org.mockito.Mockito.when;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import com.electricalweb.entities.Event;
import com.electricalweb.entities.Player;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
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


public class testDoPost {
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

    private String[] playersIdArray;
    private GameController gameController;
    private GameController spyGameController;
    private GameService gameService;
    private GameService spyGameService;

    private String[] setPlayersIdArray(int n) {
        playersIdArray = new String[n];
        for (int i=0; i<n; i++) {
            playersIdArray[i]= String.valueOf(i);
        }
        return playersIdArray;
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(request.getParameter("gameName")).thenReturn("Football");
        when(request.getParameter("date")).thenReturn("11.11.11");
        when(request.getParameter("idCustomer")).thenReturn(String.valueOf(1111));
        when(request.getParameter("name")).thenReturn("Football");
        when(request.getParameter("time")).thenReturn("11:20");
        when(request.getParameter("idProtocol")).thenReturn("111");
        setPlayersIdArray(1);
        when(request.getParameterValues("players[]")).thenReturn(playersIdArray);
        initSpy();
    }

    private void initSpy() {
        gameController = new GameController();
        spyGameController =  Mockito.spy(gameController);
        gameService = new GameService();
        spyGameService =  Mockito.spy(gameService);
        spyGameController.setGameService(spyGameService);
    }
    //start test method doPost
    @Test
    public void testDoPostForwardResponseCalledOnce() throws IOException, ServletException, NullPointerException {
        when(request.getParameter("action")).thenReturn(null);
        doNothing().when(spyGameService).forwardResponse("/WEB-INF/views/game.jsp", request, response);
        spyGameController.doPost(request, response);
        verify(spyGameService, times(1)).forwardResponse("/WEB-INF/views/game.jsp", request, response);
    }

    @Test
    public void testDoPostCallSetProtocols() throws Exception {
        doNothing().when(spyGameService).forwardResponse("/WEB-INF/views/game.jsp", request, response);
        spyGameController.doPost(request, response);
        verify(request, times(1)).setAttribute(eq("protocols"), anyList());
    }

    @Test
    public void testDoPostCallSetPlayers() throws Exception {
        doNothing().when(spyGameService).forwardResponse("/WEB-INF/views/game.jsp", request, response);
        spyGameController.doPost(request, response);
        verify(request, times(1)).setAttribute(eq("players"), anyList());
    }

    @Test
    public void testDoPostCallSetIdCustomer() throws Exception {
        doNothing().when(spyGameService).forwardResponse("/WEB-INF/views/game.jsp", request, response);
        spyGameController.doPost(request, response);
        verify(request, times(1)).setAttribute(eq("idCustomer"), anyString());
    }

    @Test
    public void testDoPostCallSetProtocol() throws Exception {
        doNothing().when(spyGameService).forwardResponse("/WEB-INF/views/game.jsp", request, response);
        spyGameController.doPost(request, response);
        verify(request, times(1)).setAttribute(eq("protocol"), any(Protocol.class));
    }

    @Test
    public void testForwardResponseParams() throws ServletException, IOException {
        doNothing().when(spyGameService).forwardResponse("/WEB-INF/views/game.jsp", request, response);
        spyGameController.doPost(request, response);
        verify(spyGameService).forwardResponse(stringCaptor.capture(),requestCaptor.capture(),responseCaptor.capture());
        assertEquals(request, requestCaptor.getValue());
        assertEquals(response, responseCaptor.getValue());
        assertEquals("/WEB-INF/views/game.jsp", stringCaptor.getValue());
    }
    //end test method doPost
}