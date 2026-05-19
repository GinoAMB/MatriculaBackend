package com.matricula.mapper;

import com.matricula.dto.documento.DocumentoRequestDTO;
import com.matricula.dto.documento.DocumentoResponseDTO;
import com.matricula.dto.documento.DocumentoUpdateRequestDTO;
import com.matricula.entity.DocumentoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DocumentoMapper {

    @Mapping(target = "idTipo", ignore = true)
    DocumentoEntity toEntity(DocumentoRequestDTO dto);

    DocumentoResponseDTO toDTO(DocumentoEntity entity);

    void updateEntityFromDto(DocumentoUpdateRequestDTO requestDTO, @MappingTarget DocumentoEntity entity);
}
