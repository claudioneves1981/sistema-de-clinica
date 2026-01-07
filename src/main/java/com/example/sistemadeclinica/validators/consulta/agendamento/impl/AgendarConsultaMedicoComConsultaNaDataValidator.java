package com.example.sistemadeclinica.validators.consulta.agendamento.impl;

import com.example.sistemadeclinica.exception.ValidationException;
import com.example.sistemadeclinica.model.Consulta;
import com.example.sistemadeclinica.repository.ConsultaRepository;
import com.example.sistemadeclinica.validators.consulta.agendamento.AgendarConsultaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AgendarConsultaMedicoComConsultaNaDataValidator implements AgendarConsultaValidator {

    private final ConsultaRepository consultaRepository;

    @Override
    public void validate(Consulta consulta) throws ValidationException {
        if(consultaRepository.existsByMedicoIdAndDataDeLessThanEqualAndDataAteGreaterThanEqual(consulta.getPaciente().getId(),
                consulta.getDataDe(),
                consulta.getDataAte()
        )) {
            throw new ValidationException("Não é possivel agendar uma consulta para um médico com uma consulta nesse mesmo hórario!");
        }
    }
}