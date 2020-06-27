package aug.bueno.spring.automated.testing.testing.repo;


import aug.bueno.spring.automated.testing.testing.model.Customer;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

public class ITCustomerRepoImpl extends ITCustomerRepoAbstract {


    private SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");


    @Test
    public void testDatabaseCall() {
        Customer customer = repo.findById(1L).get();

        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("Middle", customer.getMiddleName());
        assertEquals("", customer.getSuffix());
        assertEquals("2017-10-30", dateFormat.format(customer.getDateOfLastStay()));


    }
}
