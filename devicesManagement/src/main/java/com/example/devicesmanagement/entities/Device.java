package com.example.devicesmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "devices")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column
    private String description;

    @Column
    private String address;

    @Column(name = "max_hr_consumption")
    private int maxHrConsumption;

}
