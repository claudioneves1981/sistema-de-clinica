package com.example.sistemadeclinica.dto;

import com.example.sistemadeclinica.model.enuns.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendarConsultaDto(
        Long idMedico,

        @NotNull
        Long idPaciente,

        @NotNull
        @Schema(type = "string", pattern = "yyyy-MM-dd'T'HH:mm:ss", example = "2026-01-09T01:06:39")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime dataDe,

        @NotNull
        @Schema(type = "string", pattern = "yyyy-MM-dd'T'HH:mm:ss", example = "2026-01-09T01:06:39")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
        LocalDateTime dataAte,

        Especialidade especialidade
) {
}
