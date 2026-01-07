package com.example.sistemadeclinica.validators.consulta.agendamento.impl;

import com.example.sistemadeclinica.exception.ValidationException;
import com.example.sistemadeclinica.model.Consulta;
import com.example.sistemadeclinica.validators.consulta.agendamento.AgendarConsultaValidator;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AgendarConsultaAntecedenciaValidator implements AgendarConsultaValidator {

    @Override
    public void validate(Consulta consulta) throws ValidationException {
        var agora = LocalDateTime.now();
        var data = consulta.getDataDe();
        var diferenca = Duration.between(agora, data).toMinutes();
        if (diferenca > 0 && diferenca < 30) {
            throw new ValidationException("A consulta deve ser agendada com antecedência minima de 30 minutos!");
        }
    }
}