package com.matricula.mapper;

import com.matricula.dto.rol.RolRequestDTO;
import com.matricula.dto.rol.RolResponseDTO;
import com.matricula.entity.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RolMapper {

    //DTO -> Entity
    @Mapping(target = "idRol", ignore = true)
    RolEntity toEntity(RolRequestDTO dto);

    //Entity -> DTO
    RolResponseDTO toDto(RolEntity entity);
}
