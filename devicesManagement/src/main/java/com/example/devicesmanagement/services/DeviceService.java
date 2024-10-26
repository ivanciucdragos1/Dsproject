package com.example.devicesmanagement.services;

import com.example.devicesmanagement.dtos.DeviceDTO;
import com.example.devicesmanagement.entities.Device;
import com.example.devicesmanagement.mappers.DeviceMapper;
import com.example.devicesmanagement.repositories.DeviceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    public List<DeviceDTO> getDevices(){
        return deviceRepository.findAll().stream()
                .map(deviceMapper::toDto)
                .collect(Collectors.toList());
    }

    public void  deleteDevice(UUID id){
        deviceRepository.deleteById(id);
    }

    public DeviceDTO createDevice(DeviceDTO deviceDTO){
        return deviceMapper.toDto(deviceRepository
                .save(deviceMapper.fromDto(deviceDTO)));
    }

    public DeviceDTO editDevice(DeviceDTO deviceDTO){
        Device actDevice = deviceRepository.findById(deviceDTO.getId()).orElse(null);
        actDevice.setUserId(deviceDTO.getUserId());
        actDevice.setDescription(deviceDTO.getDescription());
        actDevice.setMaxHrConsumption(deviceDTO.getMaxHrConsumption());
        actDevice.setAddress(deviceDTO.getAddress());
        return deviceMapper.toDto(deviceRepository.save(actDevice));
    }

    public List<DeviceDTO> getUserDevices(UUID userId){
        return deviceRepository.findAllByUserId(userId).stream()
                .map(deviceMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DeviceDTO> editUserDevices(UUID oldUserId, UUID newUserId){
        List<Device> devices = deviceRepository.findAllByUserId(oldUserId)
                .stream()
                .map(d -> {
                    d.setUserId(newUserId);
                    return d;
                })
                .collect(Collectors.toList());

        return devices.stream().map(deviceMapper::toDto).collect(Collectors.toList());
    }

    public void deleteAllByUserId(UUID userId){
        deviceRepository.deleteAllByUserId(userId);
    }
}
