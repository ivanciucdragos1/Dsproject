package com.example.monitoringmanagement.mappers;

import com.example.monitoringmanagement.dtos.EnergyConsumptionDTO;
import com.example.monitoringmanagement.entities.EnergyConsumption;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnergyConsumptionMapper {
    EnergyConsumptionDTO toDto(EnergyConsumption energyConsumption);
    EnergyConsumption fromDto(EnergyConsumptionDTO energyConsumptionDTO);
}
