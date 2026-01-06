package com.example.sistemadeclinica.dto;

import com.example.sistemadeclinica.model.Paciente;

public record ItemListaPacienteDto(Long id, String nome, String email, String cpf) {

    public ItemListaPacienteDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
