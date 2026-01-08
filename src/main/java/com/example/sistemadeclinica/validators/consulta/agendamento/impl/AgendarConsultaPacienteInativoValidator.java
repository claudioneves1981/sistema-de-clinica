package com.example.sistemadeclinica.validators.consulta.agendamento.impl;

import com.example.sistemadeclinica.exception.ValidationException;
import com.example.sistemadeclinica.model.Consulta;
import com.example.sistemadeclinica.repository.PacienteRepository;
import com.example.sistemadeclinica.validators.consulta.agendamento.AgendarConsultaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgendarConsultaPacienteInativoValidator implements AgendarConsultaValidator {

    private final PacienteRepository pacienteRepository;

    @Override
    public void validate(Consulta consulta) throws ValidationException {
        if(!pacienteRepository.findAtivoById(consulta.getPaciente().getId())) {
            throw new ValidationException("Não é possivel agendar consultas com um paciente inativo!");
        }
    }
}