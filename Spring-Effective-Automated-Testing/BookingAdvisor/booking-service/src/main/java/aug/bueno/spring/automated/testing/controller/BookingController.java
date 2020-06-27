package aug.bueno.spring.automated.testing.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aug.bueno.spring.automated.testing.model.Booking;
import aug.bueno.spring.automated.testing.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	private BookingService service;

	public BookingController(BookingService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<URI> reserveRoom(Booking booking) {
		return ResponseEntity.created(URI.create("bookings/" + service.addBooking(booking))).build();
	}
}
