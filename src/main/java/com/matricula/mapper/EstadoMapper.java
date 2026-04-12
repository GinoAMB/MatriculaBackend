package com.matricula.mapper;

import com.matricula.dto.estado.EstadoRequestDTO;
import com.matricula.dto.estado.EstadoResponseDTO;
import com.matricula.entity.EstadoMatriculaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EstadoMapper {

    @Mapping(target = "idEstado", ignore = true)
    EstadoMatriculaEntity toEntity(EstadoRequestDTO dto);

    EstadoResponseDTO toDTO(EstadoMatriculaEntity entity);
}
