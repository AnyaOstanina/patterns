import com.electricalweb.controllers.UserController;
import com.electricalweb.entities.Customer;
import com.electricalweb.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private long teamId;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(request.getParameter("login")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("admin");
        initSpy();
        doNothing().when(spyUserService).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        doNothing().when(spyUserController).setAttributes(any(HttpServletRequest.class),any(Customer.class));
        doNothing().when(response).sendRedirect("http://localhost:8080/Customer_Application_war/");
    }

    private void initSpy() {
        userController = new UserController();
        spyUserController =  Mockito.spy(userController);
        userService = new UserService();
        spyUserService =  Mockito.spy(userService);
        userController.setUserService(spyUserService);
    }

    @Test
    public void testDoGetSetForwardResponseNotCalled() throws Exception {
        when(request.getParameter("login")).thenReturn("admin1");
        when(request.getParameter("password")).thenReturn("admin");
        spyUserController.doPost(request, response);
        verify(response, times(2)).sendRedirect("http://localhost:8080/Customer_Application_war/");
    }
}
