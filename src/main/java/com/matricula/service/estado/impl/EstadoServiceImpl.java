package com.matricula.service.estado.impl;

import com.matricula.dto.estado.EstadoRequestDTO;
import com.matricula.dto.estado.EstadoResponseDTO;
import com.matricula.entity.EstadoMatriculaEntity;
import com.matricula.exception.BadRequestException;
import com.matricula.mapper.EstadoMapper;
import com.matricula.repository.EstadoRepository;
import com.matricula.service.estado.EstadoService;
import com.matricula.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoServiceImpl implements EstadoService {

    private final EstadoRepository estadoRepository;
    private final EstadoMapper estadoMapper;

    @Override
    public EstadoResponseDTO register(EstadoRequestDTO requestDTO) {

        // Validar duplicado
        if (estadoRepository.existsByNombreIgnoreCase(requestDTO.nombre())){
            throw new BadRequestException(MessageConstants.EstadoMatricula.ALREADY_EXISTS);
        }

        EstadoMatriculaEntity entity = estadoMapper.toEntity(requestDTO);
        EstadoMatriculaEntity saved = estadoRepository.save(entity);
        return estadoMapper.toDTO(saved);
    }

    @Override
    public List<EstadoResponseDTO> list() {
        List<EstadoMatriculaEntity> estados = estadoRepository.findAll();
        return estados.stream()
                .map(estadoMapper::toDTO)
                .toList();
    }
}
