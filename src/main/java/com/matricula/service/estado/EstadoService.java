package com.matricula.service.estado;

import com.matricula.dto.estado.EstadoRequestDTO;
import com.matricula.dto.estado.EstadoResponseDTO;

import java.util.List;

public interface EstadoService {
    EstadoResponseDTO register(EstadoRequestDTO requestDTO);
    List<EstadoResponseDTO> list();
}
