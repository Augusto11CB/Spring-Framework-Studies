package aug.bueno.spring.automated.testing.testing.model;

import lombok.Data;

/**
 * Java class representation of a record in the Rooms table.
 */
@Entity
@Table(name = "rooms")
@Data
public class Room {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "room_number")
    private String roomNumber;

    @Column(name = "weekday_price")
    private double weekdayPrice;

    @Column(name = "weekend_price")
    private double weekendPrice;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "floor")
    private String floor;

}