package com.example.dsusermanagement.mappers;

import com.example.dsusermanagement.dtos.UserDTO;
import com.example.dsusermanagement.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto(User user);
    User fromDto(UserDTO userDTO);
}
