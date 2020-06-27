package aug.bueno.spring.automated.testing.service;

import aug.bueno.spring.automated.testing.model.Booking;

public interface BookingValidationService {
	boolean doesBookingExist(long id);
	boolean isBookingValid(Booking booking);
}
