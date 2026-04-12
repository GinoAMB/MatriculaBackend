package com.matricula.service.rol.impl;

import com.matricula.dto.rol.RolRequestDTO;
import com.matricula.dto.rol.RolResponseDTO;
import com.matricula.entity.RolEntity;
import com.matricula.exception.BadRequestException;
import com.matricula.mapper.RolMapper;
import com.matricula.repository.RolRepository;
import com.matricula.service.rol.RolService;
import com.matricula.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    @Override
    public RolResponseDTO register(RolRequestDTO requestDTO) {

        // Validar duplicado
        if (rolRepository.existsByNombreIgnoreCase(requestDTO.nombre())) {
            throw new BadRequestException(MessageConstants.Rol.ALREADY_EXISTS);
        }

        RolEntity entity = rolMapper.toEntity(requestDTO);
        RolEntity saved = rolRepository.save(entity);
        return rolMapper.toDto(saved);
    }

    @Override
    public List<RolResponseDTO> list() {
        List<RolEntity> roles = rolRepository.findAll();
        return roles.stream()
                .map(rolMapper::toDto)
                .toList();
    }
}
