package com.matricula.service.pais.impl;

import com.matricula.dto.pais.PaisRequestDTO;
import com.matricula.dto.pais.PaisResponseDTO;
import com.matricula.entity.PaisEntity;
import com.matricula.exception.BadRequestException;
import com.matricula.mapper.PaisMapper;
import com.matricula.repository.PaisRepository;
import com.matricula.service.pais.PaisService;
import com.matricula.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaisServiceImpl implements PaisService {

    private final PaisRepository paisRepository;
    private final PaisMapper paisMapper;

    @Override
    public PaisResponseDTO register(PaisRequestDTO requestDTO) {

        // Validar duplicidad
        if(paisRepository.existsByNombreIgnoreCase(requestDTO.nombre())){
            throw new BadRequestException(MessageConstants.Pais.ALREADY_EXISTS);
        }

        PaisEntity entity = paisMapper.toEntity(requestDTO);
        PaisEntity saved = paisRepository.save(entity);

        return paisMapper.toDTO(saved);
    }

    @Override
    public List<PaisResponseDTO> list() {
        List<PaisEntity> paises = paisRepository.findAll();
        return paises.stream()
                .map(paisMapper::toDTO)
                .toList();
    }
}
