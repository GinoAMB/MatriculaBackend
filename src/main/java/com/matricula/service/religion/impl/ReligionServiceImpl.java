package com.matricula.service.religion.impl;

import com.matricula.dto.religion.ReligionRequestDTO;
import com.matricula.dto.religion.ReligionResponseDTO;
import com.matricula.dto.religion.ReligionUpdateRequestDTO;
import com.matricula.entity.ReligionEntity;
import com.matricula.exception.BadRequestException;
import com.matricula.exception.NotFoundException;
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

    @Override
    public ReligionResponseDTO update(ReligionUpdateRequestDTO requestDTO) {

        // Buscar pais existente
        ReligionEntity religion = religionRepository.findById(requestDTO.idReligion())
                .orElseThrow( () ->
                        new NotFoundException(MessageConstants.Religion.NOT_FOUND));

        // Validar nombre duplicado
        if (religionRepository.existsByNombreIgnoreCase(requestDTO.nombre())
                && !religion.getNombre().equalsIgnoreCase(requestDTO.nombre())) {

            throw new BadRequestException(MessageConstants.Religion.ALREADY_EXISTS);
        }

        //Actualizar entity existente
        religionMapper.updateEntityFromDto(requestDTO, religion);

        ReligionEntity update = religionRepository.save(religion);

        return religionMapper.toDTO(update);
    }
}
