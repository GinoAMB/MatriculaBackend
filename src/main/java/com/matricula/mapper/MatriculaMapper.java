package com.matricula.mapper;

import com.matricula.dto.matricula.MatriculaRequestDTO;
import com.matricula.entity.MatriculaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MatriculaMapper {

    @Mapping(target = "idMatricula", ignore = true)
    @Mapping(target = "alumno", ignore = true) // ⚠️ se setea manual
    @Mapping(target = "seccion", ignore = true) // ⚠️ se busca en BD
    @Mapping(target = "periodo", ignore = true)
    @Mapping(target = "estado", ignore = true)
    MatriculaEntity toEntity(MatriculaRequestDTO dto);
}
