package br.com.foodwise.platform.infrastructure.rest.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest(@NotBlank String email, @NotBlank String password) {
}
