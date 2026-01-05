package com.example.sistemadeclinica.service;

import com.example.sistemadeclinica.dto.AtualizarMedicoDto;
import com.example.sistemadeclinica.dto.CriarMedicoDto;
import com.example.sistemadeclinica.dto.DetalhesMedicoDto;
import com.example.sistemadeclinica.dto.ItemListaMedicoDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;

public interface MedicoService {

    DetalhesMedicoDto criar(CriarMedicoDto criarMedicoDto);
    PagedModel<ItemListaMedicoDto> getList(Pageable pageable);
    DetalhesMedicoDto getById(Long id);
    DetalhesMedicoDto update(AtualizarMedicoDto medicoUpdateDto);
    void delete(Long id);


}
