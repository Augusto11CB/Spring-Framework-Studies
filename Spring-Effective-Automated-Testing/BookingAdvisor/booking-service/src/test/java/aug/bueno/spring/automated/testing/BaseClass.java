package aug.bueno.spring.automated.testing;



import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

import aug.bueno.spring.automated.testing.controller.BookingController;
import aug.bueno.spring.automated.testing.model.Booking;
import aug.bueno.spring.automated.testing.service.BookingService;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest (classes = BookingServiceApplication.class)
@RunWith(SpringRunner.class )
@AutoConfigureMessageVerifier
public class BaseClass {
	@Autowired
    private BookingController bookingController;

	@MockBean
	private BookingService bookingService;
	
    @Before
    public void before() throws Throwable {
    		Mockito.when(bookingService.addBooking(any())).thenReturn(1L);
        RestAssuredMockMvc.standaloneSetup(this.bookingController);
    }

}
