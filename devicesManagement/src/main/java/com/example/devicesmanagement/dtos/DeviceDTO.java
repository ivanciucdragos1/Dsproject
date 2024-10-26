package com.example.devicesmanagement.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class DeviceDTO {

    private UUID id;
    private UUID userId;
    private String description;
    private String address;
    private int maxHrConsumption;

}
