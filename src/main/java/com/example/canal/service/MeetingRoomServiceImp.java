package com.example.canal.service;

import com.example.canal.constant.Day;
import com.example.canal.model.MeetingRoom;
import com.example.canal.repository.MeetingRoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingRoomServiceImp implements MeetingRoomService{

    private MeetingRoomRepository meetingRoomRepository;

   public  MeetingRoomServiceImp(MeetingRoomRepository meetingRoomRepository){
       this.meetingRoomRepository = meetingRoomRepository;
   }

    public List<MeetingRoom> saveAll(List<MeetingRoom> meetingRooms){
        List<MeetingRoom> lists = new ArrayList<>();
         meetingRoomRepository.saveAll(meetingRooms).forEach(list -> lists.add(list));
         return  lists;
    }

    public List<MeetingRoom> findMeetingRooms(){
        return meetingRoomRepository.findMeetingRooms();
    }

    public List<MeetingRoom> findAvailableMeetingRoom(Integer nbr, Integer start, Day day) {
       return meetingRoomRepository.findAvailableMeetingRoom(nbr,start,day.toString());
    }
}
