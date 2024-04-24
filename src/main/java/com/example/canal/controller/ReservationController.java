package com.example.canal.controller;


import com.example.canal.model.MeetingRoom;
import com.example.canal.model.Reservation;
import com.example.canal.model.ReservationCommand;
import com.example.canal.model.ReservationResponse;
import com.example.canal.service.MeetingRoomService;
import com.example.canal.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReservationController {

    MeetingRoomService meetingRoomService;
    ReservationService reservationService;

    public ReservationController(MeetingRoomService meetingRoomService,
                                 ReservationService reservationService){
        this.meetingRoomService = meetingRoomService;
        this.reservationService = reservationService;
    }


    @GetMapping("/reserveration")
    public List<ReservationResponse> reserveration(){
        return reservationService.reserveration();
    }

    @PostMapping("/reserver")
    public ReservationResponse reserver(@RequestBody ReservationCommand reservationCommand){
        return reservationService.reserver(reservationCommand);
    }
}
