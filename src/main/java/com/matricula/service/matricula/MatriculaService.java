package com.matricula.service.matricula;

import com.matricula.dto.common.PageResponseDTO;
import com.matricula.dto.matricula.AlumnoDetalleResponseDTO;
import com.matricula.dto.matricula.AlumnoPrintDTO;
import com.matricula.dto.matricula.AlumnoResponseDTO;
import com.matricula.dto.matricula.MatriculaRequestDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MatriculaService {

    AlumnoResponseDTO registrar(MatriculaRequestDTO request);
    PageResponseDTO<AlumnoResponseDTO> list(
            String search,
            String nivel,
            String grado,
            String seccion,
            Pageable pageable
    );

    AlumnoDetalleResponseDTO findById(Integer idMatricula);

    List<AlumnoPrintDTO> listarParaImpresion(String nivel, String grado, String seccion);
}