package com.example.sistemadeclinica.adapter;

import com.example.sistemadeclinica.dto.*;
import com.example.sistemadeclinica.model.Agenda;
import com.example.sistemadeclinica.model.Paciente;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

    @Data
    public class AgendaViewDTOAdapter {

        private AgendaViewDTO agendaDTO;

        public AgendaViewDTOAdapter(Agenda agenda) {
            agendaDTO = toDTO(agenda);
        }
        private AgendaViewDTO toDTO(Agenda agenda){

            List<Paciente> pacienteList = agenda.getPacientes();
            List<PacienteViewDTO> pacienteDTOList = new ArrayList<>();
            for (Paciente p : pacienteList){
                PacienteViewDTO pacienteDTO = PacienteViewDTO.builder()
                        .nome(p.getNome())
                        .idade(p.getIdade())
                        .telefone(p.getTelefone())
                        .build();
                pacienteDTOList.add(pacienteDTO);
            }

            return AgendaViewDTO.builder()
                    .id(agenda.getId())
                    .data(agenda.getData())
                    .medico(agenda.getMedico().getNome())
                    .especialidade(agenda.getMedico().getEspecialidade())
                    .pacientes(pacienteDTOList)
                    .build();
        }
}
