package com.example.canal.repository;

import com.example.canal.constant.Day;
import com.example.canal.model.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeetingRoomRepository extends CrudRepository<MeetingRoom, String> {

    @Query(value = "SELECT * FROM Meeting_Room"
            , nativeQuery = true)
    List<MeetingRoom> findMeetingRooms();


    @Query(value = "SELECT * FROM Meeting_Room m WHERE m.capacity * 0.7 >= :nbr " +
            "AND m.name NOT IN (SELECT r.meeting_room FROM reservation r" +
            " WHERE r.reservation_day=:day AND (r.end_hour=:start OR r.start_hour=:start OR r.start_hour-1=:start))"+
            " order by m.capacity", nativeQuery = true)
    List<MeetingRoom> findAvailableMeetingRoom(Integer nbr, Integer start, String day);


}
