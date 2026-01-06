package com.example.sistemadeclinica.service.impl;

import com.example.sistemadeclinica.dto.CancelarConsultaDto;
import com.example.sistemadeclinica.dto.DetalhesConsultaDto;
import com.example.sistemadeclinica.exception.EspecialidadeNaoEncontradaException;
import com.example.sistemadeclinica.repository.ConsultaRepository;
import com.example.sistemadeclinica.service.CancelarConsultaService;
import com.example.sistemadeclinica.validators.consulta.cancelamento.CancelarConsultaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CancelarConsultaServiceImpl implements CancelarConsultaService {

    private final ConsultaRepository consultaRepository;
    private final List<CancelarConsultaValidator> consultaCancelarValidators;

    @Transactional
    @Override
    public DetalhesConsultaDto cancelar(CancelarConsultaDto cancelarConsultaDto) {
        var consulta = consultaRepository.findById(cancelarConsultaDto.idConsulta()).orElseThrow(() -> new EspecialidadeNaoEncontradaException("Consulta não encontrada!"));
        consultaCancelarValidators.forEach(v -> v.validate(consulta));
        consulta.cancelar(cancelarConsultaDto.motivoCancelamento());
        return new DetalhesConsultaDto(consulta);
    }
}
