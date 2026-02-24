package com.example.finance.dto.responce;

public record JwtResponse(
        String token,
        String type,
        Long expiresIn,
        String username
) {
}
