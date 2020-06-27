package aug.bueno.spring.automated.testing.repo;

import org.springframework.data.repository.CrudRepository;

import aug.bueno.spring.automated.testing.model.Booking;

public interface BookingRepo extends CrudRepository<Booking, Long> {

}
