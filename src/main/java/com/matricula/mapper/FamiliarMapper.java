package com.matricula.mapper;

import com.matricula.dto.matricula.FamiliarBasicoRequestDTO;
import com.matricula.entity.PersonaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FamiliarMapper {

    @Mapping(target = "idPersona", ignore = true)
    @Mapping(target = "tipoDocumento", ignore = true)
    @Mapping(target = "religion", ignore = true)
    @Mapping(target = "pais", ignore = true)
    @Mapping(target = "fechaNacimiento", ignore = true)
    @Mapping(target = "vieneDeOtraInstitucion", ignore = true)
    @Mapping(target = "nombreInstitucionProcedencia", ignore = true)
    @Mapping(target = "tieneDiscapacidad", ignore = true)
    @Mapping(target = "descripcionDiscapacidad", ignore = true)
    @Mapping(target = "relaciones", ignore = true)
    PersonaEntity toEntity(FamiliarBasicoRequestDTO dto);
}