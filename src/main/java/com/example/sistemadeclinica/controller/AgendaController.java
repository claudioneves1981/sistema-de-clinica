package com.example.sistemadeclinica.controller;

import com.example.sistemadeclinica.dto.AgendaDTO;
import com.example.sistemadeclinica.dto.AgendaViewDTO;
import com.example.sistemadeclinica.dto.PacienteViewDTO;
import com.example.sistemadeclinica.service.AgendaPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AgendaController {

    @Autowired
    private AgendaPacienteService agendaPacienteService;

    @PostMapping(value = "adicionar")
    @ResponseBody
    public void adicionar(@RequestBody AgendaDTO agendaDTO) {
        agendaPacienteService.adicionar(agendaDTO);
    }

    @DeleteMapping(value = "agenda/{id}/removerpaciente/{cpf}")
    @ResponseBody
    public void remover(@PathVariable("id") Long id, @PathVariable("cpf") String cpf) throws Exception {
        agendaPacienteService.remover(id,cpf);
    }

    @GetMapping(value = "agenda/{id}/pesquisarcpf/{cpf}")
    @ResponseBody
    public ResponseEntity<PacienteViewDTO> pesquisar(@PathVariable("id") Long id, @PathVariable("cpf") String cpf) throws Exception {
        PacienteViewDTO pacienteDTO = agendaPacienteService.pesquisar(id,cpf);
        if(pacienteDTO.getNome() == null){
            return new ResponseEntity<>(pacienteDTO,HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(pacienteDTO, HttpStatus.OK);
        }
    }

    @GetMapping(value = "agenda/{id}/listarpacientes")
    @ResponseBody
    public ResponseEntity<AgendaViewDTO> listarTodosPacientes(@PathVariable Long id) throws Exception {
        AgendaViewDTO agendaViewDTO = agendaPacienteService.listarTodosPacientes(id);
        return new ResponseEntity<>(agendaViewDTO,HttpStatus.OK);
    }
}
