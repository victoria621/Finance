package com.example.finance.registration.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "Email should be necessarily")
        @Email
        String email,
        @NotBlank(message = "Password should be necessarily")
        @Size(min=8,message = "The password must be at least 6 characters long")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$",
                message = "Пароль должен содержать хотя бы одну букву и одну цифру")
        String password
) {

}
