package com.example.canal.service;

import com.example.canal.constant.MeetingType;
import com.example.canal.model.Reservation;
import com.example.canal.model.ReservationCommand;
import com.example.canal.model.ReservationResponse;

import java.util.List;

public interface ReservationService {

    ReservationResponse reserver(ReservationCommand reservationCommand);

    List<ReservationResponse> reserveration();
}
