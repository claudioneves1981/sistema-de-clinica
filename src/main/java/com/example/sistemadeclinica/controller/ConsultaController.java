package com.example.sistemadeclinica.controller;

import com.example.sistemadeclinica.dto.AgendarConsultaDto;
import com.example.sistemadeclinica.dto.CancelarConsultaDto;
import com.example.sistemadeclinica.dto.DetalhesConsultaDto;
import com.example.sistemadeclinica.service.AgendarConsultaService;
import com.example.sistemadeclinica.service.CancelarConsultaService;
import com.example.sistemadeclinica.service.ConsultaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService detalhesConsultaService;
    private final AgendarConsultaService agendarConsultaService;
    private final CancelarConsultaService cancelarConsultaService;

    @GetMapping("/{idConsulta}")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<DetalhesConsultaDto> detalhes(@PathVariable Long idConsulta) {
        return ResponseEntity.ok(detalhesConsultaService.getById(idConsulta));
    }

    @PostMapping("/agendamento")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<DetalhesConsultaDto> agendar(@RequestBody @Valid AgendarConsultaDto agendarConsultaDto, UriComponentsBuilder uriComponentsBuilder) {
        var detalhes = agendarConsultaService.agendar(agendarConsultaDto);
        var uri = uriComponentsBuilder.path("/consulta/{idConsulta}").buildAndExpand(detalhes.id()).toUri();
        return ResponseEntity.created(uri).body(detalhes);
    }

    @PostMapping("/cancelamento")
    //@PreAuthorize("hasRole('USER')")
    public ResponseEntity<DetalhesConsultaDto> cancelar(@RequestBody @Valid CancelarConsultaDto cancelarConsultaDto) {
        var detalhes = cancelarConsultaService.cancelar(cancelarConsultaDto);
        return ResponseEntity.ok(detalhes);
    }
}