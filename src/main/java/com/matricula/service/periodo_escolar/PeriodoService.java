package com.matricula.service.periodo_escolar;

import com.matricula.dto.periodo_escolar.PeriodoRequestDTO;
import com.matricula.dto.periodo_escolar.PeriodoResponseDTO;

import java.util.List;

public interface PeriodoService {
    PeriodoResponseDTO register(PeriodoRequestDTO requestDTO);
    List<PeriodoResponseDTO> list();
}
