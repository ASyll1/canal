package com.example.canal.repository;

import com.example.canal.constant.Day;
import com.example.canal.model.MeetingRoom;
import com.example.canal.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, String>  {

    @Query(value = "SELECT quantity FROM equipment where name=:name"
            , nativeQuery = true)
    Integer findInitialEquipmentQuantity(String name);

    @Query(value = "SELECT COUNT(*) FROM reservation r JOIN MEETING_EQUIPMENT ra " +
            "ON r.id=ra.reservation_id where r.reservation_day=:day AND r.start_hour=:startHour "+
            "AND ra.EQUIPMENT_NAME=:name", nativeQuery = true)
    Integer findAvailableEquipmentQuantity(String name , Integer startHour, String day);
}
