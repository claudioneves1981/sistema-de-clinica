package com.example.sistemadeclinica.adapter;

import com.example.sistemadeclinica.dto.AgendaDTO;
import com.example.sistemadeclinica.dto.PacienteDTO;
import com.example.sistemadeclinica.model.Agenda;
import com.example.sistemadeclinica.model.Paciente;
import lombok.Data;

import java.util.List;

@Data
public class PacienteDTOAdapter {

    private PacienteDTO pacienteDTO;
    private List<PacienteDTO> pacienteDTOList;

    public PacienteDTOAdapter(Paciente paciente) {
        pacienteDTO = toDTO(paciente);
    }

    public PacienteDTOAdapter(List<Paciente> pacientes) {
        pacienteDTOList = toDTOList(pacientes);
    }

    private List<PacienteDTO> toDTOList(List<Paciente> pacientes) {

        for (Paciente p : pacientes){
            pacienteDTOList.add(toDTO(p));
        }

        return pacienteDTOList;
    }

    private PacienteDTO toDTO(Paciente paciente) {

        return PacienteDTO.builder()
                .id(paciente.getId())
                .cpf(paciente.getCpf())
                .nome(paciente.getNome())
                .idade(paciente.getIdade())
                .telefone(paciente.getTelefone())
                .build();
    }


}
