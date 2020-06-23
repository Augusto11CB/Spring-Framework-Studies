package aug.bueno.spring.automated.testing.testing.controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import aug.bueno.spring.automated.testing.testing.config.SecurityConfig;
import aug.bueno.spring.automated.testing.testing.model.Customer;
import aug.bueno.spring.automated.testing.testing.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerController.class)
@Import(SecurityConfig.class)
public class TestCustomerControllerSecurity {

	@MockBean
	private CustomerService service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testFindAllCustomersWithNoUser() throws Exception {
		mockMvc.perform(get("/customers")).andExpect(status().isUnauthorized());
	}
	
	@Test
	public void testFindAllCustomersWithInvalidUser() throws Exception {
		when(service.findAllCustomers()).thenReturn(Arrays.asList(new Customer(), new Customer()));
		mockMvc.perform(get("/customers").with(user("invalidUser")))
			.andExpect(status().isForbidden());
	}
	
	@Test
	public void testFindAllCustomersWithUserWhoDoesntHaveRightRole() throws Exception {
		when(service.findAllCustomers()).thenReturn(Arrays.asList(new Customer(), new Customer()));
		mockMvc.perform(get("/customers").with(user("todd")
				.password("abc123").roles("USER"))).andExpect(status().isForbidden());
	}
	
	@Test
	public void testFindAllCustomersWithValidUser() throws Exception {
		mockMvc.perform(get("/customers").with(user("bojack")
				.password("abc123").roles("ADMIN"))).andExpect(status().isOk());
	}
}
