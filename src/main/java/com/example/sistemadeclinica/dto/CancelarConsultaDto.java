package com.example.sistemadeclinica.dto;

import com.example.sistemadeclinica.model.enuns.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record CancelarConsultaDto(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivoCancelamento) {
}