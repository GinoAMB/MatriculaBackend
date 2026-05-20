package com.matricula.mapper;

import com.matricula.dto.periodo_escolar.PeriodoRequestDTO;
import com.matricula.dto.periodo_escolar.PeriodoResponseDTO;
import com.matricula.dto.periodo_escolar.PeriodoUpdateRequestDTO;
import com.matricula.entity.PeriodoEscolarEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PeriodoMapper {

    @Mapping(target = "idPeriodo", ignore = true)
    PeriodoEscolarEntity toEntity(PeriodoRequestDTO requestDTO);

    PeriodoResponseDTO toDTO(PeriodoEscolarEntity entity);

    void updateEntityFromDto(PeriodoUpdateRequestDTO requestDTO, @MappingTarget PeriodoEscolarEntity entity);
}
