package com.matricula.service.periodo_escolar.impl;

import com.matricula.dto.periodo_escolar.PeriodoRequestDTO;
import com.matricula.dto.periodo_escolar.PeriodoResponseDTO;
import com.matricula.dto.periodo_escolar.PeriodoUpdateRequestDTO;
import com.matricula.entity.PeriodoEscolarEntity;
import com.matricula.exception.BadRequestException;
import com.matricula.exception.NotFoundException;
import com.matricula.mapper.PeriodoMapper;
import com.matricula.repository.PeriodoEscolarRepository;
import com.matricula.service.periodo_escolar.PeriodoService;
import com.matricula.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Override
    public PeriodoResponseDTO update(PeriodoUpdateRequestDTO requestDTO) {

        //Buscar periodo existente
        PeriodoEscolarEntity periodoEscolar = periodoRepository.findById(requestDTO.idPeriodo())
                .orElseThrow( () ->
                        new NotFoundException(MessageConstants.PeriodoEscolar.NOT_FOUND));

        // Validar que el periodo no haya finalizado
        if (LocalDate.now().isAfter(periodoEscolar.getFechaFin())) {
            throw new BadRequestException(MessageConstants.PeriodoEscolar.PERIOD_FINISHED);
        }

        // Validar duplicidad solo si el año cambió
        if (!periodoEscolar.getAnio().equals(requestDTO.anio())
                && periodoRepository.existsByAnio(requestDTO.anio())) {

            throw new BadRequestException(MessageConstants.PeriodoEscolar.ALREADY_EXISTS);
        }

        //Actualizar entity existente
        periodoMapper.updateEntityFromDto(requestDTO, periodoEscolar);

        PeriodoEscolarEntity update = periodoRepository.save(periodoEscolar);

        return periodoMapper.toDTO(update);
    }
}
