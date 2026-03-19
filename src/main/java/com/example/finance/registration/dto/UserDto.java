package com.example.finance.registration.dto;

import org.jetbrains.annotations.NotNull;

public record UserDto(
        Long id,
        @NotNull
        String name,
        @NotNull
        String email,
        String password
) {

}
