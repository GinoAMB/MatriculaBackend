package com.matricula.mapper;

import com.matricula.dto.matricula.AlumnoRequestDTO;
import com.matricula.entity.PersonaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonaMapper {

    @Mapping(target = "idPersona", ignore = true)
    @Mapping(target = "tipoDocumento", ignore = true)
    @Mapping(target = "religion", ignore = true)
    @Mapping(target = "pais", ignore = true)
    @Mapping(target = "celular", ignore = true)
    PersonaEntity toAlumnoEntity(AlumnoRequestDTO dto);
}
