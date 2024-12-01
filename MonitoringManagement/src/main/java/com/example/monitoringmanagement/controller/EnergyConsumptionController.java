package com.example.monitoringmanagement.controller;

import com.example.monitoringmanagement.dtos.EnergyConsumptionDTO;
import com.example.monitoringmanagement.entities.EnergyConsumption;
import com.example.monitoringmanagement.service.EnergyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class EnergyConsumptionController {

    private final EnergyService energyService;

    @GetMapping("/{deviceId}")
    public List<EnergyConsumption> getAllByDeviceId(@PathVariable UUID deviceId){
        return energyService.getAllByDeviceId(deviceId);
    }
}
