package com.example.finance.controller;

import com.example.finance.dto.UserDto;
import com.example.finance.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserDto> getAllUsers() {
        log.info("Called method findAllUsers");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(
            @PathVariable("id") Long id
            ) {
        log.info("Called method getUserById");
        return userService.getUserById(id);
    }

    @PostMapping
    public UserDto createUser(
            @RequestBody UserDto createdUser
    ) {
        log.info("Called method createUser");
        return userService.createdUser(createdUser);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserDto updatedUser
    ){
        log.info("Called method updateUser");
        return userService.updateUser(id,updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(
            @PathVariable("id") Long id
    ){
        log.info("Called method deleteUser");
        userService.deleteUser(id);
    }

}
