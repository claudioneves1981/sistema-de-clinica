package com.example.sistemadeclinica.adapter;

import com.example.sistemadeclinica.dto.PacienteDTO;
import com.example.sistemadeclinica.dto.PacienteViewDTO;
import com.example.sistemadeclinica.model.Paciente;
import lombok.Data;

@Data
public class PacienteViewDTOAdapter {

    private PacienteViewDTO pacienteDTO;

    public PacienteViewDTOAdapter(Paciente paciente) {
        pacienteDTO = toDTO(paciente);
    }

    private PacienteViewDTO toDTO(Paciente paciente) {

        return PacienteViewDTO.builder()
                .nome(paciente.getNome())
                .idade(paciente.getIdade())
                .telefone(paciente.getTelefone())
                .build();
    }
}
