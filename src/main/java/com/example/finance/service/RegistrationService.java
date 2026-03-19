package com.example.finance.service;

import com.example.finance.dto.request.LoginRequest;
import com.example.finance.dto.request.RegisterRequest;
import com.example.finance.dto.responce.JwtResponse;
import com.example.finance.entity.UserEntity;
import com.example.finance.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
public class RegistrationService {
    private final UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public JwtResponse registerUser(@RequestBody RegisterRequest request) {
       if(userRepository.existsByUsername(request.username() )){
           throw  new IllegalArgumentException("Username already exists");
       }

       var userRegister = new UserEntity();
       userRegister.setUsername(request.username());
       userRegister.setEmail(request.email());
       userRegister.setPassword(request.password());


       userRepository.save(userRegister);

       String token = UUID.randomUUID().toString();

       return new JwtResponse(token, "Bearer", 3600L,
               userRegister.getUsername());
    }

    public JwtResponse login(@RequestBody LoginRequest request) {
        return null;
    }
}
