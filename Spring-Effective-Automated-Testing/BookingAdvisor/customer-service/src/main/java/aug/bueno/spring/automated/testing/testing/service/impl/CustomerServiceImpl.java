package aug.bueno.spring.automated.testing.testing.service.impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import aug.bueno.spring.automated.testing.testing.exception.CustomerSerivceClientException;
import aug.bueno.spring.automated.testing.testing.model.Customer;
import aug.bueno.spring.automated.testing.testing.repo.CustomerRepo;
import aug.bueno.spring.automated.testing.testing.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerRepo repo;
	
	public CustomerServiceImpl(CustomerRepo repo) {
		this.repo = repo;
	}

	@Override
	public Iterable<Customer> findAllCustomers() {
		return repo.findAll();
	}

	@Override
	public Iterable<Customer> findCustomersByFNameLName(String firstName, String lastName) throws CustomerSerivceClientException {
		if(StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName)) {
			throw new CustomerSerivceClientException("Missing required parameter!");
		} else if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
			throw new CustomerSerivceClientException("First and/or last name is not in proper format!");
		}
		return repo.findCustomersByFirstNameAndLastName(firstName, lastName);
	}

}
