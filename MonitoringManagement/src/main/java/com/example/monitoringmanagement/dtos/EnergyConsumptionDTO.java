package com.example.monitoringmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EnergyConsumptionDTO {

    private UUID device_id;
    private long time;
    private double value;

}
