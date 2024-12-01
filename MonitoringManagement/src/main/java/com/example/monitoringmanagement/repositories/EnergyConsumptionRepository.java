package com.example.monitoringmanagement.repositories;

import com.example.monitoringmanagement.entities.EnergyConsumption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.util.UUID;

public interface EnergyConsumptionRepository extends JpaRepository<EnergyConsumption, UUID> {
    List<EnergyConsumption> findByDeviceId(UUID deviceId);
}
