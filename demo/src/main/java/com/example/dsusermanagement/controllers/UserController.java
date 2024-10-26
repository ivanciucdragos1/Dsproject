package com.example.dsusermanagement.controllers;

import com.example.dsusermanagement.dtos.UserDTO;
import com.example.dsusermanagement.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.example.dsusermanagement.UrlMappings.USERS;

@RestController
@CrossOrigin
@RequestMapping(USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> getUsers(){
        return userService.getUsers();
    }

    @PostMapping("/create")
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    @PatchMapping("/edit")
    public UserDTO editUser(@RequestBody UserDTO userDTO){
        return userService.editUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id){
        System.out.println("here " + id);
        userService.deleteUser(id);
    }

}
