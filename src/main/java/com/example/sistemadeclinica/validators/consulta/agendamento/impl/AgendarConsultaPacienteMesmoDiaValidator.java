package com.example.sistemadeclinica.validators.consulta.agendamento.impl;

import com.example.sistemadeclinica.exception.ValidationException;
import com.example.sistemadeclinica.model.Consulta;
import com.example.sistemadeclinica.repository.ConsultaRepository;
import com.example.sistemadeclinica.validators.consulta.agendamento.AgendarConsultaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgendarConsultaPacienteMesmoDiaValidator implements AgendarConsultaValidator {

    private final ConsultaRepository consultaRepository;

    @Override
    public void validate(Consulta consulta) throws ValidationException {
        var primeiroHorario = consulta.getDataDe().withHour(7);
        var ultimoHorario = consulta.getDataDe().withHour(20);
        if(consultaRepository.existsByPacienteIdAndDataDeBetween(consulta.getPaciente().getId(), primeiroHorario, ultimoHorario)) {
            throw new ValidationException("Não é possivel agendar mais de uma consulta por paciente!");
        }
    }
}