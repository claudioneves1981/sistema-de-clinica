package com.example.sistemadeclinica.validators.consulta.agendamento.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "horario")
public class AgendarConsultaValidatorDto {

    private int horaAbertura;
    private int horaEncerramento;

}
