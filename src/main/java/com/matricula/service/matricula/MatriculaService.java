package com.matricula.service.matricula;

import com.matricula.dto.matricula.AlumnoResponseDTO;
import com.matricula.dto.matricula.MatriculaRequestDTO;

public interface MatriculaService {

    AlumnoResponseDTO registrar(MatriculaRequestDTO request);

}