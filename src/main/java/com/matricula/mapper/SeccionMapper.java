package com.matricula.mapper;

import com.matricula.dto.academico.SeccionResponseDTO;
import com.matricula.entity.SeccionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeccionMapper {

    SeccionResponseDTO toDTO(SeccionEntity entity);
}
