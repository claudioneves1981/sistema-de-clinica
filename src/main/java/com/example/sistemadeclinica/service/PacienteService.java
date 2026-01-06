package com.example.sistemadeclinica.service;

import com.example.sistemadeclinica.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

public interface PacienteService {

    DetalhesPacienteDto criar(CriarPacienteDto criarPacienteDto);
    PagedModel<ItemListaPacienteDto> getList(Pageable pageable);
    DetalhesPacienteDto getById(Long id);
    DetalhesPacienteDto update(AtualizarPacienteDto pacienteUpdateDto);
    void delete(Long id);
}
