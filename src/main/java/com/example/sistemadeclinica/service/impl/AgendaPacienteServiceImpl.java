package com.example.sistemadeclinica.service.impl;


import com.example.sistemadeclinica.dto.AgendarConsultaDto;
import com.example.sistemadeclinica.dto.DetalhesConsultaDto;
import com.example.sistemadeclinica.exception.EspecialidadeNaoEncontradaException;
import com.example.sistemadeclinica.exception.PacienteNaoEncontradoException;
import com.example.sistemadeclinica.model.Consulta;
import com.example.sistemadeclinica.model.enuns.Especialidade;
import com.example.sistemadeclinica.model.Medico;
import com.example.sistemadeclinica.repository.ConsultaRepository;
import com.example.sistemadeclinica.repository.MedicoRepository;
import com.example.sistemadeclinica.repository.PacienteRepository;
import com.example.sistemadeclinica.service.AgendaPacienteService;
import com.example.sistemadeclinica.validators.AgendarConsultaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AgendaPacienteServiceImpl implements AgendaPacienteService {

    private final ConsultaRepository consultaRepository;

    private final PacienteRepository pacienteRepository;

    private final MedicoRepository medicoRepository;

    private final List<AgendarConsultaValidator> consultaAgendarValidators;


    @Override
    @Transactional
    public DetalhesConsultaDto agendar(AgendarConsultaDto consultaDto) {
        var paciente = pacienteRepository.findById(consultaDto.idPaciente()).orElseThrow(() ->
                new PacienteNaoEncontradoException("Id do paciente informado não existe!"));

        var medico = obterMedico(consultaDto.idMedico(), consultaDto.especialidade(), consultaDto.dataDe(), consultaDto.dataAte());
        var consulta = Consulta.agendar(paciente, medico, consultaDto.dataDe(), consultaDto.dataAte());

        consultaAgendarValidators.forEach(v -> v.validate(consulta));

        return new DetalhesConsultaDto(consultaRepository.save(consulta));
    }



    private Medico obterMedico(Long idMedico, Especialidade especialidade, LocalDateTime dataDe, LocalDateTime dataAte) {
        if (Objects.nonNull(idMedico)) {
            return medicoRepository.getReferenceById(idMedico);
        }
        if (Objects.isNull(especialidade)) {
            throw new EspecialidadeNaoEncontradaException("Especialidade é obrigatória quando médico não for escolhido!");
        }
        return medicoRepository.escolherMedicoAleatorioLivreNaData(especialidade, dataDe, dataAte);
    }


}
