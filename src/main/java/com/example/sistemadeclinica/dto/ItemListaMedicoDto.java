package com.example.sistemadeclinica.dto;

import com.example.sistemadeclinica.model.Medico;
import com.example.sistemadeclinica.model.enuns.Especialidade;

public record ItemListaMedicoDto(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public ItemListaMedicoDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}