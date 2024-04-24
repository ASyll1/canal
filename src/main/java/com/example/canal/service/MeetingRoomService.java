package com.example.canal.service;

import com.example.canal.constant.Day;
import com.example.canal.model.MeetingRoom;

import java.util.List;

public interface MeetingRoomService {

    List<MeetingRoom> saveAll(List<MeetingRoom> meetingRooms);

    List<MeetingRoom> findMeetingRooms();

    List<MeetingRoom> findAvailableMeetingRoom(Integer nbr, Integer start, Day day);
}
