package com.matricula.service.religion.impl;

import com.matricula.dto.religion.ReligionRequestDTO;
import com.matricula.dto.religion.ReligionResponseDTO;
import com.matricula.entity.ReligionEntity;
import com.matricula.exception.BadRequestException;
import com.matricula.mapper.ReligionMapper;
import com.matricula.repository.ReligionRepository;
import com.matricula.service.religion.ReligionService;
import com.matricula.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReligionServiceImpl implements ReligionService {

    private final ReligionRepository religionRepository;
    private final ReligionMapper religionMapper;

    @Override
    public ReligionResponseDTO register(ReligionRequestDTO requestDTO) {

        // Validar duplicado
        if(religionRepository.existsByNombreIgnoreCase(requestDTO.nombre())){
            throw new BadRequestException(MessageConstants.Religion.ALREADY_EXISTS);
        }

        ReligionEntity entity = religionMapper.toEntity(requestDTO);
        ReligionEntity saved = religionRepository.save(entity);

        return religionMapper.toDTO(saved);
    }

    @Override
    public List<ReligionResponseDTO> list() {
        List<ReligionEntity> religiones = religionRepository.findAll();
        return religiones.stream()
                .map(religionMapper::toDTO)
                .toList();
    }
}
