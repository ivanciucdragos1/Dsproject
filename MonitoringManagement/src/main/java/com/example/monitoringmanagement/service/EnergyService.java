package com.example.monitoringmanagement.service;

import com.example.monitoringmanagement.dtos.EnergyConsumptionDTO;
import com.example.monitoringmanagement.entities.EnergyConsumption;
import com.example.monitoringmanagement.mappers.EnergyConsumptionMapper;
import com.example.monitoringmanagement.repositories.EnergyConsumptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnergyService {
    private final EnergyConsumptionMapper energyConsumptionMapper;
    private final EnergyConsumptionRepository energyConsumptionRepository;

    public List<EnergyConsumption> getAllByDeviceId(UUID deviceId){
        List<EnergyConsumption> energyConsumptions = energyConsumptionRepository.findByDeviceId(deviceId);
        return energyConsumptions;
    }
}
