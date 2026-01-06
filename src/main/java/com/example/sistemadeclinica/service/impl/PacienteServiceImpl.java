package com.example.sistemadeclinica.service.impl;

import com.example.sistemadeclinica.dto.*;
import com.example.sistemadeclinica.model.Medico;
import com.example.sistemadeclinica.model.Paciente;
import com.example.sistemadeclinica.repository.PacienteRepository;
import com.example.sistemadeclinica.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    @Override
    @Transactional
    public DetalhesPacienteDto criar(CriarPacienteDto criarPacienteDto){

        var paciente = pacienteRepository.save(Paciente.criar(criarPacienteDto));
        return new DetalhesPacienteDto(paciente);

    }

    @Override
    @Transactional
    public PagedModel<ItemListaPacienteDto> getList(Pageable pageable){

        return new PagedModel<>(pacienteRepository.findAllByAtivoTrue(pageable).map(ItemListaPacienteDto::new));

    }

    @Override
    @Transactional
    public DetalhesPacienteDto getById(Long id){

        var paciente = pacienteRepository.getReferenceById(id);
        return new DetalhesPacienteDto(paciente);

    }

    @Override
    @Transactional
    public DetalhesPacienteDto update(AtualizarPacienteDto pacienteUpdateDto){

        var paciente = pacienteRepository.getReferenceById(pacienteUpdateDto.id());
        paciente.atualizar(pacienteUpdateDto);
        return new DetalhesPacienteDto(paciente);

    }

    @Override
    @Transactional
    public void delete(Long id){
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.inativar();
    }
}
