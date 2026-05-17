package com.matricula.mapper;

import com.matricula.dto.rol.RolRequestDTO;
import com.matricula.dto.rol.RolResponseDTO;
import com.matricula.dto.rol.RolUpdateRequestDTO;
import com.matricula.entity.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RolMapper {

    //DTO -> Entity
    @Mapping(target = "idRol", ignore = true)
    RolEntity toEntity(RolRequestDTO dto);

    //Entity -> DTO
    RolResponseDTO toDto(RolEntity entity);

    // Actualizar entidad existente
    void updateEntityFromDto(RolUpdateRequestDTO dto, @MappingTarget RolEntity entity);
}
