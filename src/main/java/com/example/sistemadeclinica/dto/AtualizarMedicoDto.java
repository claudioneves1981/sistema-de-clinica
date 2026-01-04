package com.example.sistemadeclinica.dto;

import jakarta.validation.constraints.NotNull;

public record AtualizarMedicoDto(
        @NotNull
        Long id,
        String nome,
        String telefone,
        EnderecoDto endereco) {
}