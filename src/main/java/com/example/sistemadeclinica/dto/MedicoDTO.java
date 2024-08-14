package com.example.sistemadeclinica.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicoDTO {

    private Long id;
    private String nome;
    private String especialidade;

}
