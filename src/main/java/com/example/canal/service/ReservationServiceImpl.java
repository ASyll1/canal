package com.example.canal.service;

import com.example.canal.constant.Day;
import com.example.canal.constant.MeetingType;
import com.example.canal.model.*;
import com.example.canal.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService{

    MeetingRoomService meetingRoomService;

    ReservationRepository reservationRepository;

    ReservationServiceImpl(MeetingRoomService meetingRoomService
            , ReservationRepository reservationRepository){
        this.meetingRoomService = meetingRoomService;
        this.reservationRepository = reservationRepository;
    }


    public Reservation reserve(ReservationCommand reservationCommand) {
        List<MeetingRoom> availableRooms = meetingRoomService.findAvailableMeetingRoom(
                reservationCommand.getSize(),reservationCommand.getStartHour(),
                reservationCommand.getDay());
        Reservation reservation = new Reservation();
        if(availableRooms == null || availableRooms.isEmpty()){
            return null;
        }
        switch (reservationCommand.getMeetingType()){
            case SPEC: for (MeetingRoom room : availableRooms){
                if(room.isHasTable()){
                    reservation.setMeetingRoom(room);
                    break;
                } else if(hasAvailaibleEquipment
                        ("table",reservationCommand.getStartHour(),reservationCommand.getDay())){
                    Set<Equipment> additionalEquipment = new HashSet<>();
                    reservation.setMeetingRoom(room);
                    Equipment equipment= new Equipment();
                    equipment.setName("table");
                    equipment.setQuantity(1);
                    additionalEquipment.add(equipment);
                    reservation.setAdditionnalEquipment(additionalEquipment);
                    break;
                }
            }
            case RS: reservation.setMeetingRoom(availableRooms.get(0));
                break;
            case RC: for (MeetingRoom room: availableRooms){
                Set<Equipment> additionalEquipment = new HashSet<>();
                if (!room.isHasScreen() && hasAvailaibleEquipment
                        ("screen", reservationCommand.getStartHour(),reservationCommand.getDay())) {
                    Equipment equipment= new Equipment();
                    equipment.setName("screen");
                    equipment.setQuantity(1);
                    additionalEquipment.add(equipment);
                }  if (!room.isHasScreen() && !hasAvailaibleEquipment
                        ("screen", reservationCommand.getStartHour(),reservationCommand.getDay())) {
                    continue;
                }
                if (!room.isHasTable() && hasAvailaibleEquipment
                        ("table", reservationCommand.getStartHour(),reservationCommand.getDay())) {
                    Equipment equipment= new Equipment();
                    equipment.setName("table");
                    equipment.setQuantity(1);
                    additionalEquipment.add(equipment);
                }  if (!room.isHasScreen() && !hasAvailaibleEquipment
                        ("table", reservationCommand.getStartHour(),reservationCommand.getDay())) {
                    continue;
                }
                if (!room.isHasOctopus() && hasAvailaibleEquipment
                        ("octopus", reservationCommand.getStartHour(),reservationCommand.getDay())) {
                    Equipment equipment= new Equipment();
                    equipment.setName("octopus");
                    equipment.setQuantity(1);
                    additionalEquipment.add(equipment);
                }  if (!room.isHasScreen() && !hasAvailaibleEquipment
                        ("octopus", reservationCommand.getStartHour(),reservationCommand.getDay())) {
                    continue;
                }
                reservation.setAdditionnalEquipment(additionalEquipment);
                reservation.setMeetingRoom(room);
                break;

            }
            case VC: for (MeetingRoom room: availableRooms){
                Set<Equipment> additionalEquipment = new HashSet<>();
                if (!room.isHasScreen() && hasAvailaibleEquipment
                        ("screen", reservationCommand.getStartHour(),reservationCommand.getDay())) {
                    Equipment equipment= new Equipment();
                    equipment.setName("screen");
                    equipment.setQuantity(1);
                    additionalEquipment.add(equipment);
                }  if (!room.isHasScreen() && !hasAvailaibleEquipment
                        ("screen", reservationCommand.getStartHour(),reservationCommand.getDay())) {
                    continue;
                }
                if (!room.isHasTable() && hasAvailaibleEquipment
                        ("webcam", reservationCommand.getStartHour(),reservationCommand.getDay())) {
                    Equipment equipment= new Equipment();
                    equipment.setName("webcam");
                    equipment.setQuantity(1);
                    additionalEquipment.add(equipment);
                }  if (!room.isHasScreen() && !hasAvailaibleEquipment
                        ("webcam", reservationCommand.getStartHour(),reservationCommand.getDay())) {
                    continue;
                }
                if (!room.isHasOctopus() && hasAvailaibleEquipment
                        ("octopus", reservationCommand.getStartHour(),reservationCommand.getDay())) {
                    Equipment equipment= new Equipment();
                    equipment.setName("octopus");
                    equipment.setQuantity(1);
                    additionalEquipment.add(equipment);
                }  if (!room.isHasScreen() && !hasAvailaibleEquipment
                        ("octopus", reservationCommand.getStartHour(),reservationCommand.getDay())) {
                    continue;
                }
                reservation.setAdditionnalEquipment(additionalEquipment);
                reservation.setMeetingRoom(room);
                break;

            }


        }
        if(reservation.getMeetingRoom() == null){
            return null;
        }
        reservation.setReservedBy(reservationCommand.getReservedBy());
        reservation.setStartHour(reservationCommand.getStartHour());
        reservation.setEndHour(reservationCommand.getStartHour()+1);
        reservation.setReservationDay(reservationCommand.getDay().toString());
        return reservationRepository.save(reservation);
    }

    public boolean hasAvailaibleEquipment(String name, Integer startHour, Day day){
        return reservationRepository.findInitialEquipmentQuantity(name) >
                reservationRepository.findAvailableEquipmentQuantity(name,startHour,day.toString());

    }

    @Override
    public ReservationResponse reserver(ReservationCommand reservationCommand){
        Reservation reservation = reserve(reservationCommand);
        ReservationResponse reservationResponse = new ReservationResponse();
        if(reservation == null){
            reservationResponse.setMessage("Nous n'avons pas pu trouver une salle pour votre creneau");
            return  reservationResponse;
        }
        reservationResponse.setSalle(reservation.getMeetingRoom().getName());
        reservationResponse.setReservedBy(reservation.getReservedBy());
        reservationResponse.setJour(reservation.getReservationDay().toString());
        reservationResponse.setSize(reservationCommand.getSize());
        reservationResponse.setAdditionalEquipment(
                reservation.getAdditionnalEquipment().isEmpty()? new ArrayList<>():
                reservation.getAdditionnalEquipment().stream()
                        .map(item ->item.getName()).collect(Collectors.toList()));
        reservationResponse.setCreaneau(""+reservation.getStartHour()+"h-"+reservation.getEndHour()+"h");
        reservationResponse.setMessage("Reservé avec succès");
        reservationResponse.setMeetingType(reservationCommand.getMeetingType());
        return reservationResponse;
    }

    @Override
    public List<ReservationResponse> reserveration(){

        List<ReservationResponse> reservationResponses = new ArrayList<>();
        reservationRepository.findAll().forEach(reservation -> {
            ReservationResponse reservationResponse = new ReservationResponse();
            reservationResponse.setSalle(reservation.getMeetingRoom().getName());
            reservationResponse.setReservedBy(reservation.getReservedBy());
            reservationResponse.setJour(reservation.getReservationDay());
            reservationResponse.setSize(reservation.getSize());
            reservationResponse.setAdditionalEquipment(
                    reservation.getAdditionnalEquipment().isEmpty()? new ArrayList<>():
                            reservation.getAdditionnalEquipment().stream()
                                    .map(item ->item.getName()).collect(Collectors.toList()));
            reservationResponse.setCreaneau(""+reservation.getStartHour()+"h-"+reservation.getEndHour()+"h");
            reservationResponse.setMessage("Reservé avec succès");
            reservationResponses.add(reservationResponse);
        });

        return reservationResponses;
    }
}
