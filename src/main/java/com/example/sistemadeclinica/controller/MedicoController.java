package com.example.sistemadeclinica.controller;

import com.example.sistemadeclinica.dto.AtualizarMedicoDto;
import com.example.sistemadeclinica.dto.CriarMedicoDto;
import com.example.sistemadeclinica.dto.DetalhesMedicoDto;
import com.example.sistemadeclinica.dto.ItemListaMedicoDto;
import com.example.sistemadeclinica.model.Medico;
import com.example.sistemadeclinica.repository.MedicoRepository;
import com.example.sistemadeclinica.service.MedicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService medicoService;

    @PostMapping
    public ResponseEntity<DetalhesMedicoDto> create(@RequestBody @Valid CriarMedicoDto criarMedicoDto, UriComponentsBuilder uriComponentsBuilder) {
        var medico = medicoService.criar(criarMedicoDto);
        var uri = uriComponentsBuilder.path("/medico/{idConsulta}").buildAndExpand(medico.id()).toUri();
        return ResponseEntity.created(uri).body(medico);
    }

    @GetMapping
    public ResponseEntity<PagedModel<ItemListaMedicoDto>> getList(Pageable pageable) {
        var pagedModel = medicoService.getList(pageable);
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{idConsulta}")
    public ResponseEntity<DetalhesMedicoDto> getById(@PathVariable Long idConsulta) {
        var medico = medicoService.getById(idConsulta);
        return ResponseEntity.ok(medico);
    }

    @PutMapping
    public ResponseEntity<DetalhesMedicoDto> update(@RequestBody @Valid AtualizarMedicoDto medicoUpdateDto) {
        var medico = medicoService.update(medicoUpdateDto);
        return ResponseEntity.ok(medico);
    }

    @DeleteMapping("/{idConsulta}")
    public ResponseEntity<Void> delete(@PathVariable Long idConsulta) {
        medicoService.delete(idConsulta);
        return ResponseEntity.noContent().build();
    }
}