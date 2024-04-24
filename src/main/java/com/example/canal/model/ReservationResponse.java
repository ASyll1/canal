package com.example.canal.model;

import com.example.canal.constant.MeetingType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {
    private String  creaneau;
    private String salle;
    private String jour;
    private String message;
    private String reservedBy;
    private Integer size;
    private MeetingType MeetingType;
    private List<String> additionalEquipment;
}
