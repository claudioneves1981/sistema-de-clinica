package com.example.sistemadeclinica.service.impl;

import com.example.sistemadeclinica.dto.AtualizarMedicoDto;
import com.example.sistemadeclinica.dto.CriarMedicoDto;
import com.example.sistemadeclinica.dto.DetalhesMedicoDto;
import com.example.sistemadeclinica.dto.ItemListaMedicoDto;
import com.example.sistemadeclinica.model.Medico;
import com.example.sistemadeclinica.repository.MedicoRepository;
import com.example.sistemadeclinica.service.MedicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl implements MedicoService {

    private final MedicoRepository medicoRepository;

    @Override
    @Transactional
    public DetalhesMedicoDto criar(CriarMedicoDto criarMedicoDto){

        var medico = medicoRepository.save(Medico.criar(criarMedicoDto));
        return new DetalhesMedicoDto(medico);

    }

    @Override
    @Transactional
    public PagedModel<ItemListaMedicoDto> getList(Pageable pageable){

        return new PagedModel<>(medicoRepository.findAllByAtivoTrue(pageable).map(ItemListaMedicoDto::new));

    }

    @Override
    @Transactional
    public DetalhesMedicoDto getById(Long id){

        var medico = medicoRepository.getReferenceById(id);
        return new DetalhesMedicoDto(medico);

    }

    @Override
    @Transactional
    public DetalhesMedicoDto update(AtualizarMedicoDto medicoUpdateDto){

        var medico = medicoRepository.getReferenceById(medicoUpdateDto.id());
        medico.atualizar(medicoUpdateDto);
        return new DetalhesMedicoDto(medico);

    }

    @Override
    @Transactional
    public void delete(Long id){
        var medico = medicoRepository.getReferenceById(id);
        medico.inativar();
    }





}
