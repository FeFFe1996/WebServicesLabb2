package com.example.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(
        @NotBlank
        String username,
        @NotBlank
        @Size(min = 8)
        String password
) {
}
