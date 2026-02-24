package com.example.finance.dto;

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
