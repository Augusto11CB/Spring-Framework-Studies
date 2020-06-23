package aug.bueno.spring.automated.testing.testing.service;

import aug.bueno.spring.automated.testing.model.Room;
import aug.bueno.spring.automated.testing.repo.RoomRepo;
import aug.bueno.spring.automated.testing.service.impl.RoomServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoomServiceImplTest {

    private RoomRepo mockRepo;

    private RoomService service;

    @BeforeEach
    void init() {

        this.mockRepo = mock(RoomRepo.class);
        this.service = new RoomServiceImpl(this.mockRepo);
    }

    @Test
    public void lookupExistingRoom() {

        when(mockRepo.findByRoomNumber(anyString())).thenReturn(new Room());

        Room room = service.findByRoomNumber("100");

        assertNotNull(room);
    }

    @Test
    public void throwExceptionForNonExistingRoom() {

        when(mockRepo.findByRoomNumber(anyString())).thenReturn(null);

        try {
            service.findByRoomNumber("100");
            fail("Exception should had been thrown");
        } catch (Exception e) {
            assertEquals("Room number: 100, does not exist.", e.getMessage());
        }
    }

    @Test
    public void throwExceptionInvalidRoomNumberFormat() {

        RoomService service = new RoomServiceImpl(mockRepo);

        try {
            service.findByRoomNumber("BAD ROOM NUMBER!");
            fail("Exception should had been thrown");
        } catch (Exception e) {
            assertEquals("Room number: BAD ROOM NUMBER!, is an invalid room number format.", e.getMessage());
        }
    }

    @Test
    public void throwExceptionInvalidRoomNumberNull() {

        try {
            service.findByRoomNumber(null);
            fail("Exception should had been thrown");
        } catch (Exception e) {
            assertEquals("Room number: null, is an invalid room number format.", e.getMessage());
        }
    }
}
