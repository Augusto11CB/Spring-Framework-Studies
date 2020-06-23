package aug.bueno.spring.automated.testing.testing.repo;

import aug.bueno.spring.automated.testing.model.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepo extends CrudRepository<Room, Long> {
	Room findByRoomNumber(String roomNumber);
}
