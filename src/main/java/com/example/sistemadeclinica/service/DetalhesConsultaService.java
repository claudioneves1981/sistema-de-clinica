package com.example.sistemadeclinica.service;

import com.example.sistemadeclinica.dto.DetalhesConsultaDto;
import com.example.sistemadeclinica.model.Consulta;
import com.example.sistemadeclinica.model.Medico;
import com.example.sistemadeclinica.model.enuns.Especialidade;

import java.time.LocalDateTime;
import java.util.Objects;

public interface DetalhesConsultaService {

    DetalhesConsultaDto detalhes(Long idConsulta);
}
