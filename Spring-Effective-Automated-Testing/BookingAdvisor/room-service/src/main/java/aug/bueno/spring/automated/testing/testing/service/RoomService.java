package aug.bueno.spring.automated.testing.testing.service;

import aug.bueno.spring.automated.testing.model.Room;

public interface RoomService {
    Iterable<Room> getAllRooms();

    Room findRoom(long roomId);

    void updateRoom(Room room);

    void addRoom(Room room);

    Room findByRoomNumber(String string);
}
