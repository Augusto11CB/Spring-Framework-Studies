package aug.bueno.spring.automated.testing.service.impl;

import aug.bueno.spring.automated.testing.exception.RoomServiceClientException;
import aug.bueno.spring.automated.testing.model.Room;
import aug.bueno.spring.automated.testing.repo.RoomRepo;
import aug.bueno.spring.automated.testing.service.RoomService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {
    private RoomRepo repo;

    public RoomServiceImpl(RoomRepo repo) {
        this.repo = repo;
    }

    @Override
    public Iterable<Room> getAllRooms() {
        return repo.findAll();
    }

    @Override
    public Room findRoom(long roomId) {
        return repo.findById(roomId).orElse(new Room());
    }

    @Override
    public void updateRoom(Room room) {
        repo.save(room);
    }

    @Override
    public void addRoom(Room room) {
        repo.save(room);
    }

    @Override
    public Room findByRoomNumber(String roomNumber) {
        if (Strings.isNotEmpty(roomNumber) && StringUtils.isNumeric(roomNumber)) {
            Room room = repo.findByRoomNumber(roomNumber);
            if (room == null) {
                throw new RoomServiceClientException("Room number: " + roomNumber + ", does not exist.");
            }
            return room;
        } else {
            throw new RoomServiceClientException("Room number: " + roomNumber + ", is an invalid room number format.");
        }
    }

}
