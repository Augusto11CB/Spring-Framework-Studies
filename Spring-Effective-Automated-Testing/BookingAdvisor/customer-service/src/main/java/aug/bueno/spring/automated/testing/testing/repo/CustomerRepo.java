package aug.bueno.spring.automated.testing.testing.repo;

import org.springframework.data.repository.CrudRepository;

import aug.bueno.spring.automated.testing.testing.model.Customer;

public interface CustomerRepo extends CrudRepository<Customer, Long> {

	Iterable<Customer> findCustomersByFirstNameAndLastName(String firstName, String lastName);

}
