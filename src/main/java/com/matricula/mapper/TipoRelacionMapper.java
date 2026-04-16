package com.matricula.mapper;

import com.matricula.dto.tipo_relacion.TipoRequestDTO;
import com.matricula.dto.tipo_relacion.TipoResponseDTO;
import com.matricula.entity.TipoRelacionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TipoRelacionMapper {

    @Mapping(target = "idTipoRelacion", ignore = true)
    TipoRelacionEntity toEntity(TipoRequestDTO requestDTO);

    TipoResponseDTO toDTO(TipoRelacionEntity entity);
}
