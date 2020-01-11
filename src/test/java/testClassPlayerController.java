import com.electricalweb.controllers.PlayerController;
import com.electricalweb.entities.Player;
import com.electricalweb.entities.TeamList;
import com.electricalweb.interfaces.IEntity;
import com.electricalweb.services.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class testClassPlayerController {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Captor
    ArgumentCaptor<List<Player>> playersCaptor;

    @Captor
    ArgumentCaptor<Long> longCapture;
    @Captor
    ArgumentCaptor<HttpServletRequest> requestCaptor;

    private PlayerController playerController;
    private PlayerController spyPlayerController;
    private PlayerService playerService;
    private PlayerService spyPlayerService;
    private long teamId;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        TeamList teamList = new TeamList();
        List<IEntity> teamListInstance = teamList.getInstance();
        teamId = teamListInstance.get(0).getId();
        when(request.getParameter("idTeam")).thenReturn(String.valueOf(teamId));
        when(request.getParameter("teamId")).thenReturn(String.valueOf(teamId));
        initSpy();
        doNothing().when(spyPlayerService).forwardResponse("/WEB-INF/views/player.jsp", request, response);
    }

    private void initSpy() {
        playerController = new PlayerController();
        spyPlayerController =  Mockito.spy(playerController);
        playerService = new PlayerService();
        spyPlayerService =  Mockito.spy(playerService);
        spyPlayerController.setPlayerService(spyPlayerService);
    }
   //  вызывать тестовые методы нужно по отдельности, иначе игорк добавится в лист и ожидамое
   //  значение в методе testDoGetSetAttributesParams будет уже не пустым массивом
    @Test
    public void testDoGetSetAttributesParams() throws NullPointerException {
        spyPlayerController.doGet(request, response);
        verify(spyPlayerController).setAttributes(requestCaptor.capture(),longCapture.capture(), playersCaptor.capture());
        ArrayList<Object> players = new ArrayList<>();
        assertEquals(players, playersCaptor.getValue());
    }

    @Test
    public void testAddPlayers() throws Exception {
        spyPlayerController.addPlayer(request, response);
        verify(spyPlayerService, times(1)).forwardResponse("/WEB-INF/views/player.jsp", request, response);
    }
}
