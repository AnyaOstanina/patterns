import com.electricalweb.controllers.ProtocolController;
import com.electricalweb.controllers.ProtocolService;
import com.electricalweb.controllers.TeamController;
import com.electricalweb.controllers.TeamService;
import com.electricalweb.entities.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mockito.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class testClassTeamController {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Captor
    ArgumentCaptor<HttpServletResponse> responseCaptor;

    @Captor
    ArgumentCaptor<HttpServletRequest> requestCaptor;

    @Captor
    ArgumentCaptor<String> stringCaptor;

    private TeamController teamController;
    private TeamController spyTeamController;
    private TeamService teamService;
    private TeamService spyTeamService;

    private Team team;
    private List<Team> teamList;
    private Map<Player, Integer> play;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        teamController = new TeamController();
        spyTeamController = Mockito.spy(teamController);
        teamService = new TeamService();
        spyTeamService = Mockito.spy(teamService);
        spyTeamController.setTeamService(spyTeamService);
        team = new Team("Team 1");
        when(request.getParameter("teamName")).thenReturn("Team 1");
        doNothing().when(spyTeamController).forwardResponse("/WEB-INF/views/team.jsp", request, response);
        when(request.getParameter("date")).thenReturn("11.11.11");
        teamList = new ArrayList<>();
        teamList.add(team);
    }

    @Test
    public void testDoPostCallGetAllTeams() throws Exception {
        when(request.getParameter("action")).thenReturn(null);
        spyTeamController.setTeamService(spyTeamService);
        spyTeamController.doPost(request, response);
        verify(spyTeamService, times(1)).getAllTeams();
    }

    @Test
    public void testDoPostCallAddTeam() throws Exception {
        when(request.getParameter("action")).thenReturn("addTeam");
        spyTeamController.setTeamService(spyTeamService);
        spyTeamController.doPost(request, response);
        verify(spyTeamService, times(1)).addTeam(any(Team.class));
    }

    @Test
    public void testDoGet() throws Exception {
        when(spyTeamService.getAllTeams()).thenReturn(teamList);
        spyTeamController.setTeamService(spyTeamService);
        spyTeamController.doGet(request, response);
        verify(spyTeamController).forwardResponse(anyString(),requestCaptor.capture(),responseCaptor.capture());
        verify(spyTeamService, times(1)).getAllTeams();
    }
}
