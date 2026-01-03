package com.example.sistemadeclinica.dto;

import com.example.sistemadeclinica.model.enuns.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendarConsultaDto(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Future
        LocalDateTime dataDe,

        @NotNull
        @Future
        LocalDateTime dataAte,

        Especialidade especialidade
) {
}
