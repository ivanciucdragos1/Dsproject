package com.example.devicesmanagement.mappers;

import com.example.devicesmanagement.dtos.DeviceDTO;
import com.example.devicesmanagement.dtos.DeviceDTO.DeviceDTOBuilder;
import com.example.devicesmanagement.entities.Device;
import com.example.devicesmanagement.entities.Device.DeviceBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-26T01:02:24+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class DeviceMapperImpl implements DeviceMapper {

    @Override
    public DeviceDTO toDto(Device device) {
        if ( device == null ) {
            return null;
        }

        DeviceDTOBuilder deviceDTO = DeviceDTO.builder();

        deviceDTO.id( device.getId() );
        deviceDTO.userId( device.getUserId() );
        deviceDTO.description( device.getDescription() );
        deviceDTO.address( device.getAddress() );
        deviceDTO.maxHrConsumption( device.getMaxHrConsumption() );

        return deviceDTO.build();
    }

    @Override
    public Device fromDto(DeviceDTO deviceDTO) {
        if ( deviceDTO == null ) {
            return null;
        }

        DeviceBuilder device = Device.builder();

        device.id( deviceDTO.getId() );
        device.userId( deviceDTO.getUserId() );
        device.description( deviceDTO.getDescription() );
        device.address( deviceDTO.getAddress() );
        device.maxHrConsumption( deviceDTO.getMaxHrConsumption() );

        return device.build();
    }
}
