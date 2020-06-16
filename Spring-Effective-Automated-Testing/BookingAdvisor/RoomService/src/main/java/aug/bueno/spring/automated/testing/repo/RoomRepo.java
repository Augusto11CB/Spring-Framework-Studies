package aug.bueno.spring.automated.testing.repo;

import org.springframework.data.repository.CrudRepository;

import aug.bueno.spring.automated.testing.model.Room;

public interface RoomRepo extends CrudRepository<Room, Long> {
	Room findByRoomNumber(String roomNumber);
}
