package com.example.dsusermanagement.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Data
@RequiredArgsConstructor
public class RestClient {
    private final String apiUrl = "http://localhost:8088/devices";
    private RestTemplate restTemplate;
}
