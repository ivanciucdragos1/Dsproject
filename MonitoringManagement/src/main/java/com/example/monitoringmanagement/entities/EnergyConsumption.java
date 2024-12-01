package com.example.monitoringmanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "energy")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class EnergyConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "device_id")
    private UUID deviceId;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "hr_consumption")
    private double hrConsumption;
}
