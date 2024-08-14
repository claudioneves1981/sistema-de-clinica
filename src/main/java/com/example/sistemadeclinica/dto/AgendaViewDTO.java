package com.example.sistemadeclinica.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgendaViewDTO {


    private Long id;

    private List<PacienteViewDTO> pacientes;

    private LocalDate data;

    private String medico;

    private String especialidade;
}
