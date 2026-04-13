package com.matricula.service.periodo_escolar.impl;

import com.matricula.dto.periodo_escolar.PeriodoRequestDTO;
import com.matricula.dto.periodo_escolar.PeriodoResponseDTO;
import com.matricula.entity.PeriodoEscolarEntity;
import com.matricula.exception.BadRequestException;
import com.matricula.mapper.PeriodoMapper;
import com.matricula.repository.PeriodoEscolarRepository;
import com.matricula.service.periodo_escolar.PeriodoService;
import com.matricula.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeriodoServiceImpl implements PeriodoService {

    private final PeriodoEscolarRepository periodoRepository;
    private final PeriodoMapper periodoMapper;

    @Override
    public PeriodoResponseDTO register(PeriodoRequestDTO requestDTO) {

        // Validar duplicidad
        if(periodoRepository.existsByAnio(requestDTO.anio())){
            throw new BadRequestException(MessageConstants.PeriodoEscolar.ALREADY_EXISTS);
        }

        PeriodoEscolarEntity entity = periodoMapper.toEntity(requestDTO);
        PeriodoEscolarEntity saved = periodoRepository.save(entity);
        return periodoMapper.toDTO(saved);
    }

    @Override
    public List<PeriodoResponseDTO> list() {
        List<PeriodoEscolarEntity> periodoEscolarEntities = periodoRepository.findAll();
        return periodoEscolarEntities.stream()
                .map(periodoMapper::toDTO)
                .toList();
    }
}
