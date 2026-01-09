package com.example.sistemadeclinica.validators.consulta.agendamento.impl;

import com.example.sistemadeclinica.exception.ValidationException;
import com.example.sistemadeclinica.model.Consulta;
import com.example.sistemadeclinica.validators.consulta.agendamento.AgendarConsultaValidator;
import com.example.sistemadeclinica.validators.consulta.agendamento.dto.AgendarConsultaValidatorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class AgendarConsultaHorarioFuncionamentoValidator implements AgendarConsultaValidator {


    private final AgendarConsultaValidatorDto agendarConsultaValidatorDto;

    @Autowired
    public AgendarConsultaHorarioFuncionamentoValidator(AgendarConsultaValidatorDto agendarConsultaValidatorDto) {
        this.agendarConsultaValidatorDto = agendarConsultaValidatorDto;
    }

    @Override
    public void validate(Consulta consulta) throws ValidationException {
        var data = consulta.getDataDe();
        var domingo = data.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesHorarioFuncionamento = data.getHour() < agendarConsultaValidatorDto.getHoraAbertura();
        var depoisHorarioFuncionamento = data.getHour() > agendarConsultaValidatorDto.getHoraEncerramento();
        var depoisMinutosHorarioFuncionamento = (data.getHour() == agendarConsultaValidatorDto.getHoraEncerramento() && data.getMinute() > 0);

        if (domingo || antesHorarioFuncionamento || depoisHorarioFuncionamento || depoisMinutosHorarioFuncionamento) {
            throw new ValidationException("A consulta deve ser agendada dentro do hórario de funcionamento!");
        }
    }
}