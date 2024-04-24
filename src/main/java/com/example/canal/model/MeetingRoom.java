package com.example.canal.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;




@Entity(name = "MeetingRoom")
@Table(name = "MeetingRoom")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeetingRoom {

    @Id
    @Column
    private String name;

    @Column
    private int capacity;
    @Column
    private boolean hasScreen;
    @Column
    private boolean hasWebcam;
    @Column
    private boolean hasTable;
    @Column
    private boolean hasOctopus;
}
