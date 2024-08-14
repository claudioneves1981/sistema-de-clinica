package com.example.sistemadeclinica.service.impl;

import com.example.sistemadeclinica.adapter.*;
import com.example.sistemadeclinica.dto.AgendaDTO;
import com.example.sistemadeclinica.dto.AgendaViewDTO;
import com.example.sistemadeclinica.dto.PacienteDTO;
import com.example.sistemadeclinica.dto.PacienteViewDTO;
import com.example.sistemadeclinica.model.Agenda;
import com.example.sistemadeclinica.model.Paciente;
import com.example.sistemadeclinica.repository.AgendaRepository;
import com.example.sistemadeclinica.repository.PacienteRepository;
import com.example.sistemadeclinica.service.AgendaPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaPacienteServiceImpl implements AgendaPacienteService {


    @Autowired
    private AgendaRepository agendaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public void adicionar(AgendaDTO agendaDTO) {

        Agenda agenda = new AgendaModelAdapter(agendaDTO).getAgenda();
        agendaRepository.save(agenda);
    }

    @Override
    public void remover(Long id, String cpf) throws Exception {

       Agenda agenda =  agendaRepository.findById(id).orElseThrow(()-> new Exception("Agenda não Encontrada"));
       Paciente paciente = pacienteRepository.findByCpf(cpf);
       agenda.getPacientes().remove(paciente);
       agendaRepository.save(agenda);

    }

    @Override
    public PacienteViewDTO pesquisar(Long id, String cpf) throws Exception {

        Agenda agenda =  agendaRepository.findById(id).orElseThrow(()-> new Exception("Agenda não Encontrada"));
        Paciente paciente = pacienteRepository.findByCpf(cpf);
        PacienteViewDTO pacienteDTO = new PacienteViewDTO();

        for(Paciente p : agenda.getPacientes()){

            if(p.equals(paciente)){

                pacienteDTO = new PacienteViewDTOAdapter(paciente).getPacienteDTO();
                break;

            }

        }

        return pacienteDTO;

    }

    @Override
    public AgendaViewDTO listarTodosPacientes(Long id) throws Exception {
        Agenda agenda = agendaRepository.findById(id).orElseThrow(() -> new Exception("Agenda não encontrada"));
        return new AgendaViewDTOAdapter(agenda).getAgendaDTO();
    }


}
