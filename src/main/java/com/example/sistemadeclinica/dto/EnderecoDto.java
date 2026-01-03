package com.example.sistemadeclinica.dto;

import com.example.sistemadeclinica.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDto(@NotBlank String logradouro,

                          @NotBlank String bairro,

                          @NotBlank @Pattern(regexp = "\\d{8}") String cep,

                          @NotBlank String cidade,

                          @NotBlank String uf,

                          String complemento, String numero) {

    public EnderecoDto(Endereco endereco) {
        this(endereco.getLogradouro(), endereco.getBairro(), endereco.getCep(), endereco.getCidade(), endereco.getUf(), endereco.getComplemento(), endereco.getNumero());
    }
}