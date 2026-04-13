package com.matricula.service.estado.impl;

import com.matricula.dto.estado.EstadoResponseDTO;
import com.matricula.entity.EstadoMatriculaEntity;
import com.matricula.mapper.EstadoMapper;
import com.matricula.repository.EstadoRepository;
import com.matricula.service.estado.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository estadoRepository;
    private final EstadoMapper estadoMapper;

    @Override
    public List<EstadoResponseDTO> list() {
        List<EstadoMatriculaEntity> estados = estadoRepository.findAll();
        return estados.stream()
                .map(estadoMapper::toDTO)
                .toList();
    }
}
