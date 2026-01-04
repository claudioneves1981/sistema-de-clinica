package com.example.sistemadeclinica.service.impl;

import com.example.sistemadeclinica.dto.DetalhesConsultaDto;
import com.example.sistemadeclinica.model.Consulta;
import com.example.sistemadeclinica.repository.ConsultaRepository;
import com.example.sistemadeclinica.service.DetalhesConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class DetalhesConsultaServiceImpl implements DetalhesConsultaService {

    private final ConsultaRepository consultaRepository;


    @Override
    @Transactional
    public DetalhesConsultaDto detalhes(Long idConsulta){

        return new DetalhesConsultaDto(Objects.requireNonNull(obterConsulta(idConsulta)));

    }

    private Consulta obterConsulta(Long idConsulta) {

        if (Objects.nonNull(idConsulta)) {
            return consultaRepository.getReferenceById(idConsulta);
        }

        return null;
    }
}
