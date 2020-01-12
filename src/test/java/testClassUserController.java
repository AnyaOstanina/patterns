import com.electricalweb.controllers.UserController;
import com.electricalweb.entities.User;
import com.electricalweb.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class testClassUserController {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    private UserController userController;
    private UserController spyUserController;
    private UserService userService;
    private UserService spyUserService;

    @Captor
    ArgumentCaptor<HttpServletResponse> responseCaptor;
    @Captor
    ArgumentCaptor<HttpServletRequest> requestCaptor;
    @Captor
    ArgumentCaptor<String> stringCaptor;
    private long teamId;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        initSpy();
        when(request.getParameter("login")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("admin");
        doNothing().when(spyUserController).setAttributes(any(HttpServletRequest.class),any(User.class));
        doNothing().when(response).sendRedirect("http://localhost:8080/Customer_Application_war/");
    }

    private void initSpy() {
        userController = new UserController();
        spyUserController =  Mockito.spy(userController);
        userService = new UserService();
        spyUserService =  Mockito.spy(userService);
        doNothing().when(spyUserService).forwardResponse(anyString(), any(HttpServletRequest.class), any(HttpServletResponse.class));
        spyUserController.setUserService(spyUserService);
    }

    @Test
    public void testDoGetSetForwardResponseNotCalled() throws Exception {
        when(request.getParameter("login")).thenReturn("admin1");
        when(request.getParameter("password")).thenReturn("admin");
        spyUserController.doPost(request, response);
        verify(response, times(2)).sendRedirect("http://localhost:8080/Customer_Application_war/");
    }

    @Test
    public void testForwardResponseParams() throws Exception {
        spyUserController.doPost(request, response);
        verify(spyUserService).forwardResponse(stringCaptor.capture(),requestCaptor.capture(),responseCaptor.capture());
        assertEquals(request, requestCaptor.getValue());
        assertEquals(response, responseCaptor.getValue());
        assertEquals("/WEB-INF/views/customerinfo.jsp", stringCaptor.getValue());
    }
}
