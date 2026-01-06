package com.example.sistemadeclinica.service;

import com.example.sistemadeclinica.dto.DetalhesConsultaDto;

public interface ConsultaService {

    DetalhesConsultaDto getById(Long idConsulta);
}
