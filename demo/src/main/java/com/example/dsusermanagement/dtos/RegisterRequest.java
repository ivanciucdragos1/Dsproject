package com.example.dsusermanagement.dtos;

import com.example.dsusermanagement.constants.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {
    private String name;
    private Role role;
    private String password;
}
