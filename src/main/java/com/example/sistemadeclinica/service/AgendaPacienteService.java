package com.example.sistemadeclinica.service;

import com.example.sistemadeclinica.dto.AgendaDTO;
import com.example.sistemadeclinica.dto.AgendaViewDTO;
import com.example.sistemadeclinica.dto.PacienteDTO;
import com.example.sistemadeclinica.dto.PacienteViewDTO;

import java.util.List;

public interface AgendaPacienteService {

    void adicionar(AgendaDTO agenda);
    void remover(Long id, String cpf) throws Exception;
    PacienteViewDTO pesquisar(Long id, String cpf) throws Exception;
    AgendaViewDTO listarTodosPacientes(Long id) throws Exception;
}
