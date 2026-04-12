package com.matricula.mapper;

import com.matricula.dto.documento.DocumentoRequestDTO;
import com.matricula.dto.documento.DocumentoResponseDTO;
import com.matricula.entity.DocumentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentoMapper {

    @Mapping(target = "idTipo", ignore = true)
    DocumentoEntity toEntity(DocumentoRequestDTO dto);

    DocumentoResponseDTO toDTO(DocumentoEntity entity);
}
