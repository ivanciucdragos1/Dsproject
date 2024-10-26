package com.example.dsusermanagement.services;

import com.example.dsusermanagement.dtos.LoginRequest;
import com.example.dsusermanagement.dtos.RegisterRequest;
import com.example.dsusermanagement.entities.User;
import com.example.dsusermanagement.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User register(RegisterRequest registerRequest){
        User user = User.builder()
                .name(registerRequest.getName())
                .role(registerRequest.getRole())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        return userRepository.save(user);
    }

    public User login(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getName(),
                        loginRequest.getPassword()
                )
        );
        return userRepository.findByName(loginRequest.getName()).orElseThrow();
    }

}
