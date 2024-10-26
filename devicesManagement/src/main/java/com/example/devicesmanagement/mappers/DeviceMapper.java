package com.example.devicesmanagement.mappers;

import com.example.devicesmanagement.dtos.DeviceDTO;
import com.example.devicesmanagement.entities.Device;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeviceMapper {
    DeviceDTO toDto(Device device);
    Device fromDto(DeviceDTO deviceDTO);
}
