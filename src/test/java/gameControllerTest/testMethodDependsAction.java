package gameControllerTest;

import com.electricalweb.controllers.GameController;
import com.electricalweb.controllers.GameService;
import com.electricalweb.entities.Protocol;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(Parameterized.class)
public class testMethodDependsAction {

    @Parameterized.Parameter
    public String action;


    @Parameterized.Parameters(name = "{0} ---> : Test")
    public static String[] data() {
        String[] actionArray = new String[2];
        actionArray[0] = "addProtocol";
        actionArray[1] = "addEventAction";
        return actionArray;
    }

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    private String[] playersIdArray;
    private GameController gameController;
    private GameController spyGameController;
    private GameService gameService;
    private GameService spyGameService;

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
        gameController = new GameController();
        spyGameController = Mockito.spy(gameController);
        gameService = new GameService();
        spyGameService = Mockito.spy(gameService);
    }

    private String[] setPlayersIdArray(int n) {
        playersIdArray = new String[n];
        for (int i = 0; i < n; i++) {
            playersIdArray[i] = String.valueOf(i);
        }
        return playersIdArray;
    }

    @Test
    public void testGetAllProtocolsNotCalled() throws ServletException, IOException {
        spyGameController.setGameService(spyGameService);
        when(request.getParameter("action")).thenReturn(action);
        doNothing().when(spyGameController).addProtocol(request, response);
        spyGameController.doPost(request, response);
        verify(spyGameService, times(0)).getAllProtocols();
    }

    @Test
    public void testGetAllPlayersNotCalled() throws ServletException, IOException {
        spyGameController.setGameService(spyGameService);
        when(request.getParameter("action")).thenReturn(action);
        doNothing().when(spyGameController).addProtocol(request, response);
        spyGameController.doPost(request, response);
        verify(spyGameService, times(0)).getAllPlayers();
    }

    @Test
    public void testAddEventActionCalledOnce() throws Exception {
        when(request.getParameter("action")).thenReturn(action);
        doNothing().when(spyGameController).addEventAction(request, response);
        spyGameController.doPost(request, response);
        verify(spyGameController, times(1)).addEventAction(request, response);
    }

    @Test
    public void testAddProtocolCalledOnce() throws ServletException, IOException {
        when(request.getParameter("action")).thenReturn(action);
        doNothing().when(spyGameController).addProtocol(request, response);
        spyGameController.doPost(request, response);
        verify(spyGameController, times(1)).addProtocol(request, response);
    }
}