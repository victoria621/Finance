package com.example.finance.registration.dto.responce;

public record JwtResponse(
        String token,
        String type,
        Long expiresIn,
        String username
) {
}
