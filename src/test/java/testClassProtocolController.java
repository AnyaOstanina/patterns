import com.electricalweb.controllers.GameController;
import com.electricalweb.controllers.GameService;
import com.electricalweb.controllers.ProtocolController;
import com.electricalweb.controllers.ProtocolService;
import com.electricalweb.entities.Player;
import com.electricalweb.entities.Protocol;
import com.electricalweb.entities.ProtocolList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.internal.util.reflection.FieldSetter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class testClassProtocolController {
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

    private ProtocolController protocolController;
    private ProtocolController spyProtocolController;
    private ProtocolService protocolService;
    private ProtocolService spyProtocolService;

    private Protocol proto;
    private List<Protocol> protoList;
    private Map<Player, Integer> play;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        proto = new Protocol("11.11.11", "Football", 111);
        when(request.getParameter("idProtocol")).thenReturn(String.valueOf(proto.getId()));
        protoList = ProtocolList.getInstance();
        protoList.add(proto);
        play = new HashMap<Player, Integer>();
        play.put(new Player("Ivan", "One"), 0);
        initSpy();
    }

    public void initSpy() {
        protocolController = new ProtocolController();
        spyProtocolController =  Mockito.spy(protocolController);
        protocolService = new ProtocolService();
        spyProtocolService = Mockito.spy(protocolService);
    }

    //завистливая функция getProtocol в классе ProtocolService путем тестирования метода doPost
    @Test
    public void testDoPostCheckParams() throws Exception {
        spyProtocolService.setProtoList(protoList);
        when(spyProtocolService.getStatisticGol(proto)).thenReturn(0);
        when(spyProtocolService.getStatisticGolPlayer(proto)).thenReturn(play);
        spyProtocolController.setProtoService(spyProtocolService);
        doNothing().when(spyProtocolController).forwardResponse("/WEB-INF/views/protocol.jsp", request, response);
        spyProtocolController.doPost(request, response);
        verify(spyProtocolController).forwardResponse(stringCaptor.capture(),requestCaptor.capture(),responseCaptor.capture());
        assertEquals(request, requestCaptor.getValue().getAttributeNames());
    }

    @Test
    public void testDoPostCheckSetAttrProtocol() throws Exception {
        spyProtocolService.setProtoList(protoList);
        when(spyProtocolService.getStatisticGol(proto)).thenReturn(0);
        when(spyProtocolService.getStatisticGolPlayer(proto)).thenReturn(play);
        spyProtocolController.setProtoService(spyProtocolService);
        doNothing().when(spyProtocolController).forwardResponse("/WEB-INF/views/protocol.jsp", request, response);
        spyProtocolController.doPost(request, response);
        verify(request, times(1)).setAttribute(eq("protocol"), any(Protocol.class));
    }

    @Test
    public void testDoPostCheckForwardResponseCalled() throws Exception {
        spyProtocolService.setProtoList(protoList);
        when(spyProtocolService.getStatisticGol(proto)).thenReturn(0);
        when(spyProtocolService.getStatisticGolPlayer(proto)).thenReturn(play);
        spyProtocolController.setProtoService(spyProtocolService);
        doNothing().when(spyProtocolController).forwardResponse("/WEB-INF/views/protocol.jsp", request, response);
        spyProtocolController.doPost(request, response);
        verify(spyProtocolController, times(1)).forwardResponse("/WEB-INF/views/protocol.jsp", request, response);
    }
}