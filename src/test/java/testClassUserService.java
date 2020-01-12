import com.electricalweb.controllers.UserController;
import com.electricalweb.entities.User;
import com.electricalweb.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class testClassUserService {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    private UserService userService;
    private UserService spyUserService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(request.getParameter("login")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("admin");
        initSpy();
        doNothing().when(spyUserService).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        doNothing().when(response).sendRedirect("http://localhost:8080/Customer_Application_war/");
    }

    private void initSpy() {
        userService = new UserService();
        spyUserService =  Mockito.spy(userService);
    }

    @Test
    public void testGetCustomerByPasswordFindUser() throws Exception {
        when(request.getParameter("login")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("admin");
        spyUserService.getCustomerByPassword(request, response);
        verify(response, times(0)).sendRedirect("http://localhost:8080/Customer_Application_war/");
    }

    @Test
    public void testGetCustomerByPasswordNotFindUser() throws Exception {
        when(request.getParameter("login")).thenReturn("admin1");
        when(request.getParameter("password")).thenReturn("admin");
        spyUserService.getCustomerByPassword(request, response);
        verify(response, times(1)).sendRedirect("http://localhost:8080/Customer_Application_war/");
    }
}
