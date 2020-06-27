package aug.bueno.spring.automated.testing.testing.controller;

import aug.bueno.spring.automated.testing.testing.exception.CustomerSerivceClientException;
import aug.bueno.spring.automated.testing.testing.model.Customer;
import aug.bueno.spring.automated.testing.testing.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerController.class)
public class TestCustomerController {

	@MockBean
	private CustomerService service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testSuccessfulFindAllCustomers() throws Exception {
		when(service.findAllCustomers()).thenReturn(Arrays.asList(new Customer(), new Customer()));
		mockMvc.perform(get("/customers")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	public void testSuccessSearchCustomersByFNameLName() throws Exception {
		when(service.findCustomersByFNameLName("test", "test"))
		.thenReturn(Arrays.asList(new Customer()));
		mockMvc.perform(get("/customers/search/byFNameLName?fName=test&lName=test"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(1)));
	}

	@Test
	public void testMissingParamSearchCustomersByFNameLName() throws Exception {
		mockMvc.perform(get("/customers/search/byFNameLName?fName=test")).andExpect(status().isBadRequest())
				.andExpect(content().string(""));
		verify(service, times(0)).findCustomersByFNameLName(any(), any());
	}

	@Test
	public void testInvalidParamSearchCustomersByFNameLName() throws Exception {
		when(service.findCustomersByFNameLName(any(), any()))
				.thenThrow(new CustomerSerivceClientException("Invalid parameter passed in!"));
		mockMvc.perform(get("/customers/search/byFNameLName?fName=test&lName=invalid"))
				.andExpect(status().isBadRequest()).andExpect(content().contentType("text/plain;charset=UTF-8"))
				.andExpect(content().string("Invalid parameter passed in!"));
	}

}
