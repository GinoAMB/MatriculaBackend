package com.matricula.service.academico;

import com.matricula.dto.academico.NivelResponseDTO;
import com.matricula.dto.academico.SeccionRequestDTO;

import java.util.List;

public interface NivelService {

    List<NivelResponseDTO> listarEstructura();
    void registrarSeccion(SeccionRequestDTO request);
}
