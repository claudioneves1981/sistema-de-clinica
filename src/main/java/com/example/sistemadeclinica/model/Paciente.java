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
@SequenceGenerator(name = "seq_paciente" , sequenceName = "seq_paciente", allocationSize = 1)
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_paciente")
    @Column(name = "ID_PACIENTE")
    private Long id;

    private String nome;
    private String cpf;
    private int idade;
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "ID_AGENDA")
    private Agenda agenda;

}
