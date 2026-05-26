package com.example.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDto(
        @NotBlank
        String userName,
        @NotBlank
        @Size(min = 8)
        String passWord
) {
}
