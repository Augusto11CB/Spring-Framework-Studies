package aug.bueno.spring.automated.testing.testing.service;

import aug.bueno.spring.automated.testing.testing.exception.CustomerSerivceClientException;
import aug.bueno.spring.automated.testing.testing.model.Customer;

public interface CustomerService {
	Iterable<Customer> findAllCustomers();

	Iterable<Customer> findCustomersByFNameLName(String firstName, String lastName) throws CustomerSerivceClientException;
}
