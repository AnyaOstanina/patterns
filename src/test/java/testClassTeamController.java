import com.electricalweb.controllers.TeamController;
import com.electricalweb.controllers.TeamService;
import com.electricalweb.entities.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import org.mockito.*;
import static org.mockito.Mockito.*;

public class testClassTeamController {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    private TeamController teamController;
    private TeamController spyTeamController;
    private TeamService teamService;
    private TeamService spyTeamService;

    private Team team;
    private List<Team> teamList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        teamController = new TeamController();
        spyTeamController = Mockito.spy(teamController);
        teamService = new TeamService();
        spyTeamService = Mockito.spy(teamService);
        spyTeamController.setTeamService(spyTeamService);
        team = new Team("Team 1");
        when(request.getParameter("teamName")).thenReturn("Team 1");
        doNothing().when(spyTeamService).forwardResponse("/WEB-INF/views/team.jsp", request, response);
        when(request.getParameter("date")).thenReturn("11.11.11");
        teamList = new ArrayList<>();
        teamList.add(team);
    }

    @Test
    public void testDoPostCallGetAllTeams() {
        when(request.getParameter("action")).thenReturn(null);
        spyTeamController.setTeamService(spyTeamService);
        spyTeamController.doPost(request, response);
        verify(spyTeamService, times(1)).getAllTeams();
    }

    @Test
    public void testDoPostCallAddTeam() {
        when(request.getParameter("action")).thenReturn("addTeam");
        spyTeamController.setTeamService(spyTeamService);
        spyTeamController.doPost(request, response);
        verify(spyTeamService, times(1)).addTeam(request);
    }

    @Test
    public void testDoPostCallSetAttrTeam() {
        when(request.getParameter("action")).thenReturn("addTeam");
        spyTeamController.doPost(request, response);
        verify(request, times(1)).setAttribute(eq("teams"), anyList());
    }

    @Test
    public void testDoGet() {
        when(spyTeamService.getAllTeams()).thenReturn(teamList);
        spyTeamController.setTeamService(spyTeamService);
        spyTeamController.doGet(request, response);
        verify(spyTeamService, times(1)).getAllTeams();
    }
}
