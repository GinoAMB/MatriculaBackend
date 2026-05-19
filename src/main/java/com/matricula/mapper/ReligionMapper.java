package com.matricula.mapper;

import com.matricula.dto.religion.ReligionRequestDTO;
import com.matricula.dto.religion.ReligionResponseDTO;
import com.matricula.dto.religion.ReligionUpdateRequestDTO;
import com.matricula.entity.ReligionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReligionMapper {

    @Mapping(target = "idReligion", ignore = true)
    ReligionEntity toEntity(ReligionRequestDTO dto);

    ReligionResponseDTO toDTO(ReligionEntity entity);

    void updateEntityFromDto(ReligionUpdateRequestDTO requestDTO, @MappingTarget ReligionEntity entity);
}
