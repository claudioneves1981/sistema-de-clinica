package com.example.sistemadeclinica.service;

import com.example.sistemadeclinica.dto.CancelarConsultaDto;
import com.example.sistemadeclinica.dto.DetalhesConsultaDto;

public interface CancelarConsultaService {

    DetalhesConsultaDto cancelar(CancelarConsultaDto cancelarConsultaDto);
}


