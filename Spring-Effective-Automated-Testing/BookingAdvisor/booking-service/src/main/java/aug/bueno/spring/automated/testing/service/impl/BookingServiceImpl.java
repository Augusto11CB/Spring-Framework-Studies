package aug.bueno.spring.automated.testing.service.impl;

import org.springframework.stereotype.Service;

import aug.bueno.spring.automated.testing.model.Booking;
import aug.bueno.spring.automated.testing.repo.BookingRepo;
import aug.bueno.spring.automated.testing.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
	private BookingRepo repo;
	
	public BookingServiceImpl(BookingRepo repo) {
		this.repo = repo;
	}

	@Override
	public Booking getBookingById(long id) {
		return repo.findOne(id);
	}

	@Override
	public Iterable<Booking> getBookings() {
		return repo.findAll();
	}

	@Override
	public Long addBooking(Booking booking) {
		return repo.save(booking).getId();
	}

	@Override
	public void updateBooking(Booking booking) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBooking(long id) {
		repo.delete(id);
	}

}
