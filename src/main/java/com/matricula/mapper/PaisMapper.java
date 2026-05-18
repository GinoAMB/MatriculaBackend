package com.matricula.mapper;

import com.matricula.dto.pais.PaisRequestDTO;
import com.matricula.dto.pais.PaisResponseDTO;
import com.matricula.dto.pais.PaisUpdateRequestDTO;
import com.matricula.entity.PaisEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PaisMapper {

    @Mapping(target = "idPais", ignore = true)
    PaisEntity toEntity(PaisRequestDTO dto);

    PaisResponseDTO toDTO(PaisEntity entity);

    void updateEntityFromDto(PaisUpdateRequestDTO requestDTO, @MappingTarget PaisEntity entity);
}
