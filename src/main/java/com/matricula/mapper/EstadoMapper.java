package com.matricula.mapper;

import com.matricula.dto.estado.EstadoResponseDTO;
import com.matricula.entity.EstadoMatriculaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EstadoMapper {

    EstadoResponseDTO toDTO(EstadoMatriculaEntity entity);
}
