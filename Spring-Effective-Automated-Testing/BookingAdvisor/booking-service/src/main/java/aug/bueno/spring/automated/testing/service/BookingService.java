package aug.bueno.spring.automated.testing.service;

import aug.bueno.spring.automated.testing.model.Booking;

public interface BookingService {
	public Booking getBookingById(long id);
	public Iterable<Booking> getBookings();
	public Long addBooking(Booking booking);
	public void updateBooking(Booking booking);
	public void deleteBooking(long id);
}
