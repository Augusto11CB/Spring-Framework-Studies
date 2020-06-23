package aug.bueno.spring.automated.testing.testing.config;

import aug.bueno.spring.automated.testing.testing.controller.CustomerController;
import aug.bueno.spring.automated.testing.testing.service.CustomerService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerController.class)
@Import(ControllerLoggingAspect.class)
public class TestControllerAspectLoggingWeaving {

	@MockBean
	private CustomerService service;

	@Autowired
	private MockMvc mockMvc;

	@AfterAll
	public void cleanUp() {
		TestAppender.clearEvents();
	}

	@Test
	public void testLoggingAspectForSuccessfulRequest() throws Exception {
		mockMvc.perform(get("/customers"));
		assertThat(TestAppender.getEvents()).extracting("message").contains("Entering Method: findAllCustomers",
				"Exiting Method: findAllCustomers");
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testLoggingAspectWhenExceptionIsThrown() throws Exception {
		when(service.findAllCustomers()).thenThrow(RuntimeException.class);
		try {
			mockMvc.perform(get("/customers"));
		} catch (Exception e) {
			// Ignore thrown exception.
		}
		assertThat(TestAppender.getEvents()).extracting("message").contains("Entering Method: findAllCustomers",
				"Exception occurred in method: findAllCustomers");
	}

	@Test
	public void veryifyAllEndPointsAreBeingWeaved() throws Exception {
		mockMvc.perform(get("/customers"));
		mockMvc.perform(get("/customers/search/byFNameLName?fName=test&lName=test"));

		assertThat(TestAppender.getEvents()).extracting("message").contains("Entering Method: findAllCustomers",
				"Exiting Method: findAllCustomers", "Entering Method: findCustomersByFNameLName",
				"Exiting Method: findCustomersByFNameLName");
	}
}
