package com.example.sistemadeclinica.controller;

import com.example.sistemadeclinica.dto.AgendarConsultaDto;
import com.example.sistemadeclinica.dto.DetalhesConsultaDto;
import com.example.sistemadeclinica.model.enuns.Especialidade;
import com.example.sistemadeclinica.service.AgendarConsultaService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AgendarConsultaService agendarConsultaUseCase;

    @Autowired
    private JacksonTester<AgendarConsultaDto> agendarConsultaJson;

    @Autowired
    private JacksonTester<DetalhesConsultaDto> detalhesConsultaJson;

    @Test
    @DisplayName("Deveria devover codigo 400 quando informacoes estao invalidas")
    @WithMockUser
    public void agendarCase01() throws Exception {
        var reponse = mvc.perform(post("/consultas/agendamento")).andReturn().getResponse();
        assertThat(reponse.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria retornar 200 ao realizar um agendamento com dados validos")
    @WithMockUser
    public void agendarCase02() throws Exception {
        var dataDe = LocalDateTime.now().plusHours(1);
        var dataAte = dataDe.plusHours(1);

        var agendar = new AgendarConsultaDto(1L, 1L, dataDe, dataAte, Especialidade.CARDIOLOGIA);
        var consultaAgendada = new DetalhesConsultaDto(null, 1L, 1L, dataDe, dataAte, null);

        when(agendarConsultaUseCase.agendar(any())).thenReturn(consultaAgendada);

        var reponse = mvc.perform(
                post("/consultas/agendamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(agendarConsultaJson.write(agendar).getJson())
        ).andReturn().getResponse();

        assertThat(reponse.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        String jsonEsperado = detalhesConsultaJson.write(consultaAgendada).getJson();

        assertThat(reponse.getContentAsString()).isEqualTo(jsonEsperado);
    }
}
