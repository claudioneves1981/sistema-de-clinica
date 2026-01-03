package com.example.sistemadeclinica.repository;

import com.example.sistemadeclinica.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByPacienteIdAndDataDeBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByMedicoIdAndDataDeLessThanEqualAndDataAteGreaterThanEqual(Long medicoId, LocalDateTime dataDe, LocalDateTime dataAte);
}
