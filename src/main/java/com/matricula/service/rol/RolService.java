package com.matricula.service.rol;

import com.matricula.dto.rol.RolRequestDTO;
import com.matricula.dto.rol.RolResponseDTO;

import java.util.List;

public interface RolService {
    RolResponseDTO register(RolRequestDTO requestDTO);
    List<RolResponseDTO> list();
}
