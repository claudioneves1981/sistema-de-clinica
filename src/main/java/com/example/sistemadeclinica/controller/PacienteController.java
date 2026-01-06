package com.example.sistemadeclinica.controller;

import com.example.sistemadeclinica.dto.AtualizarPacienteDto;
import com.example.sistemadeclinica.dto.CriarPacienteDto;
import com.example.sistemadeclinica.dto.DetalhesPacienteDto;
import com.example.sistemadeclinica.dto.ItemListaPacienteDto;
import com.example.sistemadeclinica.service.PacienteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<DetalhesPacienteDto> create(@RequestBody @Valid CriarPacienteDto pacienteDto, UriComponentsBuilder uriComponentsBuilder) {
        var paciente = pacienteService.criar(pacienteDto);
        var uri = uriComponentsBuilder.path("/paciente/{idConsulta}").buildAndExpand(paciente.id()).toUri();
        return ResponseEntity.created(uri).body(paciente);
    }

    @GetMapping
    public ResponseEntity<PagedModel<ItemListaPacienteDto>> getList(Pageable pageable) {
        var pagedModel = pacienteService.getList(pageable);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{idConsulta}")
    public ResponseEntity<DetalhesPacienteDto> getById(@PathVariable Long idConsulta) {
        var paciente = pacienteService.getById(idConsulta);
        return ResponseEntity.ok(paciente);
    }

    @PutMapping
    public ResponseEntity<DetalhesPacienteDto> update(@RequestBody @Valid AtualizarPacienteDto pacienteUpdateDto) {
        var paciente = pacienteService.update(pacienteUpdateDto);
        return ResponseEntity.ok(paciente);
    }

    @DeleteMapping("/{idConsulta}")
    public ResponseEntity<Void> delete(@PathVariable Long idConsulta) {
        pacienteService.delete(idConsulta);
        return ResponseEntity.noContent().build();
    }
}
