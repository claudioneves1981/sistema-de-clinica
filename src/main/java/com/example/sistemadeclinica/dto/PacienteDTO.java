package com.example.sistemadeclinica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PacienteDTO {

    private Long id;
    private String cpf;
    private String nome;
    private int idade;
    private String telefone;


}
