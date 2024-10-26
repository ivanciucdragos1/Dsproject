package com.example.dsusermanagement.services;

import com.example.dsusermanagement.api.RestClient;
import com.example.dsusermanagement.dtos.UserDTO;
import com.example.dsusermanagement.entities.User;
import com.example.dsusermanagement.mappers.UserMapper;
import com.example.dsusermanagement.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final RestClient restClient;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> getUsers(){
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    public UserDTO addUser(UserDTO userDTO){

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return userMapper.toDto(userRepository
                .save(userMapper.fromDto(userDTO)));
    }

    public UserDTO editUser(UserDTO userDTO){
        User actUser = userRepository.findById(userDTO.getId()).orElse(null);
        actUser.setName(userDTO.getName());
        actUser.setRole(userDTO.getRole());
        actUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userMapper.toDto(userRepository.save(actUser));
    }

    public void deleteUser(UUID id){
        String deleteUrl = restClient.getApiUrl() + "/users" + "?userId=" + id;
        System.out.println(deleteUrl);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(deleteUrl);
        userRepository.deleteById(id);
    }
}
