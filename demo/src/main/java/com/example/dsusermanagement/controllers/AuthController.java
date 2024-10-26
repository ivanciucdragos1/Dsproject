package com.example.dsusermanagement.controllers;

import com.example.dsusermanagement.dtos.LoginRequest;
import com.example.dsusermanagement.dtos.RegisterRequest;
import com.example.dsusermanagement.entities.User;
import com.example.dsusermanagement.security.JwtService;
import com.example.dsusermanagement.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.dsusermanagement.UrlMappings.AUTH;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    private final AuthService authenticationService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        User authUser = authenticationService.login(loginRequest);
        String jwtToken = jwtService.generateToken(authUser);
        return jwtToken;
    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest registerRequest){
        User user = authenticationService.register(registerRequest);
        return user;
    }

}
