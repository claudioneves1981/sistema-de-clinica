package com.example.sistemadeclinica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@SequenceGenerator(name = "seq_medico" , sequenceName = "seq_medico", allocationSize = 1)
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_medico")
    @Column(name = "ID_MEDICO")
    private Long id;

    private String nome;
    private String especialidade;

}
