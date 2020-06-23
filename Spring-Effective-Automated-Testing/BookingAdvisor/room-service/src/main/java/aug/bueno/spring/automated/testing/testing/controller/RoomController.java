package aug.bueno.spring.automated.testing.testing.controller;

import aug.bueno.spring.automated.testing.model.Room;
import aug.bueno.spring.automated.testing.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {

	private RoomService service;
	
	public RoomController(RoomService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<Iterable<Room>> getAllRooms(){
		return ResponseEntity.ok(service.getAllRooms());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Room> findRoomById(long id){
		return ResponseEntity.ok(service.findRoom(id));
	}
	
	@PostMapping
	public ResponseEntity<?> addRoom(Room room){
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateRoom(Room room){
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRoom(long id){
		return ResponseEntity.ok().build();
	}
}
