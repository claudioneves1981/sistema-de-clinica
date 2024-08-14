package com.example.sistemadeclinica.adapter;

import com.example.sistemadeclinica.dto.AgendaDTO;
import com.example.sistemadeclinica.dto.MedicoDTO;
import com.example.sistemadeclinica.dto.PacienteDTO;
import com.example.sistemadeclinica.model.Agenda;
import com.example.sistemadeclinica.model.Paciente;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AgendaDTOAdapter {

    private AgendaDTO agendaDTO;

    public AgendaDTOAdapter(Agenda agenda) {
        agendaDTO = toDTO(agenda);
    }
    private AgendaDTO toDTO(Agenda agenda){

        List<Paciente> pacienteList = agenda.getPacientes();
        List<PacienteDTO> pacienteDTOList = new ArrayList<>();
        for (Paciente p : pacienteList){
            PacienteDTO pacienteDTO = PacienteDTO.builder()
                    .nome(p.getNome())
                    .idade(p.getIdade())
                    .telefone(p.getTelefone())
                    .build();
            pacienteDTOList.add(pacienteDTO);
        }

        return AgendaDTO.builder()
                .id(agenda.getId())
                .data(agenda.getData())
                .medico(MedicoDTO.builder()
                        .nome(agenda.getMedico().getNome())
                        .especialidade(agenda.getMedico().getEspecialidade())
                        .id(agenda.getMedico().getId())
                        .build())
                .pacientes(pacienteDTOList)
                .build();

    }
}
