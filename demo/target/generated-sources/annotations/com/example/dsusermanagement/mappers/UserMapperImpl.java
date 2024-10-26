package com.example.dsusermanagement.mappers;

import com.example.dsusermanagement.dtos.UserDTO;
import com.example.dsusermanagement.dtos.UserDTO.UserDTOBuilder;
import com.example.dsusermanagement.entities.User;
import com.example.dsusermanagement.entities.User.UserBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-25T15:36:11+0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.id( user.getId() );
        userDTO.name( user.getName() );
        userDTO.role( user.getRole() );
        userDTO.password( user.getPassword() );

        return userDTO.build();
    }

    @Override
    public User fromDto(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserBuilder user = User.builder();

        user.id( userDTO.getId() );
        user.name( userDTO.getName() );
        user.role( userDTO.getRole() );
        user.password( userDTO.getPassword() );

        return user.build();
    }
}
