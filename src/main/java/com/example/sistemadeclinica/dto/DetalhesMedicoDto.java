package com.example.sistemadeclinica.dto;

import com.example.sistemadeclinica.model.Medico;
import com.example.sistemadeclinica.model.enuns.Especialidade;

public record DetalhesMedicoDto(Long id, String name, String email, String crm, String telefone,
                                Especialidade especialidade, EnderecoDto endereco) {

    public DetalhesMedicoDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(),
                medico.getEspecialidade(), new EnderecoDto(medico.getEndereco()));
    }
}