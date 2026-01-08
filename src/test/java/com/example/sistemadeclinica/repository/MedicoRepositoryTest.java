package com.example.sistemadeclinica.repository;

import com.example.sistemadeclinica.dto.CriarMedicoDto;
import com.example.sistemadeclinica.dto.CriarPacienteDto;
import com.example.sistemadeclinica.dto.EnderecoDto;
import com.example.sistemadeclinica.model.Consulta;
import com.example.sistemadeclinica.model.Medico;
import com.example.sistemadeclinica.model.Paciente;
import com.example.sistemadeclinica.model.enuns.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    public void escolherMedicoAleatorioNaDataCase01() {
        LocalDateTime proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 30);
        var proximaSegundaAs10AteAs11 = proximaSegundaAs10.plusHours(1);
        Medico medico = cadastrarMedico();
        Paciente paciente = cadastrarPaciente();
        agendarConsulta(medico, paciente, proximaSegundaAs10, proximaSegundaAs10AteAs11);

        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10, proximaSegundaAs10AteAs11);

        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        var proximaSegundaAs10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 30);
        var medico = cadastrarMedico();

        var medicoLivre = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10, proximaSegundaAs10.plusHours(1));

        assertThat(medicoLivre).isEqualTo(medico);
    }

    private void agendarConsulta(Medico medico, Paciente paciente, LocalDateTime dataDe, LocalDateTime dataAte) {
        var consulta = Consulta.agendar(paciente, medico, dataDe, dataAte);
        em.persist(consulta);
    }

    private Medico cadastrarMedico() {
        var medico = Medico.criar(dadosMedico());
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente() {
        var paciente = Paciente.criar(dadosPaciente());
        em.persist(paciente);
        return paciente;
    }

    private CriarMedicoDto dadosMedico() {
        return new CriarMedicoDto(
                "Medico",
                "medico@voll.med",
                "123456",
                "61999999999",
                Especialidade.CARDIOLOGIA,
                dadosEndereco()
        );
    }

    private CriarPacienteDto dadosPaciente() {
        return new CriarPacienteDto(
                "Paciente",
                "00000000000",
                "paciente@email.com",
                "61999999999",
                dadosEndereco()
        );
    }

    private EnderecoDto dadosEndereco() {
        return new EnderecoDto(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}