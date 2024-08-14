package com.example.sistemadeclinica.dto;

import com.example.sistemadeclinica.model.Medico;
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
public class AgendaDTO {

    private Long id;

    private List<PacienteDTO> pacientes;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    private MedicoDTO medico;

}
