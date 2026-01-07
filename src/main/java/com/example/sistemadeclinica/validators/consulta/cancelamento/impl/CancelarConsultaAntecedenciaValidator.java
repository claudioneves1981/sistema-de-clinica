package com.example.sistemadeclinica.validators.consulta.cancelamento.impl;

import com.example.sistemadeclinica.exception.ValidationException;
import com.example.sistemadeclinica.model.Consulta;
import com.example.sistemadeclinica.repository.ConsultaRepository;
import com.example.sistemadeclinica.validators.consulta.cancelamento.CancelarConsultaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CancelarConsultaAntecedenciaValidator implements CancelarConsultaValidator {

    private final ConsultaRepository consultaRepository;

    @Override
    public void validate(Consulta consulta) throws ValidationException {
        var agora = LocalDateTime.now();
        var data = consulta.getDataDe();
        var diferenca = Duration.between(agora, data).toHours();
        if (diferenca > 0 && diferenca < 24) {
            throw new ValidationException("A consulta só pode ser cancelada com antecedência minima de 24 horas!");
        }
    }
}