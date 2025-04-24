package com.example.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContatoDTO(
        @NotBlank String nome,
        @NotBlank String contato,
        @NotNull  Long profissionalId
) {}