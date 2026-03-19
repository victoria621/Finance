package com.example.finance.registration.controller;

import com.example.finance.registration.dto.responce.JwtResponse;
import com.example.finance.registration.dto.request.LoginRequest;
import com.example.finance.registration.dto.request.RegisterRequest;
import com.example.finance.registration.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {

    private final RegistrationService registrationService;
    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public JwtResponse registerUser(
            @RequestBody RegisterRequest request
    ) {
        log.info("Called method registerUser");
        return registrationService.registerUser(request);
    }

    @PostMapping("/login")
    public JwtResponse loginUser(
            @RequestBody LoginRequest request
    ){
        log.info("Called method loginUser");
        return registrationService.login(request);
    }

    @PostMapping("/logout")
    public void logout(){
        log.info("Called method logout");
    }

}
