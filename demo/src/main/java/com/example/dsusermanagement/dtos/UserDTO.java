package com.example.dsusermanagement.dtos;

import com.example.dsusermanagement.constants.Role;
import lombok.*;

import java.util.UUID;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Data
public class UserDTO {
    private UUID id;
    private String name;
    private Role role;
    private String password;
}
