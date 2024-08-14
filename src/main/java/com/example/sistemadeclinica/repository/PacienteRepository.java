package com.example.sistemadeclinica.repository;

import com.example.sistemadeclinica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    @Query(value = "SELECT p FROM Paciente p where p.cpf =:cpf")
    Paciente findByCpf(@Param("cpf") String cpf);
}
