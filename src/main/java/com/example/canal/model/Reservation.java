package com.example.canal.model;


import com.example.canal.constant.Day;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.util.List;
import java.util.Set;

@Entity(name = "reservation")
@Table(name = "reservation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meeting_room", nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private MeetingRoom meetingRoom;


    private String reservationDay;

    private Integer startHour;

    private Integer endHour;

    private String ReservedBy;

    private Integer size;

    @ManyToMany()
    @JoinTable(name = "meeting_equipment",
            joinColumns = {@JoinColumn(name = "reservation_id")},
            inverseJoinColumns = { @JoinColumn(name = "equipment_name") })
    @NotFound(action = NotFoundAction.IGNORE)
    private Set<Equipment> additionnalEquipment;
}
