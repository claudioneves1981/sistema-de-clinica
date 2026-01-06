package com.example.sistemadeclinica.service.impl;

import com.example.sistemadeclinica.dto.DetalhesConsultaDto;
import com.example.sistemadeclinica.dto.DetalhesPacienteDto;
import com.example.sistemadeclinica.model.Consulta;
import com.example.sistemadeclinica.repository.ConsultaRepository;
import com.example.sistemadeclinica.service.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {

    private final ConsultaRepository consultaRepository;



    @Override
    @Transactional
    public DetalhesConsultaDto getById(Long id){

        var consulta = consultaRepository.getReferenceById(id);
        return new DetalhesConsultaDto(consulta);

    }
}
