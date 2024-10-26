package com.example.devicesmanagement.repositories;

import com.example.devicesmanagement.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {
    List<Device> findAllByUserId(UUID userId);
    void deleteAllByUserId(UUID userId);
}
