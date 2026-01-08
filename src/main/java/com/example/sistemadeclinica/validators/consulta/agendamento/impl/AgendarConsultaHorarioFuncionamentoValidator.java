package com.example.sistemadeclinica.validators.consulta.agendamento.impl;

import com.example.sistemadeclinica.exception.ValidationException;
import com.example.sistemadeclinica.model.Consulta;
import com.example.sistemadeclinica.validators.consulta.agendamento.AgendarConsultaValidator;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class AgendarConsultaHorarioFuncionamentoValidator implements AgendarConsultaValidator {

    @Override
    public void validate(Consulta consulta) throws ValidationException {
        var data = consulta.getDataDe();
        var domingo = data.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesHorarioFuncionamento = data.getHour() < 7;
        var depoisHorarioFuncionamento = data.getHour() > 20;
        var depoisMinutosHorarioFuncionamento = (data.getHour() == 20 && data.getMinute() > 0);

        if (domingo || antesHorarioFuncionamento || depoisHorarioFuncionamento || depoisMinutosHorarioFuncionamento) {
            throw new ValidationException("A consulta deve ser agendada dentro do hórario de funcionamento!");
        }
    }
}