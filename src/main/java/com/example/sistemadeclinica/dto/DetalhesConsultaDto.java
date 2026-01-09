package com.example.sistemadeclinica.dto;

import com.example.sistemadeclinica.model.Consulta;
import com.example.sistemadeclinica.model.enuns.MotivoCancelamento;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record DetalhesConsultaDto(
        Long id,
        Long idMedico,
        Long idPaciente,

        @Schema(type = "string", pattern = "yyyy-MM-dd'T'HH:mm:ss", example = "2026-01-09T01:06:39")
        LocalDateTime dataDe,

        @Schema(type = "string", pattern = "yyyy-MM-dd'T'HH:mm:ss", example = "2026-01-09T01:06:39")
        LocalDateTime dataAte,

        MotivoCancelamento motivoCancelamento
) {
    public DetalhesConsultaDto(Consulta consulta) {
        this(consulta.getId(),
                consulta.getMedico().getId(),
                consulta.getPaciente().getId(),
                consulta.getDataDe(),
                consulta.getDataAte(),
                consulta.getMotivoCancelamento());
    }
}