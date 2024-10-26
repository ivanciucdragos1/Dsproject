package com.example.devicesmanagement.controllers;

import com.example.devicesmanagement.dtos.DeviceDTO;
import com.example.devicesmanagement.services.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.example.UrlMappings.API_URL;

@RestController
@RequestMapping(API_URL)
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping
    public List<DeviceDTO> findAll(){
        return deviceService.getDevices();
    }

    @GetMapping("/{userId}")
    public List<DeviceDTO> findAllByUserId(@PathVariable UUID userId){
        return deviceService.getUserDevices(userId);
    }

    @PostMapping
    public DeviceDTO createDevice(@RequestBody DeviceDTO deviceDTO){
        return deviceService.createDevice(deviceDTO);
    }

    @PatchMapping
    public DeviceDTO editDevice(@RequestBody DeviceDTO deviceDTO){
        return deviceService.editDevice(deviceDTO);
    }

    @PatchMapping("/edit")
    public List<DeviceDTO> editUserDevices(@RequestParam UUID oldUserId, @RequestParam UUID newUserId){
        return deviceService.editUserDevices(oldUserId, newUserId);
    }

    @DeleteMapping
    public void deleteDevice(@RequestParam UUID id){
        deviceService.deleteDevice(id);
    }

    @DeleteMapping("/users")
    public void deleteUserDevices(@RequestParam UUID userId){
        deviceService.deleteAllByUserId(userId);
    }
}
