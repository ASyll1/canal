package com.example.canal.model;

import com.example.canal.constant.Day;
import com.example.canal.constant.MeetingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationCommand {

    private Integer startHour;
    private String reservedBy;
    private Integer size;
    private MeetingType meetingType;
    private Day day;
}
