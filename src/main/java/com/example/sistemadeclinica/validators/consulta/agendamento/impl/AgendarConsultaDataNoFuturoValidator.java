package com.example.sistemadeclinica.validators.consulta.agendamento.impl;

import com.example.sistemadeclinica.exception.ValidationException;
import com.example.sistemadeclinica.model.Consulta;
import com.example.sistemadeclinica.validators.consulta.agendamento.AgendarConsultaValidator;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgendarConsultaDataNoFuturoValidator implements AgendarConsultaValidator {

    @Override
    public void validate(Consulta consulta) throws ValidationException {
        var hoje = LocalDateTime.now();
        var data = consulta.getDataDe();
        if(data.isBefore(hoje)) {
            throw new ValidationException("Não é possivel agendar para uma data no passado!");
        }
    }
}