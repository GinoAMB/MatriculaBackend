package com.matricula.service.tipo_relacion.impl;

import com.matricula.dto.tipo_relacion.TipoRequestDTO;
import com.matricula.dto.tipo_relacion.TipoResponseDTO;
import com.matricula.entity.TipoRelacionEntity;
import com.matricula.exception.BadRequestException;
import com.matricula.mapper.TipoRelacionMapper;
import com.matricula.repository.TipoRelacionRepository;
import com.matricula.service.tipo_relacion.TipoRelacionService;
import com.matricula.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoRelacionServiceImpl implements TipoRelacionService {

    private final TipoRelacionRepository tipoRelacionRepository;
    private final TipoRelacionMapper tipoRelacionMapper;

    @Override
    public TipoResponseDTO register(TipoRequestDTO requestDTO) {

        if(tipoRelacionRepository.existsByNombreIgnoreCase(requestDTO.nombre())){
            throw new BadRequestException(MessageConstants.TipoRelacion.ALREADY_EXISTS);
        }

        TipoRelacionEntity entity = tipoRelacionMapper.toEntity(requestDTO);
        TipoRelacionEntity saved = tipoRelacionRepository.save(entity);
        return tipoRelacionMapper.toDTO(saved);
    }

    @Override
    public List<TipoResponseDTO> list() {
        List<TipoRelacionEntity> tipoRelacionEntities = tipoRelacionRepository.findAll();
        return tipoRelacionEntities.stream()
                .map(tipoRelacionMapper::toDTO)
                .toList();
    }
}
