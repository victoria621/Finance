package com.example.finance.service;

import com.example.finance.dto.UserDto;
import com.example.finance.dto.UserMapper;
import com.example.finance.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

     public List<UserDto> getAllUsers() {
         return userRepository.findAll()
                 .stream()
                 .map(userMapper::toDto)
                 .toList();
     }
}
