package com.example.sistemadeclinica.validators.consulta.agendamento.impl;

import com.example.sistemadeclinica.exception.ValidationException;
import com.example.sistemadeclinica.model.Consulta;
import com.example.sistemadeclinica.repository.MedicoRepository;
import com.example.sistemadeclinica.validators.consulta.agendamento.AgendarConsultaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AgendarConsultaMedicoInativoValidator implements AgendarConsultaValidator {

    private final MedicoRepository medicoRepository;

    @Override
    public void validate(Consulta consulta) throws ValidationException {
        if (Objects.isNull(consulta.getMedico())) {
            return;
        }
        if (!medicoRepository.findAtivoById(consulta.getMedico().getId())) {
            throw new ValidationException("Não é possivel agendar consultas com um médico inátivo!");
        }
    }
}