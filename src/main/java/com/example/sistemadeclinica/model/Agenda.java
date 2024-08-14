package com.example.sistemadeclinica.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@SequenceGenerator(name = "seq_agenda" , sequenceName = "seq_agenda", allocationSize = 1)
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_agenda")
    @Column(name = "ID_AGENDA")
    private Long id;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="TB_PACIENTE_AGENDA",
            joinColumns = @JoinColumn(name = "ID_AGENDA", referencedColumnName = "ID_AGENDA"),
            inverseJoinColumns = @JoinColumn(name = "ID_PACIENTE",referencedColumnName = "ID_PACIENTE")
    )
    private List<Paciente> pacientes;


    private LocalDate data;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinTable(
            name="TB_MEDICO_AGENDA",
            joinColumns = @JoinColumn(name = "ID_AGENDA", referencedColumnName = "ID_AGENDA"),
            inverseJoinColumns = @JoinColumn(name = "ID_MEDICO",referencedColumnName = "ID_MEDICO")
    )
    private Medico medico;



}
