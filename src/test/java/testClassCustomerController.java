import com.electricalweb.controllers.CustomerController;
import com.electricalweb.entities.Customer;
import com.electricalweb.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.mockito.Mockito.*;

public class testClassCustomerController {
    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    private CustomerController customerController;
    private CustomerController spyCustomerController;
    private CustomerService customerService;
    private CustomerService spyCustomerService;
    private long teamId;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(request.getParameter("login")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("admin");
        initSpy();
        doNothing().when(spyCustomerService).forwardResponse("/WEB-INF/views/customerinfo.jsp", request, response);
        doNothing().when(spyCustomerController).setAttributes(any(HttpServletRequest.class),any(Customer.class));
        doNothing().when(response).sendRedirect("http://localhost:8080/Customer_Application_war/");
    }

    private void initSpy() {
        customerController = new CustomerController();
        spyCustomerController =  Mockito.spy(customerController);
        customerService = new CustomerService();
        spyCustomerService =  Mockito.spy(customerService);
        customerController.setCustomerService(spyCustomerService);
    }

    @Test
    public void testDoGetSetForwardResponseNotCalled() throws Exception {
        when(request.getParameter("login")).thenReturn("admin1");
        when(request.getParameter("password")).thenReturn("admin");
        spyCustomerController.doPost(request, response);
        verify(response, times(2)).sendRedirect("http://localhost:8080/Customer_Application_war/");
    }
}
