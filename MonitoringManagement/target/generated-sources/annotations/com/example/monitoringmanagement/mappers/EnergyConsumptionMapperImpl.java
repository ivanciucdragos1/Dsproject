package com.example.monitoringmanagement.mappers;

import com.example.monitoringmanagement.dtos.EnergyConsumptionDTO;
import com.example.monitoringmanagement.dtos.EnergyConsumptionDTO.EnergyConsumptionDTOBuilder;
import com.example.monitoringmanagement.entities.EnergyConsumption;
import com.example.monitoringmanagement.entities.EnergyConsumption.EnergyConsumptionBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-01T14:00:47+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class EnergyConsumptionMapperImpl implements EnergyConsumptionMapper {

    @Override
    public EnergyConsumptionDTO toDto(EnergyConsumption energyConsumption) {
        if ( energyConsumption == null ) {
            return null;
        }

        EnergyConsumptionDTOBuilder energyConsumptionDTO = EnergyConsumptionDTO.builder();

        return energyConsumptionDTO.build();
    }

    @Override
    public EnergyConsumption fromDto(EnergyConsumptionDTO energyConsumptionDTO) {
        if ( energyConsumptionDTO == null ) {
            return null;
        }

        EnergyConsumptionBuilder energyConsumption = EnergyConsumption.builder();

        return energyConsumption.build();
    }
}
