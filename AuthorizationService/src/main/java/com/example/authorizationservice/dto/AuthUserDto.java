package com.example.authorizationservice.dto;

import java.util.List;

public record AuthUserDto(
        String username,
        String password,
        List<String> roles
) {
}
