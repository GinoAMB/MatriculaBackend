package com.matricula.service.pais;

import com.matricula.dto.pais.PaisRequestDTO;
import com.matricula.dto.pais.PaisResponseDTO;

import java.util.List;

public interface PaisService {
    PaisResponseDTO register(PaisRequestDTO requestDTO);
    List<PaisResponseDTO> list();
}
