package com.example.sistemadeclinica.adapter;

import com.example.sistemadeclinica.dto.AgendaDTO;
import com.example.sistemadeclinica.dto.PacienteDTO;
import com.example.sistemadeclinica.model.Agenda;
import com.example.sistemadeclinica.model.Medico;
import com.example.sistemadeclinica.model.Paciente;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AgendaModelAdapter {

    private Agenda agenda;

    public AgendaModelAdapter(AgendaDTO agendaDTO) {
        agenda = toModel(agendaDTO);
    }
    private Agenda toModel(AgendaDTO agendaDTO){

        List<PacienteDTO> pacienteDTOList = agendaDTO.getPacientes();
        List<Paciente> pacienteList = new ArrayList<>();
        for (PacienteDTO p : pacienteDTOList){
            Paciente paciente = Paciente.builder()
                    .id(p.getId())
                    .cpf(p.getCpf())
                    .nome(p.getNome())
                    .idade(p.getIdade())
                    .telefone(p.getTelefone())
                    .build();
            pacienteList.add(paciente);
        }

        return Agenda.builder()
                .id(agendaDTO.getId())
                .data(agendaDTO.getData())
                .medico(Medico.builder()
                        .id(agendaDTO.getMedico().getId())
                        .especialidade(agendaDTO.getMedico().getEspecialidade())
                        .nome(agendaDTO.getMedico().getNome())
                        .build())
                .pacientes(pacienteList)
                .build();

    }
}
